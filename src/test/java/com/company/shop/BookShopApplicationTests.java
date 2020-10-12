package com.company.shop;

import com.company.shop.exception.AuthorNotFoundException;
import com.company.shop.exception.BookNotFoundException;
import com.company.shop.exception.OutOfBooksException;
import com.company.shop.model.Author;
import com.company.shop.model.Book;
import com.company.shop.service.impl.AuthorService;
import com.company.shop.service.impl.BookService;
import com.company.shop.service.impl.ShopService;
import com.company.shop.util.Filter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookShopApplicationTests {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ShopService shopService;

    private Author author;
    private Author author2;
    private Book book;

    @Before
    public void before() {
        author = new Author();
        author.setName("Aleksander");
        author.setPatronymic("Sergeevich");
        author.setSurname("Pushkin");

        author2 = new Author();
        author2.setName("author2");
        author2.setPatronymic("author2");
        author2.setSurname("author2");

        book = new Book();
        book.setTitle("Eugene Onegin");
        book.setPublicationYear(1823);
        book.setQuantity(1);
    }

    @Test(expected = AuthorNotFoundException.class)
    @Transactional
    public void testAuthorService() {
        authorService.saveAuthor(author);
        Author savedAuthor = authorService.findById(author.getId());
        assertNotNull(savedAuthor);
        assertEquals(author, savedAuthor);
        authorService.deleteAuthor(author.getId());
        assertNull(authorService.findById(author.getId()));
    }

    @Test(expected = BookNotFoundException.class)
    @Transactional
    public void testBookService() {
        authorService.saveAuthor(author);
        book.getAuthors().add(author);
        bookService.saveBook(book);
        Book savedBook = bookService.findById(book.getId());
        assertNotNull(savedBook);
        assertEquals(book, savedBook);
        bookService.deleteBook(book.getId());
        Author savedAuthor = authorService.findById(author.getId());
        assertNotNull(savedAuthor);
        authorService.deleteAuthor(author.getId());
        assertNull(bookService.findById(book.getId()));
    }

    @Test(expected = OutOfBooksException.class)
    @Transactional()
    public void testSale() {
        bookService.saveBook(book);
        shopService.saleBook(book.getId());
        shopService.saleBook(book.getId());
    }

    @Test()
    @Transactional()
    public void testFilter() {

        Book book1 = new Book();
        book1.setTitle("Book1");
        book1.setPublicationYear(1800);
        book1.setQuantity(0);
        book1.getAuthors().add(author);


        Book book2 = new Book();
        book2.setTitle("Book2");
        book2.setPublicationYear(1900);
        book2.setQuantity(2);
        book2.getAuthors().add(author2);

        bookService.saveBook(book1);
        bookService.saveBook(book2);

        Pageable page =
                PageRequest.of(0, 5);

        Filter filterAuthor= new Filter();
        filterAuthor.setAuthorSurname("Pushkin");
        Page pageAuthor = bookService.getByFilter(filterAuthor,page);
        assertEquals(book1, pageAuthor.getContent().get(0));

        Filter filterTitle= new Filter();
        filterTitle.setTitle("Book1");
        Page pageTitle = bookService.getByFilter(filterTitle,page);
        assertEquals(book1, pageTitle.getContent().get(0));

        Filter filterStatusTrue= new Filter();
        filterStatusTrue.setStatus(true);
        Page pageStatusTrue = bookService.getByFilter(filterStatusTrue,page);
        assertEquals(book2, pageStatusTrue.getContent().get(0));

        Filter filterStatusFalse= new Filter();
        filterStatusFalse.setStatus(false);
        Page pageStatusFalse = bookService.getByFilter(filterStatusFalse,page);
        assertEquals(book1, pageStatusFalse.getContent().get(0));

        Filter filterBeforeYear= new Filter();
        filterBeforeYear.setBeforePublicationYear(1850);
        Page pageBeforeYear = bookService.getByFilter(filterBeforeYear,page);
        assertEquals(book1, pageBeforeYear.getContent().get(0));

        Filter filterAfterYear= new Filter();
        filterAfterYear.setAfterPublicationYear(1850);
        filterAfterYear.setStatus(true);
        Page pageAfterYear = bookService.getByFilter(filterAfterYear,page);
        assertEquals(book2, pageAfterYear.getContent().get(0));
    }

}
