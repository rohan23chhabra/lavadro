package com.waoss.lavadro.model.user;

import com.waoss.lavadro.model.product.Product;
import com.waoss.lavadro.model.transaction.cart.Cart;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"products", "cart"})
public class User implements Serializable {

    private String username;

    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    private Cart cart;

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }


    protected User() {
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                '}';
    }
}
