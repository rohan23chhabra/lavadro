package com.waoss.lavadro.model.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE user SET cart_id = ? WHERE user_id = ?")
    void updateCartIdByUserId(@Param("cart_id") Long cart_id, @Param("user_id") Long user_id);

    @Modifying
    @Transactional
    @Query (nativeQuery = true, value = "SELECT id from user where username = ?")
    Long findIdByUser(@Param("username") String username);
}
