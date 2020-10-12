package com.company.shop.repository;

import com.company.shop.exception.AuthorNotFoundException;
import com.company.shop.model.Author;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long>, JpaSpecificationExecutor<Author> {
    Optional<Author> findByNameAndPatronymicAndSurname(String name, String patronymic, String surname) ;
}
