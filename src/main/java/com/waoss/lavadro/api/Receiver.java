package com.waoss.lavadro.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waoss.lavadro.model.transaction.cart.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Receiver {
    private static final Logger log = LoggerFactory.getLogger(Receiver.class);
    @Qualifier("objectMapper")
    @Autowired
    private ObjectMapper objectMapper;


    public void receiveMessage(final Cart cart) {
        //Gson gson = new Gson();
        //Cart cart = gson.fromJson(message, Cart.class);
        log.info(cart.toString());
    }
}
