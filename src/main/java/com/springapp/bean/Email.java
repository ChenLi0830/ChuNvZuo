package com.springapp.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Chen on 15-01-28.
 */
@Entity
//@Table(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<PurchasedItem> fetchPurchasedItem(){
        if (account.contains("gmail")){
            return new GoogleMailFetcher().start(account, password);
        } else {
            //todo Make fetchEmail applicable to other email service, too.
            System.out.println("Not gmail account!");
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;

        Email email = (Email) o;

        if (account != null ? !account.equals(email.account) : email.account != null) return false;
        if (password != null ? !password.equals(email.password) : email.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = account != null ? account.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
