package com.Library.entites;

public class Book {
    private int idBook;
    private String title;
    private int idAuthor;
    private String isbn;
    private int yearPublication;
    private int review;
    private int numSheets;

    public Book() {};

    public Book(int idBook, String title, int idAuthor, String isbn, int yearPublication, int review, int numSheets) {
        this.idBook = idBook;
        this.title = title;
        this.idAuthor = idAuthor;
        this.isbn = isbn;
        this.yearPublication = yearPublication;
        this.review = review;
        this.numSheets = numSheets;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getNumSheets() {
        return numSheets;
    }

    public void setNumSheets(int numSheets) {
        this.numSheets = numSheets;
    }
}
