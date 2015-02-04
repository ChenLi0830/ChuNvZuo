package com.springapp.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Chen on 15-02-03.
 */
@Entity
public class PurchasedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String itemName;
    private String itemURL;
    private String itemPicURL;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public String getItemPicURL() {
        return itemPicURL;
    }

    public void setItemPicURL(String itemPicURL) {
        this.itemPicURL = itemPicURL;
    }
}
