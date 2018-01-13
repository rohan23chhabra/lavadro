package com.waoss.lavadro.model.transaction;

import com.waoss.lavadro.model.product.Product;
import com.waoss.lavadro.model.user.User;
import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

@Data
@Deprecated
public abstract class TransactionalEntity {
    @ElementCollection(targetClass = Product.class)
    private List<Product> products = new ArrayList<>();


    private User user;
}
