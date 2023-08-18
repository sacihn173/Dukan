package com.dukan.app.CommonProductStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonProductStatsService {

    @Autowired
    private CommonProductStatsRepository cpsRpo;

    public CommonProductStats addNewCommonProductStats(int product1Id, int product2Id) {
        int id1 = Math.min(product1Id, product2Id);
        int id2 = Math.max(product1Id, product2Id);
        return cpsRpo.save(new CommonProductStats(id1, id2, 0, 0));
    }

    public void addCommonProductSales(int product1Id, int product2Id, int val) {
        // create product pair if don't exist
        CommonProductStats cps = findByProductIds(product1Id, product2Id);
        if(cps == null)
            cps = addNewCommonProductStats(product1Id, product2Id);
        if(cps != null) {
            cps.setSales(cps.getSales() + val);
            cpsRpo.save(cps);
        }
    }

    public void addCommonProductImpressions(int product1Id, int product2Id, int val) {
        // create product pair if don't exist
        addNewCommonProductStats(product1Id, product2Id);
        CommonProductStats cps = findByProductIds(product1Id, product2Id);
        if(cps != null) {
            cps.setImpressions(cps.getImpressions() + val);
            cpsRpo.save(cps);
        }
    }

    public CommonProductStats findByProductIds(int product1Id, int product2Id) {
        int id1 = Math.min(product1Id, product2Id);
        int id2 = Math.max(product1Id, product2Id);
        return cpsRpo.findByProductIds(id1, id2).orElse(null);
    }

}
