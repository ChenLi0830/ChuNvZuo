package com.springapp.test.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

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

import com.springapp.bean.User;
import com.springapp.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/main/java/com/springapp/configs/dao-context.xml",
		"classpath:com/main/java/com/springapp/configs/security-context.xml",
		"classpath:com/test/java/com/springapp/test/config/datasource.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private Email email1 = new Email("lulugeo.li@gmail.com","Geoffery0830");
	private Email email2 = new Email("lulugeo.li@gmail.com","asdfjkl;");


	private User user1 = new User("johnwpurcell", "John Purcell", "hellothere",
			email1, true, "ROLE_USER");
	private User user2 = new User("richardhannay", "Richard Hannay", "the39steps",
			email1, true, "ROLE_ADMIN");
	private User user3 = new User("suetheviolinist", "Sue Black", "iloveviolins",
			email2, true, "ROLE_USER");
	private User user4 = new User("rogerblake", "Rog Blake", "liberator",
			email2, false, "user");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		
		List<User> users1 = usersDao.getAllUsers();
		
		assertEquals("One user should have been created and retrieved", 1, users1.size());
		
		assertEquals("Inserted user should match retrieved", user1, users1.get(0));
		
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		List<User> users2 = usersDao.getAllUsers();
		
		assertEquals("Should be four retrieved users.", 4, users2.size());
	}
	
	@Test
	public void testExists() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		
		assertTrue("User should exist.", usersDao.exists(user2.getUsername()));
		assertFalse("User should not exist.", usersDao.exists("xkjhsfjlsjf"));
	}
}
