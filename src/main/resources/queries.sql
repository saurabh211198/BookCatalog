--Select
SELECT * FROM book;

--Insert
INSERT INTO book(title,author, price) VALUES ('JAVA 8','Saurabh',1000.0);

--Update
UPDATE book SET price = 2000.0 WHERE id = 1;

--Delete
DELETE FROM book where id = 2;

--INNER JOIN --Only Matching rows from both tables valid category book returned
SELECT b.title,b.author,c.name AS category_name
FROM book b
INNER JOIN category c ON
b.category_id = c.id;

--LEFT JOIN -- Return all books .if no category category name will be returned NULL
SELECT b.title,b.author,c.name AS category_name
FROM book b
LEFT JOIN category c ON
b.category_id = c.id;
