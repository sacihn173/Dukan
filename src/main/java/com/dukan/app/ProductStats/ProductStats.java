package com.dukan.app.ProductStats;

import jakarta.persistence.*;

/* Every time a 'Product' is created, create a 'ProductStats' */
@Entity
@Table(name = "product_stats")
public class ProductStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer productId;
    private Integer sales;
    private Integer impressions;

    public ProductStats(){
    }

    public ProductStats(Integer productId, Integer sales, Integer impressions) {
        this.productId = productId;
        this.sales = sales;
        this.impressions = impressions;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getImpressions() {
        return impressions;
    }

    public void setImpressions(Integer impressions) {
        this.impressions = impressions;
    }
}
