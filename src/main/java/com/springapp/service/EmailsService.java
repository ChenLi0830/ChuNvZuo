package com.springapp.service;

import com.springapp.bean.Email;
import com.springapp.dao.EmailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Chen on 15-02-09.
 */

@Service("emailsSerive")
public class EmailsService {
    @Autowired
    private EmailsDao emailsDao;


    public void saveOrUpdate(Email email) {
        emailsDao.saveOrUpdate(email);
    }
}
