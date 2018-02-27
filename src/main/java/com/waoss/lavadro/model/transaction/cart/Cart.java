package com.waoss.lavadro.model.transaction.cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.waoss.lavadro.model.product.Product;
import com.waoss.lavadro.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "cart")
@NoArgsConstructor
@ToString
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
