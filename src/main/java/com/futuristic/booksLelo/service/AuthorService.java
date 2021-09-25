package com.futuristic.booksLelo.service;

import com.futuristic.booksLelo.exception.BookLeloException;
import com.futuristic.booksLelo.model.Author;
import com.futuristic.booksLelo.model.Book;

import java.util.Collection;
import java.util.List;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
public interface AuthorService {
    List<Author> createMissingAuthor(List<Author> authors, List<String> authorsName) throws BookLeloException;
    List<Author> updateBookDetails(Book book, Collection<Author> authors);
}
