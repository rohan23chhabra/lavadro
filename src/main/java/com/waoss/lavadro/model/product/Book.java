package com.waoss.lavadro.model.product;

import com.waoss.lavadro.model.user.User;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Book extends Product implements Serializable {

    private String author;

    private Integer pages;

    private String subject;

    public Book(final String name, final Long price, final User user, final String author, final Integer pages,
                final String subject) {
        super(name, price, user);
        this.author = author;
        this.pages = pages;
        this.subject = subject;
    }
}
