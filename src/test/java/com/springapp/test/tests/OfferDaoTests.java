package com.springapp.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import com.springapp.bean.Email;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springapp.bean.Offer;
import com.springapp.bean.User;
import com.springapp.dao.OffersDao;
import com.springapp.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:com/main/java/com/springapp/configs/dao-context.xml",
        "classpath:com/main/java/com/springapp/configs/security-context.xml",
        "classpath:com/test/java/com/springapp/test/config/datasource.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

    @Autowired
    private OffersDao offersDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource dataSource;

    private Email email1 = new Email("lulugeo.li@gmail.com","Wangwei19820510");
    private Email email2 = new Email("chen.li@gmail.com","asdfjkl;");


    private User user1 = new User("johnwpurcell", "John Purcell", "hellothere",
            true, "ROLE_USER");
    private User user2 = new User("richardhannay", "Richard Hannay", "the39steps",
            true, "ROLE_ADMIN");
    private User user3 = new User("suetheviolinist", "Sue Black", "iloveviolins",
            true, "ROLE_USER");
    private User user4 = new User("rogerblake", "Rog Blake", "liberator",
            false, "user");

    private Offer offer1 = new Offer(user1, "This is a test offer.");
    private Offer offer2 = new Offer(user1, "This is another test offer.");
    private Offer offer3 = new Offer(user2, "This is yet another test offer.");
    private Offer offer4 = new Offer(user3, "This is a test offer once again.");
    private Offer offer5 = new Offer(user3,
            "Here is an interesting offer of some kind.");
    private Offer offer6 = new Offer(user3, "This is just a test offer.");
    private Offer offer7 = new Offer(user4,
            "This is a test offer for a user that is not enabled.");

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

//        jdbc.execute("delete from user_purchasedItem");
        jdbc.execute("delete from user_email");
        jdbc.execute("delete from purchasedItem");
        jdbc.execute("delete from offer");
        jdbc.execute("delete from user");
        jdbc.execute("delete from email");

//        jdbc.execute("delete from users_purchasedItems");
//        jdbc.execute("delete from users_emails");
//        jdbc.execute("delete from purchasedItem");
//        jdbc.execute("delete from offers");
//        jdbc.execute("delete from users");
//        jdbc.execute("delete from email");
    }

    @Test
    public void testDelete() {
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

        Offer retrieved1 = offersDao.getOffer(offer2.getId());
        assertNotNull("Offer with ID " + retrieved1.getId() + " should not be null (deleted, actual)", retrieved1);

        offersDao.delete(offer2.getId());

        Offer retrieved2 = offersDao.getOffer(offer2.getId());
        assertNull("Offer with ID " + retrieved1.getId() + " should be null (deleted, actual)", retrieved2);
    }

    @Test
    public void testGetById() {
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

        Offer retrieved1 = offersDao.getOffer(offer1.getId());
        assertEquals("Offers should match", offer1, retrieved1);

        Offer retrieved2 = offersDao.getOffer(offer7.getId());
        assertNull("Should not retrieve offer for disabled user.", retrieved2);
    }

    @Test
    public void testCreateRetrieve() {
        usersDao.saveOrUpdate(user1);
        usersDao.saveOrUpdate(user2);
        usersDao.saveOrUpdate(user3);
        usersDao.saveOrUpdate(user4);

        offersDao.saveOrUpdate(offer1);

        List<Offer> offers1 = offersDao.getOffers();
        assertEquals("Should be one offer.", 1, offers1.size());

//        assertThat(offer1,is(equalTo(offers1.get(0))));
        assertEquals("Retrieved offer should equal inserted offer.", offer1, offers1.get(0));

        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);

        List<Offer> offers2 = offersDao.getOffers();
        assertEquals("Should be six offers for enabled users.", 6,
                offers2.size());


        Offer offerRetrieved1 = offersDao.getOffer(offer1.getId());
        assertEquals(offerRetrieved1,offer1);
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



}
