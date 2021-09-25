package com.futuristic.booksLelo.repository;

import com.futuristic.booksLelo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAllByNameIn(List<String> names);
}
