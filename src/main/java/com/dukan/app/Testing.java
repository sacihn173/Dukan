package com.dukan.app;

import com.dukan.app.Feed.FeedService;
import com.dukan.app.Order.Order;
import com.dukan.app.Order.OrderRepository;
import com.dukan.app.Order.OrderService;
import com.dukan.app.Product.Product;
import com.dukan.app.Product.ProductRepository;
import com.dukan.app.Product.ProductService;
import com.dukan.app.Tags.Tag;
import com.dukan.app.User.User;
import com.dukan.app.User.UserRepository;
import com.dukan.app.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Testing {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    public void test() {

        for(int i = 0; i < 503; i++) {
            User user = new User();
            user.setName("sachin");
            userRepository.save(user);
        }
        for(int i = 0; i < 10000; i++) {
            Product prodcut = new Product();
            prodcut.setProductName("headphone" + " " + i);
            prodcut.setPrice(i);
            Set<Tag> s = new HashSet<>();
            Tag t1 = new Tag();
            t1.setTag("electronics");
            Tag t2 = new Tag();
            t2.setTag("headphone");
            Tag t3 = new Tag();
            t3.setTag("phone");
            s.addAll(Arrays.asList(t1, t2, t3));
            prodcut.setTags(s);
            productService.createProduct(prodcut);
        }
        for(int i = 0; i < 1000; i++) {
            int userId = (i+1) % 500;
            Order order = new Order();
            order.setUser(userRepository.findById(userId).orElse(null));
            Random ran = new Random();
            Product p1 = productRepository.findById(ran.nextInt(9999)).orElse(null);
            Product p2 = productRepository.findById(ran.nextInt(9999)).orElse(null);
            Set<Product> s = new HashSet<>();
            s.addAll(Arrays.asList(p1,p2));
            order.setProducts(s);
            orderService.createOrder(order);
        }

    }
}
