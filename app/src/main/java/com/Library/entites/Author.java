package com.Library.entites;

public class Author {
    private int idAuthor;
    private String name;
    private String surname;
    private String isoCounty;
    private int age;

    public Author(int idAuthor, String name, String surname, String isoCounty, int age) {
        this.idAuthor = idAuthor;
        this.name = name;
        this.surname = surname;
        this.isoCounty = isoCounty;
        this.age = age;
    }

    public Author(){}

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIsoCounty() {
        return isoCounty;
    }

    public void setIsoCounty(String isoCounty) {
        this.isoCounty = isoCounty;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
