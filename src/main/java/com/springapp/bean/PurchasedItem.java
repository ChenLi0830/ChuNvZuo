package com.springapp.bean;

import javax.persistence.*;

/**
 * Created by Chen on 15-02-03.
 */
@Entity
//@Table(name = "purchasedItems")
public class PurchasedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String itemName;
    private String itemURL;

    @Override
    public String toString() {
        return "PurchasedItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemURL='" + itemURL + '\'' +
                ", itemPicURL='" + itemPicURL + '\'' +
                '}';
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchasedItem)) return false;

        PurchasedItem that = (PurchasedItem) o;

        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (itemPicURL != null ? !itemPicURL.equals(that.itemPicURL) : that.itemPicURL != null) return false;
        if (itemURL != null ? !itemURL.equals(that.itemURL) : that.itemURL != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemName != null ? itemName.hashCode() : 0;
        result = 31 * result + (itemURL != null ? itemURL.hashCode() : 0);
        result = 31 * result + (itemPicURL != null ? itemPicURL.hashCode() : 0);
        return result;
    }
}
