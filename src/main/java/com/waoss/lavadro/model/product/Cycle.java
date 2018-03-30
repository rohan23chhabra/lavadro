package com.waoss.lavadro.model.product;

import com.waoss.lavadro.model.user.User;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Cycle extends Product implements Serializable {

    private String model;

    private String company;

    public Cycle(final String name, final Long price, final User user, final String model,
                 final String company) {
        super(name, price, user);
        this.model = model;
        this.company = company;
    }
}
