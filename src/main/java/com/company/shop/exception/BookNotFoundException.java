package com.company.shop.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super("Book with id=" + id + " not found");
    }

    public BookNotFoundException(String title) {
        super("Book with title=" + title + " not found");
    }
}
