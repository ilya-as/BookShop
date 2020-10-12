package com.company.shop.service.impl;

import com.company.shop.exception.BookNotFoundException;
import com.company.shop.exception.OutOfBooksException;
import com.company.shop.model.Book;
import com.company.shop.repository.BookRepository;
import com.company.shop.service.IShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopService implements IShopService {

    private BookRepository bookRepository;

    public ShopService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(rollbackFor = OutOfBooksException.class)
    @Override
    public String saleBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        Integer expectedQuantity = book.getQuantity() - 1;
        if (expectedQuantity < 0) {
            throw new OutOfBooksException(bookId);
        }
        book.setQuantity(expectedQuantity);
        bookRepository.save(book);
        return "Book with id: " + bookId + " sale successfully!";
    }
}
