package com.dukan.app.CommonProductStats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommonProductStatsRepository extends JpaRepository<CommonProductStats, Integer> {

    @Query(
            value = "SELECT * FROM common_product_stats WHERE product1id = :product1Id and product2id = :product2Id",
            nativeQuery = true
    )
    Optional<CommonProductStats> findByProductIds(int product1Id, int product2Id);

}
