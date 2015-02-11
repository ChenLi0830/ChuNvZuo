package com.springapp.service;

import com.springapp.bean.Offer;
import com.springapp.bean.PurchasedItem;
import com.springapp.bean.User;
import com.springapp.dao.OffersDao;
import com.springapp.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 1/16/15.
 */
@Service("usersService")
public class UsersService {
    @Autowired
    private UsersDao usersDao;

    public void saveOrUpdateUser(User user) {
        usersDao.saveOrUpdate(user);
//        offersDao.saveOrUpdate(offer);
    }

    public boolean exists(String username) {
        return usersDao.exists(username);
    }

    public User getUserByName(String username){
        return usersDao.getUser(username);
    }

    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }

    public void addPurchasedItemList(List<PurchasedItem> purchasedItemList){
        usersDao.addPurchasedItemList(purchasedItemList);
    }
}
