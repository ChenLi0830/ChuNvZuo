package com.springapp.dao;

import com.springapp.bean.Email;
import com.springapp.bean.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Transactional
@Component("usersDao")
public class UsersDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Session session(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void create(User user) {
        Session session = session();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session.saveOrUpdate(user);
    }

    public boolean exists(String username) {
        Criteria criteria = session().createCriteria(User.class);
        criteria.add(Restrictions.eq("username",username));
        User user = (User) criteria.uniqueResult();

        return user != null;
    }

    @SuppressWarnings(value = "unchecked")
    public List<User> getAllUsers() {
        return session().createQuery("from User").list();
    }

//    public List<Email> getEmails(String username){
//        Criteria criteria = session().createCriteria(Email.class);
//
//    }
}
