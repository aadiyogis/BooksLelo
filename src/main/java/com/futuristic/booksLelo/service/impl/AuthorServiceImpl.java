package com.futuristic.booksLelo.service.impl;

import com.futuristic.booksLelo.exception.BookLeloException;
import com.futuristic.booksLelo.model.Author;
import com.futuristic.booksLelo.model.Book;
import com.futuristic.booksLelo.repository.AuthorRepository;
import com.futuristic.booksLelo.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
@Slf4j
@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> createMissingAuthor(List<Author> authors, List<String> authorsName) throws BookLeloException {
        List<Author> authorList = new ArrayList<>();
        for (String authorName : authorsName) {
            boolean isFound = false;
            for (Author author : authors) {
                if (!isFound && author.getName().equalsIgnoreCase(authorName)) {
                    isFound = true;
                } else {
                    break;
                }
            }
            if (!isFound) {
                try {
                    Author author = createAuthorWithoutBook(authorName);
                    authorList.add(author);
                } catch (BookLeloException ble) {
                    throw new BookLeloException("Invalid name is present. Name cannot be null or empty");
                }
            }
        }
        return authorList;
    }

    @Override
    public List<Author> updateBookDetails(Book book, Collection<Author> authors) {
        List<Author> updatedAuthors = new ArrayList<>();
        for (Author author : authors) {
            Set<Book> books = author.getBooks();
            books.add(book);
            author.setBooks(books);

            Author updateAuthor = authorRepository.save(author);
            updatedAuthors.add(updateAuthor);
        }
        return updatedAuthors;
    }

    public Author createAuthorWithoutBook(String name) throws BookLeloException {
        if (name != null && !name.isEmpty()) {
            Author author = new Author();
            author.setName(name);

            return authorRepository.save(author);
        } else {
            throw new BookLeloException("Invalid input : Author name is empty");
        }
    }
}
