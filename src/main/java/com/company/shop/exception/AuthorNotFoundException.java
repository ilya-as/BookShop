package com.company.shop.exception;

import com.company.shop.model.Author;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super("Author with id=" + id + " not found");
    }

    public AuthorNotFoundException(Author author) {
        super("Author with name=" + author.getName() + " patronymic= " + author.getPatronymic() + " surname= " +
                author.getSurname() + " not found");
    }
}
