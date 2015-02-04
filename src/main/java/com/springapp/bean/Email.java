package com.springapp.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
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
    private Collection<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();

    public Collection<PurchasedItem> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(Collection<PurchasedItem> purchasedItems) {
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
            return new GoogleMailFetcher().start(account, password);
        } else {
            //todo Make fetchEmail applicable to other email service, too.
            System.out.println("Not gmail account!");
            return null;
        }
    }
}
