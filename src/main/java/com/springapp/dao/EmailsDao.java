package com.springapp.dao;

import com.springapp.bean.Email;
import com.springapp.bean.Offer;
import com.springapp.bean.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component("emailsDao")
public class EmailsDao {

    @Autowired
    public SessionFactory sessionFactory;

    public Session session(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Email> getEmails(User user) {

        Criteria criteria = session().createCriteria(Email.class);
        criteria.createAlias("user","u");
        criteria.add(Restrictions.eq("u.enabled",true));
        criteria.add(Restrictions.eq("u",user));

        return criteria.list();
    }

//    @SuppressWarnings("unchecked")
//    public List<Offer> getOffers(String username) {
//        Criteria criteria = session().createCriteria(Offer.class);
//        criteria.createAlias("user","u");
//        criteria.add(Restrictions.eq("u.enabled",true));
//        criteria.add(Restrictions.eq("u.username",username));
//
//        return criteria.list();
//    }


    public void saveOrUpdate(Email email) {
        session().saveOrUpdate(email);
    }

    public boolean delete(int id) {
        Query query = session().createQuery("delete Email where id = :id");
        query.setInteger("id",id);
        return query.executeUpdate()==1;//executeUpdate returns numbers of rows effected.
    }

    public Email getEmail(String account) {
        Criteria criteria = session().createCriteria(Email.class);
//        criteria.createAlias("user","u");
//        criteria.add(Restrictions.eq("u.enabled",true));
        criteria.add(Restrictions.eq("account",account));

        return (Email) criteria.uniqueResult();
    }
}
