package com.company.shop.dto;

import com.company.shop.exception.AuthorNotFoundException;
import com.company.shop.model.Book;
import com.company.shop.service.impl.AuthorService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private AuthorService authorService;

    public BookMapper(AuthorService authorService) {
        this.authorService = authorService;
    }

    public Book mapDtoToModel(BookDTO bookDTO) throws AuthorNotFoundException {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setQuantity(bookDTO.getQuantity());
        book.setAuthors(bookDTO.getAuthors().keySet().stream()
                .map(id -> authorService.findById(id)).collect(Collectors.toSet()));
        return book;
    }

    public BookDTO mapModelToDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        Map<Long, String> authors = new HashMap<>();
        book.getAuthors().stream().forEach(author -> authors.put(author.getId(), author.getName() + " "
                + author.getPatronymic() + " " + author.getSurname()));
        bookDTO.setAuthors(authors);
        bookDTO.setPublicationYear(book.getPublicationYear());
        bookDTO.setQuantity(book.getQuantity());
        return bookDTO;
    }
}
