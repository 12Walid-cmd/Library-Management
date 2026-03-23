package ca.hollandcollege.lms.entity;

import jakarta.persistence.*;

/*
 * This entity represents a system user who can log in.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Username used during login.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /*
     * Password stored in the database.
     * Your current sample data uses plain text.
     */
    @Column(nullable = false)
    private String password;

    /*
     * User full name.
     */
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /*
     * User email address.
     */
    private String email;

    /*
     * Role linked through role_id.
     */
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    /*
     * Whether the account is enabled.
     */
    @Column(nullable = false)
    private boolean enabled = true;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}