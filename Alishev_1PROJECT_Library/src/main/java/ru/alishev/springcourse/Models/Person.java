package ru.alishev.springcourse.Models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id_person;
    @NotEmpty(message = "Name can`t be empty!")
    @Size(min = 2,max = 50, message = "Name should be between 2 and 50 characters")
    private String full_name;
    @Min( value = 1901, message = "Birth year should be more than 1900")
    @Max(value = 2022, message = "Birth year should not be more than 2022")
    private int age_birth;

    public Person() {
    }

    public Person(String full_name, int age_birth) {
        this.full_name = full_name;
        this.age_birth = age_birth;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getAge_birth() {
        return age_birth;
    }

    public void setAge_birth(int age_birth) {
        this.age_birth = age_birth;
    }
}
