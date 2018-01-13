package com.waoss.lavadro.model.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository<T extends Product> extends CrudRepository<T, Long> {
}
