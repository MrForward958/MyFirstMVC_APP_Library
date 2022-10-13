package ru.alishev.springcourse.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.Models.Book;
import ru.alishev.springcourse.Models.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> addListBook() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book where id_book=?", new Object[]{id},
                new BookMapper()).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name_book,author_book,year_release) values (?,?,?)",
        book.getName_book(),book.getAuthor_book(),book.getYear_release());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name_book=?, author_book=?, year_release=? where id_book=?",
        book.getName_book(),book.getAuthor_book(),book.getYear_release(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book where id_book=?",id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE  Book SET id_person=null where id_book=?",id);
    }


    public void assign(int id, Person person) {
        jdbcTemplate.update("UPDATE Book SET id_person=? where id_book=?",person.getId_person(),id);
    }
}
