package com.futuristic.booksLelo.repository;

import com.futuristic.booksLelo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);
}
