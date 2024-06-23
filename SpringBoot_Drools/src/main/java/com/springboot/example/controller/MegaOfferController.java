package com.springboot.example.controller;

import com.springboot.example.entity.Order;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mega-offer")
public class MegaOfferController {

    @Autowired
    private KieSession kieSession;

    @PostMapping("/order")
    public Order orderNow(@RequestBody Order order) {
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        return order;
    }
}
