package com.company.shop.dto;

import java.util.*;

public class AuthorDTO {
    private long id;
    private String name;
    private String patronymic;
    private String surname;
    private Map<Long,String> books = new HashMap<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Map<Long, String> getBooks() {
        return books;
    }

    public void setBooks(Map<Long, String> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return id == authorDTO.id &&
                Objects.equals(name, authorDTO.name) &&
                Objects.equals(patronymic, authorDTO.patronymic) &&
                Objects.equals(surname, authorDTO.surname) &&
                Objects.equals(books, authorDTO.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, patronymic, surname, books);
    }
}
