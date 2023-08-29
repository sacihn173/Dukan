package com.dukan.app.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(
            value = "select product_id from product_tags where tag in :tags",
            nativeQuery = true
    )
    List<Integer> findByTags(List<String> tags);

    @Query(
            value = "select * from product where product_id in :productIds",
            nativeQuery = true
    )
    List<Product> findByProductIds(List<Integer> productIds);
}
