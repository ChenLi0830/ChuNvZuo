package com.springapp.test.tests;

import com.springapp.bean.Email;
import com.springapp.bean.Offer;
import com.springapp.bean.PurchasedItem;
import com.springapp.bean.User;
import com.springapp.dao.EmailsDao;
import com.springapp.dao.OffersDao;
import com.springapp.dao.UsersDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:com/main/java/com/springapp/configs/dao-context.xml",
        "classpath:com/main/java/com/springapp/configs/security-context.xml",
        "classpath:com/test/java/com/springapp/test/config/datasource.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
public class EmailDaoTests {

    @Autowired
    private EmailsDao emailsDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource dataSource;

    private Email email1 = new Email("lulugeo.li@gmail.com","Wangwei19820510");
    private Email emailUpdate1 = new Email("lulugeo.li@gmail.com","abc123");
    private Email email2 = new Email("yichen.li0830@gmail.com","asdfjkl;");
    private Email email3 = new Email("chen.li@mun.ca","aaa;");


    private User user1 = new User("johnwpurcell", "John Purcell", "hellothere",
            email1, true, "ROLE_USER");
    private User user2 = new User("richardhannay", "Richard Hannay", "the39steps", email2, true, "ROLE_ADMIN");
    private User user3 = new User("suetheviolinist", "Sue Black", "iloveviolins", true, "ROLE_USER");
    private User user4 = new User("rogerblake", "Rog Blake", "liberator", false, "user");

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from user_email");
        jdbc.execute("delete from user_purchaseditem");
        jdbc.execute("delete from purchasedItem");
        jdbc.execute("delete from offer");
        jdbc.execute("delete from user");
        jdbc.execute("delete from email");
    }

/*
    @Test
    public void testDelete() {
        emailsDao.saveOrUpdate(email1);
        emailsDao.saveOrUpdate(email2);
        emailsDao.saveOrUpdate(email3);

        Email retrievedEmail1 = emailsDao.getEmail(email1.getAccount());
        assertEquals("Retrieved email should be the same as the saved email",email1, retrievedEmail1);


        Offer retrieved1 = offersDao.getOffer(offer2.getId());
        assertNotNull("Offer with ID " + retrieved1.getId() + " should not be null (deleted, actual)", retrieved1);

        offersDao.delete(offer2.getId());

        Offer retrieved2 = offersDao.getOffer(offer2.getId());
        assertNull("Offer with ID " + retrieved1.getId() + " should be null (deleted, actual)", retrieved2);
    }
*/

    @Test
    public void testGetByAccount() {
        emailsDao.saveOrUpdate(email1);
        emailsDao.saveOrUpdate(email2);
        emailsDao.saveOrUpdate(email3);

        Email retrievedEmail1 = emailsDao.getEmail(email1.getAccount());
        assertEquals("Retrieved email should be the same as the saved email", email1, retrievedEmail1);

    }

    @Test
    public void testUpdate() {
        emailsDao.saveOrUpdate(email1);
        emailsDao.saveOrUpdate(email2);
        emailsDao.saveOrUpdate(email3);
        emailsDao.saveOrUpdate(emailUpdate1);

        Email retrievedEmail1 = emailsDao.getEmail(email1.getAccount());
        assertEquals("Retrieved email should be the same as the updated email", emailUpdate1, retrievedEmail1);
        assertEquals("Retrieved email should be the same as the updated email", emailUpdate1.getPassword(), retrievedEmail1.getPassword());
//        Email retrievedEmail2 = emailsDao.getEmail(email2.getAccount());
//        assertNull("Should not retrieve offer for disabled user.", retrieved2);
    }


    @Test
    public void testGetByUser() {
        emailsDao.saveOrUpdate(email1);
        emailsDao.saveOrUpdate(email2);
        emailsDao.saveOrUpdate(email3);

        usersDao.saveOrUpdate(user1);
        usersDao.saveOrUpdate(user2);
        usersDao.saveOrUpdate(user3);
        usersDao.saveOrUpdate(user4);

        List<Email> retrievedEmailList = (List<Email>) user1.getEmailList();
        assertTrue("Retrieved list should have one email", retrievedEmailList.size()==1);

        user1.addEmail(email3);
        usersDao.saveOrUpdate(user1);
        retrievedEmailList = (List<Email>) user1.getEmailList();
        assertTrue("Retrieved list should have two email", retrievedEmailList.size()==2);

        List<Email> retrievedEmailList3 = (List<Email>) user3.getEmailList();
        assertTrue("Retrieved list should have one email", retrievedEmailList3.size() == 0);

        List<User> userList = usersDao.getAllUsers();
        User userRetrieved1 = userList.get(1);
//        User userRetrieved1 = usersDao.getUser(user1.getUsername());
//        List<Email> emailList = (List<Email>) userRetrieved1.getEmailList();
        System.out.println(userRetrieved1.getEmailList());
        assertEquals("UserRetrieved should be the same as the User1", userList.get(0), user1);
    }

    @Test
    public void testAddPurchasedItems(){
        emailsDao.saveOrUpdate(email1);
        emailsDao.saveOrUpdate(email2);
        emailsDao.saveOrUpdate(email3);

        usersDao.saveOrUpdate(user1);
        usersDao.saveOrUpdate(user2);
        usersDao.saveOrUpdate(user3);
        usersDao.saveOrUpdate(user4);

        List<Email> retrievedEmailList = (List<Email>) user1.getEmailList();
        for (Email email: retrievedEmailList){
            List<PurchasedItem> purchasedItems = email.fetchPurchasedItem();
            usersDao.addPurchasedItemList(purchasedItems);
            user1.addPurchasedItems(purchasedItems);
        }
        usersDao.saveOrUpdate(user1);
    }

    @Test
    public void testRetrievePurchasedItems(){
        emailsDao.saveOrUpdate(email1);
        emailsDao.saveOrUpdate(email2);
        emailsDao.saveOrUpdate(email3);

        usersDao.saveOrUpdate(user1);
        usersDao.saveOrUpdate(user2);
        usersDao.saveOrUpdate(user3);
        usersDao.saveOrUpdate(user4);

        List<Email> retrievedEmailList = (List<Email>) user1.getEmailList();
        for (Email email: retrievedEmailList){
            List<PurchasedItem> purchasedItems = email.fetchPurchasedItem();
            usersDao.addPurchasedItemList(purchasedItems);
            user1.addPurchasedItems(purchasedItems);
        }
        usersDao.saveOrUpdate(user1);

        User retrievedUser1 = usersDao.getUser(user1.getUsername());

        assertEquals("UserRetrieved should be the same as the User1", retrievedUser1, user1);
    }



