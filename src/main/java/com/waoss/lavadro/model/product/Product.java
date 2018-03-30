package com.waoss.lavadro.model.product;

import com.waoss.lavadro.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private Long price;

    private LocalDateTime uploadTime;

    //@ElementCollection(targetClass = Category.class, fetch = FetchType.LAZY)
    //private List<Category> categories = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    protected Product(final String name, final Long price, final User user) {
        this.name = name;
        this.price = price;
        this.user = user;
        this.getUser().getProducts().add(this);
    }

    protected Product() {
    }
}
