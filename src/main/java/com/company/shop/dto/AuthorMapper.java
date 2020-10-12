package com.company.shop.dto;

import com.company.shop.model.Author;
import com.company.shop.service.impl.BookService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class AuthorMapper {

    private BookService bookService;

    public AuthorMapper(BookService bookService) {
        this.bookService = bookService;
    }

    public Author mapDtoToModel(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setPatronymic(authorDTO.getPatronymic());
        author.setSurname(authorDTO.getSurname());
        author.setBooks(authorDTO.getBooks().keySet().stream()
                .map(id -> bookService.findById(id)).collect(Collectors.toSet()));
        return author;
    }

    public AuthorDTO mapModelToDto(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setPatronymic(author.getPatronymic());
        authorDTO.setSurname(author.getSurname());
        Map<Long, String> books = new HashMap<>();
        author.getBooks().stream().forEach(book -> books.put(book.getId(), book.getTitle()));
        authorDTO.setBooks(books);
        return authorDTO;
    }
}