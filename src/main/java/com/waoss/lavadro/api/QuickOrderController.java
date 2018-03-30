package com.waoss.lavadro.api;


import com.waoss.lavadro.model.transaction.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class QuickOrderController {

    @Autowired
    private QuickOrderService quickOrderService;

    @RequestMapping(path = "/receive", method = RequestMethod.POST)
    public ResponseEntity<String> receiveOrder(@RequestBody Cart cart) {
        quickOrderService.placeOrder(cart);
        return new ResponseEntity<>("Order received!", HttpStatus.OK);
    }
}
