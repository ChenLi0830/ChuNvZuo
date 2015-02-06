package com.springapp.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User {
    @Id
    @NotBlank(message = "User name cannot be blank.")
    @Size(max = 30, min = 3, message = "Username must be between 3 and 30 characters long.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^\\S+$")
    @Size(min = 5, max = 80, message = "Password must be between 5 and 80 characters long.")
    private String password;

    @OneToMany
    private Collection<Email> emailList = new ArrayList<Email>();

    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 60, min = 3, message = "Name must be between 3 and 60 characters long.")
    private String name;
    private boolean enabled = false;
    private String authority;


    public User() {

    }

    public User(String username, String name, String password, Email email, boolean enabled,
                String authority) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
        this.authority = authority;
        addEmail(email);
    }

    public User(String username, String password, String name, boolean enabled, String authority) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.enabled = enabled;
        this.authority = authority;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(Collection<Email> emailList) {
        this.emailList = emailList;
    }

    public void addEmail(Email email) {
        this.emailList.add(email);
    }
}
