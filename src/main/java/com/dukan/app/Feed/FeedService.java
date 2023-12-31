package com.dukan.app.Feed;

import com.dukan.app.CommonProductStats.CommonProductStatsService;
import com.dukan.app.Order.Order;
import com.dukan.app.Product.Product;
import com.dukan.app.Product.ProductService;
import com.dukan.app.Tags.TagService;
import com.dukan.app.User.User;
import com.dukan.app.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final Logger log = LoggerFactory.getLogger(FeedService.class);

    public List<Product> generateUserFeed(int userId, int size) {
        List<Product> feed = new ArrayList<>();

        // check if feed for user already present in cache
        // TODO : instead of caching the user feed directly, cache some other parameters
        if(Boolean.TRUE.equals(redisTemplate.hasKey(String.valueOf(userId))))
            return (List<Product>) redisTemplate.opsForValue().get(String.valueOf(userId));

        /*
         1. get user history products to get tags from them
         2. get products with similar tags
         3. from common stats get order result of products to be in feed
         4. get details of feed products (with limit 'size')
         */

        User user = userSrv.findUserById(userId);
        List<Integer> historyProductIds = new ArrayList<>();
        for(Order order : user.getOrders())
            for(Product product : order.getProducts())
                historyProductIds.add(product.getProductId());

        List<String> userHistoryTags = tagSrv.getProductTags(historyProductIds);

        List<Integer> productsWithSameTags = productSrv.findProductByTags(userHistoryTags);

        List<Integer> feedProductIds = cpsSrv.getStatsForProductOrderedBySales(productsWithSameTags, 0, size);

        feed = productSrv.findByProductIds(feedProductIds);

        cacheFeed(feed, userId);

        return feed;
    }

    private void cacheFeed(List<Product> feed, int userId) {
        redisTemplate.opsForValue().set(String.valueOf(userId), feed);
        log.info("feed cached for userId : " + userId);
    }

}
