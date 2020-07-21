---- drop ----
DROP TABLE IF EXISTS `books`;

---- create ----
CREATE TABLE books (
    id INT(11) AUTO_INCREMENT NOT NULL,
    isbn VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

---- insert sample data ----
INSERT INTO books(id, isbn) VALUES (1, '9784043636037');