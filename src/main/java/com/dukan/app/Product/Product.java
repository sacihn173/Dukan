package com.dukan.app.Product;

import com.dukan.app.Order.Order;
import com.dukan.app.ProductStats.ProductStats;
import com.dukan.app.Tags.Tag;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    private String productName;
    private Integer price;
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Order> orders;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "productId"),
            inverseJoinColumns = @JoinColumn(name = "tag", referencedColumnName = "tag")
    )
    private Set<Tag> tags;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
