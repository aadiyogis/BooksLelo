package com.futuristic.booksLelo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
@Data
public class BookRequest {

    private String bookName;
    private List<String> authors;

}
