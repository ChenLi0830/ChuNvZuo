package com.springapp.test.tests;

import com.springapp.bean.*;
import com.springapp.dao.OffersDao;
import com.springapp.dao.UsersDao;
import junit.framework.Assert;
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
public class GoogleMailFetcherTest {

    @Autowired
    private OffersDao offersDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource dataSource;

    private String username = "lulugeo.li@gmail.com";
    private String password = "Wangwei19820510";
    Email lulugeoGmail = new Email(username,password);

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }

    @Test
    public void testFetchPurchasedItemList() {
        List<PurchasedItem> purchasedItems = new GoogleMailFetcher().start(username, password);
        System.out.println(purchasedItems);
        Assert.assertNotNull(purchasedItems);
        Assert.assertTrue(purchasedItems.size() > 6);
    }

    @Test
    public void testGetItemList() {
        List<PurchasedItem> purchasedItems = lulugeoGmail.fetchEmail();

        System.out.println(purchasedItems);
        Assert.assertNotNull(purchasedItems);
        Assert.assertTrue(purchasedItems.size() > 6);
    }


}