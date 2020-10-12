package com.company.shop.service;

import com.company.shop.dto.AuthorDTO;
import com.company.shop.model.Author;

import java.util.List;

public interface IAuthorService {
    Author saveAuthor(Author author);

    Iterable<Author> getAllAuthors();

    Author updateAuthor(Long authorId, Author author);

    String deleteAuthor(Long authorId);

    Author findById(Long authorId);
}
