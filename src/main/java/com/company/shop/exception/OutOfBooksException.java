package com.company.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OutOfBooksException extends RuntimeException {
    public OutOfBooksException(Long id) {
        super("Book with id=" + id + " is run out");
    }

    public OutOfBooksException(String title) {
        super("Book with title=" + title + " is run out");
    }
}
