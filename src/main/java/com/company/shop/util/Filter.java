package com.company.shop.util;


public class Filter {
    String title;
    Integer afterPublicationYear;
    Integer beforePublicationYear;
    String authorSurname;
    Boolean status;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAfterPublicationYear() {
        return afterPublicationYear;
    }

    public void setAfterPublicationYear(Integer afterPublicationYear) {
        this.afterPublicationYear = afterPublicationYear;
    }

    public Integer getBeforePublicationYear() {
        return beforePublicationYear;
    }

    public void setBeforePublicationYear(Integer beforePublicationYear) {
        this.beforePublicationYear = beforePublicationYear;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
