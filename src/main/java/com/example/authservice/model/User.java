package com.example.authservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "account")
public class User {

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isDeleted = false;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="unique_id", nullable = false)
    private String uniqueId;

    @Column(name="is_deleted")
    private boolean isDeleted;

    protected User() {

    }

    @PrePersist
    private void generateUniqueId() {
        this.uniqueId = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
