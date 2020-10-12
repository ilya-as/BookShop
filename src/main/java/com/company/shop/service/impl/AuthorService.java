package com.company.shop.service.impl;

import com.company.shop.exception.AuthorNotFoundException;
import com.company.shop.exception.BookNotFoundException;
import com.company.shop.model.Author;
import com.company.shop.model.Book;
import com.company.shop.repository.AuthorRepository;
import com.company.shop.service.IAuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthorService implements IAuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Iterable<Author> getAllAuthors() {
        Iterable<Author> authors = authorRepository.findAll();
        return authors;
    }

    @Transactional
    @Override
    public Author updateAuthor(Long authorId, Author author) {
        authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        author.setId(authorId);
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public String deleteAuthor(Long authorId) {
        authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        authorRepository.deleteById(authorId);
        return "Author with id: " + authorId + " deleted successfully!";
    }

    @Override
    public Author findById(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
    }

}
