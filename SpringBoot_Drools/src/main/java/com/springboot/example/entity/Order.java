package com.springboot.example.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

    private String name;
    private String cardType;
    private int discount;
    private int price;

}
