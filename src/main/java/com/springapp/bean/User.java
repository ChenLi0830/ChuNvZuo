package com.springapp.bean;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

@Entity
//@Table(name = "users")
public class User {
    @Id
    @NotBlank(message = "User name cannot be blank.")
    @Size(max = 30, min = 3, message = "Username must be between 3 and 30 characters long.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^\\S+$")
    @Size(min = 5, max = 80, message = "Password must be between 5 and 80 characters long.")
    private String password;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(name="users_emails", joinColumns=@JoinColumn(name="user_name"),inverseJoinColumns=@JoinColumn(name="email_account"))
    private Collection<Email> emailList = new ArrayList<Email>();

    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 60, min = 3, message = "Name must be between 3 and 60 characters long.")
    private String name;
    private boolean enabled = false;
    private String authority;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(name="users_purchasedItems", joinColumns=@JoinColumn(name="user_name"),inverseJoinColumns=@JoinColumn(name="purchasedItem_id"))
    private Collection<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();

    public Collection<PurchasedItem> getPurchasedItems() {
        return purchasedItems;
    }

    public void addPurchasedItems(Collection<PurchasedItem> purchasedItems) {
        this.purchasedItems.addAll(purchasedItems);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (enabled != user.enabled) return false;
        if (authority != null ? !authority.equals(user.authority) : user.authority != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (emailList != null ? emailList.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }
}
