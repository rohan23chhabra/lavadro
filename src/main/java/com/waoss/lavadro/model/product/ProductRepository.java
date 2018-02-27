package com.waoss.lavadro.model.product;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository<T extends Product> extends CrudRepository<T, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "SELECT * FROM product p WHERE p.id NOT IN (SELECT p2.id FROM product p2 WHERE p2.user_id = ?);")
    List<T> findNotByUser(@Param("user_id") Long userId);
}




