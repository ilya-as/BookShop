package com.company.shop.dto;

import java.util.*;

public class BookDTO {
    private long id;
    private String title;
    private Integer publicationYear;
    private Integer quantity;
    private Map<Long,String> authors = new HashMap<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Map<Long, String> getAuthors() {
        return authors;
    }

    public void setAuthors(Map<Long, String> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return id == bookDTO.id &&
                Objects.equals(title, bookDTO.title) &&
                Objects.equals(publicationYear, bookDTO.publicationYear) &&
                Objects.equals(quantity, bookDTO.quantity) &&
                Objects.equals(authors, bookDTO.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, publicationYear, quantity, authors);
    }
}
