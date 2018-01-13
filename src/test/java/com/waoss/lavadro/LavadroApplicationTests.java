package com.waoss.lavadro;

import com.waoss.lavadro.model.product.*;
import com.waoss.lavadro.model.transaction.cart.Cart;
import com.waoss.lavadro.model.transaction.cart.CartRepository;
import com.waoss.lavadro.model.user.User;
import com.waoss.lavadro.model.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = LavadroApplication.class)
public class LavadroApplicationTests {

    @Autowired
    private ProductRepository<? extends Product> productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    private org.slf4j.Logger log = LoggerFactory.getLogger(LavadroApplicationTests.class);

    @Test
    public void retrieveProducts() {
        saveBook();
        productRepository.findAll().forEach(p -> log.info(p.toString()));
    }

    @Test
    public void saveBook() {
        Set<Product> products = new HashSet<>();
        User user1 = new User("rohan", "rohan");
        products.add(new Book("b1", 125L, user1, "a1", 400, "math"));
        products.add(new Cycle("c1", 2600L, user1, "gear", "atlas"));
        Cart cart = new Cart(user1, products);
        cartRepository.save(cart);
    }

}
