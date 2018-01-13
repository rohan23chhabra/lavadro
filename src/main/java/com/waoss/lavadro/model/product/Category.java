package com.waoss.lavadro.model.product;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Deprecated
public class Category implements Serializable {
    private String name;

    public Category(final String name) {
        this.name = name;
    }

    protected Category() {
    }
}
