package ru.alishev.springcourse.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.Models.Book;
import ru.alishev.springcourse.Models.Person;


import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> addListPerson() {
        return jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person where id_person=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Person show (String name){
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?",
                new Object[]{name}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (full_name,age_birth) VALUES (?,?)",
                person.getFull_name(),person.getAge_birth());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, age_birth=? WHERE id_person=?",
                person.getFull_name(),person.getAge_birth(),id);
    }


    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id_person=?",id);
    }

    public List<Book> getBookByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id_person=?",
                new Object[]{id},new BookMapper());
    }

}
