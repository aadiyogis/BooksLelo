package com.futuristic.booksLelo.controller;

import com.futuristic.booksLelo.dto.BookRequest;
import com.futuristic.booksLelo.exception.BookLeloException;
import com.futuristic.booksLelo.model.Book;
import com.futuristic.booksLelo.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
@Slf4j
@Controller
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("BookController -- getAllBooks -- start");
        List<Book> books = bookService.listAllBooks();
        ResponseEntity responseEntity = new ResponseEntity(books, HttpStatus.OK);
        log.info("BookController -- getAllBooks -- end");
        return responseEntity;
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        log.info("BookController -- createBook -- start, {}", bookRequest);
        ResponseEntity<Book> responseEntity;
        try {
            Book aBook = bookService.addABook(bookRequest.getBookName(), bookRequest.getAuthors());
            responseEntity = new ResponseEntity(aBook, HttpStatus.OK);
            log.info("Created book successfully");
        } catch (BookLeloException ble) {
            log.error("Exception occured while creating book object");
            responseEntity = new ResponseEntity(ble.getMessage(), HttpStatus.BAD_REQUEST);
        }
        log.info("BookController -- createBook -- end");
        return responseEntity;
    }
}