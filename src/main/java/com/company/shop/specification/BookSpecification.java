package com.company.shop.specification;

import com.company.shop.model.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

public class BookSpecification {
    public static Specification<Book> withAuthorSurname(String surname) {
        return (book, query, builder) -> surname == null ? null : builder.equal(book.joinSet("authors", JoinType.INNER).get("surname"), surname);
    }

    public static Specification<Book> withTitle(String title) {
        return (book, query, builder) -> title == null ? null : builder.equal(book.get("title"), title);
    }

    public static Specification<Book> afterPublicationYear(final Integer publicationYear) {
      return (book, query, builder) -> publicationYear == null ? null : builder.ge(book.get("publicationYear"), publicationYear);
    }

    public static Specification<Book> beforePublicationYear(final Integer publicationYear) {
        return (book, query, builder) -> publicationYear == null ? null : builder.le(book.get("publicationYear"), publicationYear);
    }

    public static Specification<Book> status(final Boolean status) {
        return (book, query, builder) -> (status == null || !status) ? builder.le(book.get("quantity"), 0)
                : builder.ge(book.get("quantity"), 1);
    }
}
