package com.dukan.app.Product;

import com.dukan.app.ProductStats.ProductStatsService;
import com.dukan.app.Tags.Tag;
import com.dukan.app.Tags.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRpo;
    @Autowired
    private ProductStatsService psSrv;
    @Autowired
    private TagService tagSrv;

    public Product createProduct(Product product) {
        // create tags if don't exist
        for(Tag tag : product.getTags()) {
            tagSrv.createTag(tag);
        }
        productRpo.save(product);
        // create new ProductStats
        psSrv.createProductStats(product.getProductId(), 0, 0);
        return product;
    }
}
