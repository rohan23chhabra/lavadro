package com.waoss.lavadro.model.transaction.cart;

import com.waoss.lavadro.model.product.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO cart_products(cart_id, products_id) VALUES (?, ?)")
    void insertIntoCartProducts(@Param("cart_id") Long cart_id, @Param("products_id") Long product_id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE cart SET user_id = ? WHERE id = ?")
    void updateUserIdByCartId(@Param("user_id") Long user_id, @Param("id") Long cart_id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE id IN " +
            "(SELECT products_id FROM cart_products WHERE cart_id = ?)")
    List<Product> getCartProductsByCartId(@Param("cart_id") Long cart_id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM cart_products WHERE cart_id = ?")
    void deleteProductFromCart(@Param("cart_id") Long cart_id);
}
