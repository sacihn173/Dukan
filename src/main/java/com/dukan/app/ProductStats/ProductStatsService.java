package com.dukan.app.ProductStats;

import com.dukan.app.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ProductStatsService {

    @Autowired
    private ProductStatsRepository psRpo;

    public void createProductStats(int productId, int sales, int impressions) {
        psRpo.save(new ProductStats(productId, sales, impressions));
    }

    // TODO: Make bulk updates instead of single each time
    @Async
    public void addSales(Integer productId) {
        ProductStats ps = psRpo.findByProductId(productId);
        if(ps != null) {
            ps.setSales(ps.getSales() + 1);
            psRpo.save(ps);
        }
    }

    @Async
    public void addImpression(Integer productId) {
        ProductStats ps = psRpo.findByProductId(productId);
        if(ps != null) {
            ps.setImpressions(ps.getImpressions() + 1);
            psRpo.save(ps);
        }
    }

}
