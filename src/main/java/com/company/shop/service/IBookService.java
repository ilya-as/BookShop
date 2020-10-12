package com.company.shop.service;

import com.company.shop.model.Book;
import com.company.shop.util.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IBookService {
    Book saveBook(Book book);

    Iterable<Book> getAllBooks();

    Book updateBook(Long bookId, Book book);

    String deleteBook(Long bookId);

    Book findById(Long bookId);

    Page<Book> getByFilter(Filter filter, Pageable pageable);
}
