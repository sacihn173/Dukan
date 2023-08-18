package com.dukan.app.Order;

import com.dukan.app.Product.Product;
import com.dukan.app.User.User;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER) /*Merge lets only association with existing users*/
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "productId")
    )
    private Set<Product> products;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
