package com.dukan.app.Feed;

import com.dukan.app.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    @Autowired
    private FeedService feedSrv;

    @GetMapping
    public List<Product> generateFeed(@RequestParam int userId, @RequestParam(defaultValue = "100") int size) {
        return feedSrv.generateUserFeed(userId, size);
    }

}
