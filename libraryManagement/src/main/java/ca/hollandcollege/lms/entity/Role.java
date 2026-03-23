package ca.hollandcollege.lms.entity;

import jakarta.persistence.*;

/*
 * This entity represents a user role such as ADMIN or STAFF.
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Role name stored in the database.
     * Examples: ADMIN, STAFF, CLIENT
     */
    @Column(nullable = false, unique = true)
    private String name;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}