package com.waoss.lavadro.model.transaction.cart;

import com.waoss.lavadro.model.product.Product;
import com.waoss.lavadro.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "cart")
@NoArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    public Cart(final User user, final Set<Product> products) {
        this.user = user;
        this.products = products;
        this.getUser().setCart(this);
    }
}
