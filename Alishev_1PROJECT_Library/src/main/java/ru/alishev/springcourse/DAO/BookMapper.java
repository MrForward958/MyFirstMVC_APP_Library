package ru.alishev.springcourse.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.alishev.springcourse.Models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setId_book(rs.getInt("id_book"));
        book.setId_person(rs.getInt("id_person"));
        book.setName_book(rs.getString("name_book"));
        book.setAuthor_book(rs.getString("author_book"));
        book.setYear_release(rs.getInt("year_release"));

        return book;
    }
}
