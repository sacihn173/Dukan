package com.dukan.app.Tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

    @Query(
            value = "select tag from product_tags where product_id in :productIds group by tag",
            nativeQuery = true
    )
    List<String> getProductTags(List<Integer> productIds);
}
