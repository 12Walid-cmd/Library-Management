package ca.hollandcollege.lms.entity;

import jakarta.persistence.*;

/*
 * This entity represents the clients table in the database.
 * A client is a library user/member who can borrow books.
 */
@Entity
@Table(name = "clients")
public class Client {

    // Primary key for the client table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Client first name
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    // Client last name
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    // Client email
    @Column(length = 100)
    private String email;

    // Client phone number
    @Column(length = 20)
    private String phone;

    // Client city
    @Column(length = 100)
    private String city;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    // Needed for edit forms
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}