package com.tdd.cartservices.services.impl;

import com.tdd.cartservices.entity.Cart;
import com.tdd.cartservices.repository.CartRepository;
import com.tdd.cartservices.services.CartService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @Override
    public Mono<Cart> createCart() {
        return readFile().flatMap(this::insertToDb);

    }

    private Mono<String> readFile() {
        return  Mono.fromCallable(() -> {
            System.out.println("Current Thread name: " + Thread.currentThread().getName() + " is Virtual: " + Thread.currentThread().isVirtual());
            try (BufferedInputStream rd = new BufferedInputStream(Files.newInputStream(Path.of("D:/Downloads/Compressed/Windows.iso")), 8192);
                 BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("D:/WindowsCopy.iso"))) {
                byte[] byteBuffer = new byte[8192];
                while (rd.read(byteBuffer, 0, byteBuffer.length) != -1) {
                    outputStream.write(byteBuffer);
                    outputStream.flush();
                }
            }
            return "OK";
        }).publishOn(Schedulers.boundedElastic());
    }

    private Mono<Cart> insertToDb(String fileName) {
        return Mono.fromSupplier(() -> Cart.builder()
                .name(fileName)
                .fullName(Thread.currentThread().getName())
                .build()).flatMap(cartRepository::save);
    }
}
