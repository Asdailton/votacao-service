package br.com.adam.studyingspringboot.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="person")
public class Person extends RepresentationModel<Person> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String name;
    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name = "country", nullable = false)
    private String country;

    public Person() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getId() == person.getId() && Objects.equals(getName(), person.getName()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getCountry(), person.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastName(), getCountry());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
