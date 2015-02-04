package com.springapp.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany
    private List<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();

    public List<PurchasedItem> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<PurchasedItem> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

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

    public List<PurchasedItem> fetchEmail(){
        if (account.contains("gmail")){
            new GoogleMailFetcher().start(account,password);
            return this.purchasedItems;
        }
        return this.purchasedItems;
    }
}
