package com.company.shop.controller;

import com.company.shop.dto.BookDTO;
import com.company.shop.dto.BookMapper;
import com.company.shop.model.Book;
import com.company.shop.service.IBookService;
import com.company.shop.util.Filter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    private IBookService bookService;
    private BookMapper bookMapper;

    public BookController(IBookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        Iterable<Book> books = bookService.getAllBooks();
        List<BookDTO> bookDTOList =StreamSupport.stream(books.spliterator(), false)
                .map(bookMapper::mapModelToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(bookDTOList);
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookMapper.mapModelToDto(bookService.saveBook(bookMapper.mapDtoToModel(bookDTO)));
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable(name = "id") Long bookId, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookMapper.mapModelToDto(bookService.updateBook(bookId, bookMapper.mapDtoToModel(bookDTO)));
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") Long bookId) {
        String message = bookService.deleteBook(bookId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<BookDTO>> getByFilter(@RequestBody Filter filter,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by("title"));
        Iterable<Book> books = bookService.getByFilter(filter, paging);
        List<BookDTO> bookDTOList =StreamSupport.stream(books.spliterator(), false)
                .map(bookMapper::mapModelToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOList);
    }
}
