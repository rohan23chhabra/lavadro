package com.waoss.lavadro.api;

import com.waoss.lavadro.LavadroApplication;
import com.waoss.lavadro.model.transaction.cart.Cart;
import com.waoss.lavadro.model.transaction.cart.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuickOrderService {

    public static final Logger log = LoggerFactory.getLogger(QuickOrderService.class);
    private final CartRepository cartRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public QuickOrderService(final CartRepository cartRepository,
                             final RabbitTemplate rabbitTemplate) {
        this.cartRepository = cartRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void placeOrder(Cart cart) {
        cartRepository.save(cart);
        //String cartJson = new Gson().toJson(cart);
        //log.info(cartJson);
        rabbitTemplate.convertAndSend(LavadroApplication.EXCHANGE_NAME, LavadroApplication.ROUTING_KEY, cart);
    }


}
