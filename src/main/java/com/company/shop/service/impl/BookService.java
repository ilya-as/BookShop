package com.company.shop.service.impl;

import com.company.shop.exception.BookNotFoundException;
import com.company.shop.model.Book;
import com.company.shop.repository.BookRepository;

import com.company.shop.service.IBookService;
import com.company.shop.specification.BookSpecification;
import com.company.shop.util.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookService implements IBookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Book updateBook(Long bookId, Book book) throws BookNotFoundException {
        bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        book.setId(bookId);
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public String deleteBook(Long bookId) throws BookNotFoundException {
        bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        bookRepository.deleteById(bookId);
        return "Book with id: " + bookId + " deleted successfully!";
    }

    @Override
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    }

    @Override
    public Page<Book> getByFilter(Filter filter, Pageable pageable) {
        return bookRepository.findAll(
                Specification
                        .where(BookSpecification.withTitle(filter.getTitle())
                                .and(BookSpecification.afterPublicationYear(filter.getAfterPublicationYear()))
                                .and(BookSpecification.beforePublicationYear(filter.getBeforePublicationYear()))
                                .and(BookSpecification.withAuthorSurname(filter.getAuthorSurname()))
                                .and(BookSpecification.status(filter.getStatus()))
                        ), pageable);
    }
}

