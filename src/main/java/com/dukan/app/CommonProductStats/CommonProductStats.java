package com.dukan.app.CommonProductStats;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * 'product1Id' < 'product2Id', always followed
 */
@Entity
@Table(name = "common_product_stats")
public class CommonProductStats implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer product1Id;
    private Integer product2Id;
    /* Common impressions on both the products */
    private Integer impressions;
    /* Common sales of both the products */
    private Integer sales;

    public CommonProductStats() {
    }

    public CommonProductStats(Integer product1Id, Integer product2Id, Integer impressions, Integer sales) {
        this.product1Id = product1Id;
        this.product2Id = product2Id;
        this.impressions = impressions;
        this.sales = sales;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct1Id() {
        return product1Id;
    }

    public void setProduct1Id(Integer product1Id) {
        this.product1Id = product1Id;
    }

    public Integer getProduct2Id() {
        return product2Id;
    }

    public void setProduct2Id(Integer product2Id) {
        this.product2Id = product2Id;
    }

    public Integer getImpressions() {
        return impressions;
    }

    public void setImpressions(Integer impressions) {
        this.impressions = impressions;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }
}
