package com.springapp.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Chen on 15-01-28.
 */
@Entity
public class Email {
    @Id
    @NotBlank
    @org.hibernate.validator.constraints.Email
    private String account;
    @NotBlank
    private String password;

    public Email(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Email() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Email{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
