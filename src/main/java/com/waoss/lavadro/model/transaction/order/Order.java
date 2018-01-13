package com.waoss.lavadro.model.transaction.order;

import lombok.Data;

import javax.persistence.*;

@Deprecated
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
