package com.futuristic.booksLelo.service.impl;

import com.futuristic.booksLelo.exception.BookLeloException;
import com.futuristic.booksLelo.model.Author;
import com.futuristic.booksLelo.model.Book;
import com.futuristic.booksLelo.repository.AuthorRepository;
import com.futuristic.booksLelo.repository.BookRepository;
import com.futuristic.booksLelo.service.AuthorService;
import com.futuristic.booksLelo.service.BookService;
import com.futuristic.booksLelo.util.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    @Override
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book addABook(String name, List<String> authorsName) throws BookLeloException {
        if (ValidationUtil.isValid(name) && ValidationUtil.isNonNull(authorsName) && !authorsName.isEmpty()) {

            Optional<Book> byName = bookRepository.findByName(name);
            if (byName.isPresent()) {
                return byName.get();
            } else {
                Book book = new Book();
                book.setName(name);
                Book savedBook = bookRepository.save(book);
                Set<Author> authorSet = new HashSet<>();
                try {
                    List<Author> authors = authorRepository.findAllByNameIn(authorsName);
                    authorSet.addAll(authors);
                    if (authors == null || authors.isEmpty() || authors.size() != authorsName.size()) {
                        List<Author> missingAuthors = authorService.createMissingAuthor(authors, authorsName);
                        authorSet.addAll(missingAuthors);
                    }
                    authorService.updateBookDetails(savedBook, authorSet);
                    savedBook.setAuthors(authorSet);
                } catch (BookLeloException ble) {
                    throw new BookLeloException("Author names cannot be empty/blank.");
                }

                authorRepository.saveAll(authorSet);
                Book updatedBook = bookRepository.saveAndFlush(savedBook);
                return updatedBook;
            }
        } else {
            throw new BookLeloException("Missing input parameters.");
        }
    }

}
