package com.dukan.app.ProductStats;

import com.dukan.app.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatsRepository extends JpaRepository<ProductStats, Integer> {
    ProductStats findByProductId(Integer productId);
}
