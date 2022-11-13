In this project, I implemented the MVC web application for the library.
I have used jdbcTemplate to communicate with the database.
Functional:
1) Pages for adding, changing and deleting a person.
2) Pages for adding, editing and deleting a book
3) A page with a list of all people (people are clickable - when you click, you go to the person's page).
4) A page with a list of all books (books are clickable - when clicked,
go to the page of the book).
5) The person's page, which shows the values ​​of his fields and the list of books that he
took. If the person did not take any books, instead of the list should be the text "Man
I haven't picked up a single book yet."
6) A page of a book, which shows the values ​​of the fields of this book and the name of the person,
who took this book. If this book has not been taken by anyone, the text "This
the book is free.
7) On the page of the book, if the book is taken by a person, next to his name there should be a button
"Release book". This button is pressed by the librarian when the reader
returns this book back to the library. After pressing this button the book again
becomes loose and disappears from the list of the person's books.
8) On the page of the book, if the book is free, there should be a drop-down list (<select>)
with all people and the "Assign Book" button. This button is pressed by the librarian
when the reader wants to take this book home. After pressing this button, the book
must begin to belong to the selected person and must appear in his list
books.