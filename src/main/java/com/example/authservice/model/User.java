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
    public User(String username, String password, String email, String address, String companyName, String fulname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.companyName = companyName;
        this.fulname = fulname;
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

    @Column(name="email")
    public String email;

    @Column(name="address")
    public String address;

    @Column(name="company_name")
    public String companyName;

    @Column(name="fulname")
    public String fulname;

    @Column(name="account_type")
    public String accountType;
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
