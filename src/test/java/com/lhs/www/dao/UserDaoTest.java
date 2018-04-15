package com.lhs.www.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lhs.www.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	
	
	@Test
	@Ignore
	public void getUserInfoById(){
		User user = userDao.getUserInfoById("1");
		assertEquals("lhs", user.getName());
		System.out.println(user.getName());
	}
	
	@Test
	@Ignore
	public void insertUser(){
		User user = new User();
		user.setName("hxx");
		user.setPhone("5093");
		user.setAddress("老河口");
		int keyId = userDao.insertUser(user);
		assertEquals(1, keyId);
		System.out.println("---------------"+keyId);
	}
	
}
