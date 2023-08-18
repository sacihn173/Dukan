package com.dukan.app.Tags;

import com.dukan.app.Product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    private String tag;
    @ManyToMany(mappedBy = "tags")
    @JsonBackReference
    private Set<Product> products;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