/*



    @Test
    public void testCreateRetrieve() {
        usersDao.saveOrUpdate(user1);
        usersDao.saveOrUpdate(user2);
        usersDao.saveOrUpdate(user3);
        usersDao.saveOrUpdate(user4);

        offersDao.saveOrUpdate(offer1);

        List<Offer> offers1 = offersDao.getOffers();
        assertEquals("Should be one offer.", 1, offers1.size());

        assertEquals("Retrieved offer should equal inserted offer.", offer1,
                offers1.get(0));

        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);

        List<Offer> offers2 = offersDao.getOffers();
        assertEquals("Should be six offers for enabled users.", 6,
                offers2.size());

    }

    @Test
    public void testUpdate() {
        usersDao.saveOrUpdate(user1);
        usersDao.saveOrUpdate(user2);
        usersDao.saveOrUpdate(user3);
        usersDao.saveOrUpdate(user4);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);

        offer3.setText("This offer has updated text.");
        offersDao.saveOrUpdate(offer3);

        Offer retrieved = offersDao.getOffer(offer3.getId());
        assertEquals("Retrieved offer should be updated.", offer3, retrieved);
    }

    @Test
    public void testGetUsername() {
        usersDao.saveOrUpdate(user1);
        usersDao.saveOrUpdate(user2);
        usersDao.saveOrUpdate(user3);
        usersDao.saveOrUpdate(user4);

        offersDao.saveOrUpdate(offer1);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);

        List<Offer> offers1 = offersDao.getOffers(user3.getUsername());
        assertEquals("Should be three offers for this user.", 3, offers1.size());

        List<Offer> offers2 = offersDao.getOffers("sdfsfd");
        assertEquals("Should be zero offers for this user.", 0, offers2.size());

        List<Offer> offers3 = offersDao.getOffers(user2.getUsername());
        assertEquals("Should be 1 offer for this user.", 1, offers3.size());
    }
*/



}