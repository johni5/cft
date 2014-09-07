package com.del.cft.test.eis;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * User: johni5
 * Date: 04.09.14
 * Time: 17:41
 */
@Entity
@Table(name="PERSON")
public class Person {

    @Id
    @Column(name="ID")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="PATRONYMIC")
    private String patronymic;

    @Column(name="LAST_NAME")
    private String lastName;

    public Person() {
    }

    public Person(String firstName, String patronymic, String lastName) {
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void copy(Person that){
        id = that.getId();
        firstName = that.getFirstName();
        lastName = that.getLastName();
        patronymic = that.getPatronymic();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
