package com.futuristic.booksLelo.service;

import com.futuristic.booksLelo.exception.BookLeloException;
import com.futuristic.booksLelo.model.Book;

import java.util.List;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
public interface BookService {

    List<Book> listAllBooks();

    Book addABook(String name, List<String> authorsName) throws BookLeloException;
}
