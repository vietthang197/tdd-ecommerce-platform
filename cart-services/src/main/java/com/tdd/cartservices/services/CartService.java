package com.tdd.cartservices.services;

import com.tdd.cartservices.entity.Cart;
import reactor.core.publisher.Mono;

public interface CartService {
    Mono<Cart> createCart();
}
