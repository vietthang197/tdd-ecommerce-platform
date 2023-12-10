package com.tdd.core.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {


    @PreAuthorize("@keycloakAuthzService.isGrant(#httpServletRequest, #httpServletResponse)")
    @GetMapping
    public String test() {
        return "OK";
    }
}
