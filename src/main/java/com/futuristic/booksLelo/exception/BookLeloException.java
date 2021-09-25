package com.futuristic.booksLelo.exception;

/**
 * @author : Aditya
 * @since : 25-09-2021, Sat
 **/
public class BookLeloException extends Exception {

    private String message;
    private String errorCode;

    public BookLeloException(String message, String errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }

    public BookLeloException(String message) {
        super(message);
        this.message = message;
    }
}
