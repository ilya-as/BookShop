package com.company.shop.controller;

import com.company.shop.dto.BookMapper;
import com.company.shop.service.impl.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    private ShopService shopService;

    public ShopController(ShopService shopService, BookMapper bookMapper) {
        this.shopService = shopService;
    }

    @PutMapping("/sale/{id}")
    public ResponseEntity<String> saleBook(@PathVariable(name = "id") Long bookId) {
        String message = shopService.saleBook(bookId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
