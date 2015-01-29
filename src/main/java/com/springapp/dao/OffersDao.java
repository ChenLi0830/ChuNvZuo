package com.springapp.dao;

import com.springapp.bean.Offer;
import com.springapp.bean.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Component("offersDao")
public class OffersDao {

    @Autowired
    public SessionFactory sessionFactory;

    public Session session(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Offer> getOffers() {

        Criteria criteria = session().createCriteria(Offer.class);
        criteria.createAlias("user","u");
        criteria.add(Restrictions.eq("u.enabled",true));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<Offer> getOffers(String username) {
        Criteria criteria = session().createCriteria(Offer.class);
        criteria.createAlias("user","u");
        criteria.add(Restrictions.eq("u.enabled",true));
        criteria.add(Restrictions.eq("u.username",username));

        return criteria.list();
    }


    public void saveOrUpdate(Offer offer) {
        session().saveOrUpdate(offer);
    }

    @Transactional
    public void create(List<Offer> offers) {

        for ( int i=0; i<offers.size(); i++ ) {
            session().save(offers.get(i));
            if ( i % 20 == 0 ) { //20, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session().flush();
                session().clear();
            }
        }
    }

    public boolean delete(int id) {
        Query query = session().createQuery("delete Offer where id = :id");
        query.setInteger("id",id);
        return query.executeUpdate()==1;//executeUpdate returns numbers of rows effected.
    }

    public Offer getOffer(int id) {
        Criteria criteria = session().createCriteria(Offer.class);
        criteria.createAlias("user","u");
        criteria.add(Restrictions.eq("u.enabled",true));
        criteria.add(Restrictions.eq("id",id));

        return (Offer) criteria.uniqueResult();
    }

}
