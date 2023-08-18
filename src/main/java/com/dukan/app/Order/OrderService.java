package com.dukan.app.Order;

import com.dukan.app.CommonProductStats.CommonProductStatsService;
import com.dukan.app.Product.Product;
import com.dukan.app.ProductStats.ProductStatsService;
import com.dukan.app.User.User;
import com.dukan.app.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRpo;
    @Autowired
    private ProductStatsService psSrv;
    @Autowired
    private CommonProductStatsService cpsSrv;
    @Autowired
    private UserService userSrv;

    public Order createOrder(Order order) {
        // add sales to product stats
        for(Product product : order.getProducts()) {
            psSrv.addSales(product.getProductId());
        }
        updateCommonProductSalesFromUserHistory(order);
        // TODO : This return additional field values as null for the first time, fix it
        return orderRpo.save(order);
    }

    @Async
    public void updateCommonProductSalesFromUserHistory(Order order) {
        // add sales to common product stats (get all history product of the user)
        User user = userSrv.findUserById(order.getUser().getUserId());
        for(Order userOrder : user.getOrders()) {
            for(Product hisProduct : userOrder.getProducts()) {
                for(Product curOrderProduct : order.getProducts()) {
                    if(!curOrderProduct.getProductId().equals(hisProduct.getProductId()))
                        cpsSrv.addCommonProductSales(hisProduct.getProductId(), curOrderProduct.getProductId(), 1);
                }
            }
        }
        List<Product> p1 = new ArrayList<>(order.getProducts());
        int index = 0;
        for(Product p : order.getProducts()) {
            for(int i = index+1; i < p1.size(); i++) {
                if(!p.getProductId().equals(p1.get(i).getProductId()))
                    cpsSrv.addCommonProductSales(p.getProductId(), p1.get(i).getProductId(), 1);
                index++;
            }
        }
    }

    public Order findById(Integer orderId) {
        return orderRpo.findById(orderId).orElse(null);
    }
}
