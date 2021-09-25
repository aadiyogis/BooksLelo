package com.futuristic.booksLelo.bootStrap;

import com.futuristic.booksLelo.model.Author;
import com.futuristic.booksLelo.model.Book;
import com.futuristic.booksLelo.repository.AuthorRepository;
import com.futuristic.booksLelo.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
@Slf4j
@Component
@AllArgsConstructor
public class BootUp implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        log.debug("Setting initial data for book apps");

        Author kundKund = new Author();
        kundKund.setName("Acharya Kund Kund");

        Set<Author> acharya = new HashSet<>();
        acharya.add(kundKund);

        Book samayasaar = new Book();
        samayasaar.setName("Samayasaar");
        samayasaar.setIsbn("1231");
        samayasaar.setAuthors(acharya);

        Book niyamsaar = new Book();
        niyamsaar.setName("Niyamsaar");
        niyamsaar.setIsbn("1232");
        niyamsaar.setAuthors(acharya);

        Set<Book> parmaagam = new HashSet<>();
        parmaagam.add(niyamsaar);
        parmaagam.add(samayasaar);

        kundKund.setBooks(parmaagam);

        bookRepository.saveAndFlush(samayasaar);
        bookRepository.saveAndFlush(niyamsaar);

        authorRepository.save(kundKund);

        log.debug("Setup completed !!!");
    }
}
