package com.engineer.inzynier.entities;


import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Entity(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String username;
    private Date created;
    private Role userRole;
    private String uid;
    private String password;

    public User(@Nullable Long id, @Nullable String username, @Nullable String email, @Nullable Date created, @Nullable Role userRole, @Nullable String password, @Nullable String uid) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.created = created;
        this.userRole = userRole;
        this.password = password;
        this.uid = uid;
    }

    public User() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    @Nullable
    public Date getCreated() {
        return created;
    }

    public void setCreated(@Nullable Date created) {
        if (created != null) {
            this.created = created;
        } else {
            this.created = new Date();
        }

    }

    @Nullable
    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(@Nullable Role userRole) {
        this.userRole = userRole;
    }

    @Nullable
    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        if (uid != null) {
            this.uid = uid;
        } else {
            this.uid = UUID.randomUUID().toString();
        }
    }

    @Nullable
    public String getPassword() {
        return this.password;
    }

    public void setPassword(@Nullable String password) {
        if (password != null) {
            if (password.length() > 64) {
                this.password = password;
            } else {
                this.password = (new BCryptPasswordEncoder()).encode(password);
            }
        }

    }
}
