CREATE TABLE IF NOT EXISTS book (
   id serial primary key,
   title text NOT NULL,
   publicationYear integer,
   quantity integer
);

CREATE TABLE IF NOT EXISTS author (
   id serial primary key,
   name text NOT NULL,
   patronymic text NOT NULL,
   surname text NOT NULL
);

CREATE TABLE IF NOT EXISTS book_author (
   book_id integer NOT NULL,
   author_id integer NOT NULL,
   PRIMARY KEY (book_id, author_id),
   CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES book (id),
   CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES author (id)
)