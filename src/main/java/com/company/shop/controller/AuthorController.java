package com.company.shop.controller;

import com.company.shop.dto.AuthorDTO;
import com.company.shop.dto.AuthorMapper;
import com.company.shop.dto.BookDTO;
import com.company.shop.model.Author;
import com.company.shop.service.IAuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {
    private IAuthorService authorService;
    private AuthorMapper authorMapper;

    public AuthorController(IAuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        Iterable<Author> authors = authorService.getAllAuthors();
        List<AuthorDTO> authorDTOList = StreamSupport.stream(authors.spliterator(), false)
                .map(authorMapper::mapModelToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOList);
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO createdAuthor = authorMapper.mapModelToDto(authorService.saveAuthor(authorMapper.mapDtoToModel(authorDTO)));
        return ResponseEntity.ok(createdAuthor);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable(name = "id") Long authorId,
                                                  @RequestBody AuthorDTO authorDTO) {
        AuthorDTO createdAuthor = authorMapper.mapModelToDto(authorService.updateAuthor(authorId, authorMapper.mapDtoToModel(authorDTO)));
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable(name = "id") Long authorId) {
        String message = authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
