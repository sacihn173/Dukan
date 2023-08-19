package com.dukan.app.Feed;

import com.dukan.app.CommonProductStats.CommonProductStats;
import com.dukan.app.CommonProductStats.CommonProductStatsService;
import com.dukan.app.Order.Order;
import com.dukan.app.Product.Product;
import com.dukan.app.Product.ProductService;
import com.dukan.app.Tags.Tag;
import com.dukan.app.Tags.TagService;
import com.dukan.app.User.User;
import com.dukan.app.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FeedService {

    @Autowired
    private UserService userSrv;
    @Autowired
    private CommonProductStatsService cpsSrv;
    @Autowired
    private TagService tagSrv;
    @Autowired
    private ProductService productSrv;

    public List<Product> generateUserFeed(int userId, int size) {
        List<Product> feed = new ArrayList<>();
        // get all history products
        User user = userSrv.findUserById(userId);
        // TODO: return trending products here
        if(user == null) return feed;

        Set<Integer> hisProductIds = new HashSet<>();
        for(Order order : user.getOrders()) {
            for(Product product : order.getProducts()) {
                hisProductIds.add(product.getProductId());
            }
        }

        // [history product, product with similar tag, common sales]
        List<List<Integer>> similarProducts = new ArrayList<>();
        // find products with same tags
        for(Order order : user.getOrders()) {
            for(Product product : order.getProducts()) {
                for(Tag tag : product.getTags()) {
                    Set<Product> pWithTags = tagSrv.findProductsByTag(tag.getTag());
                    for(Product p : pWithTags) {
                        similarProducts.add(Arrays.asList(product.getProductId(), p.getProductId(), 0));
                    }
                }
            }
        }

        // get common sales of the products
        for(List<Integer> v : similarProducts) {
            CommonProductStats cps = cpsSrv.findByProductIds(v.get(0), v.get(1));
            if(cps != null) {
                v.set(2, cps.getSales());
            } else v.set(2, 0);
        }
        Map<Integer, Integer> productSales = new HashMap<>();
        for(var v : similarProducts)
            productSales.put(v.get(1), v.get(2));
        similarProducts.clear();
        for (Map.Entry<Integer, Integer> entry : productSales.entrySet()) {
            similarProducts.add(Arrays.asList(entry.getKey(), entry.getValue()));
        }

        // sort in descending order by common sales
        Collections.sort(similarProducts, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o2.get(1)-o1.get(1);
            }
        });

        for(var v : similarProducts) {
            if(size == 0) return feed;
            feed.add(productSrv.findByProductId(v.get(0)));
            size--;
        }
        return feed;
    }

}
