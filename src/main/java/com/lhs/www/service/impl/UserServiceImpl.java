package com.lhs.www.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhs.www.dao.UserDao;
import com.lhs.www.entity.User;
import com.lhs.www.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUserInfoById(String id) {
		return userDao.getUserInfoById(id);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean insertUser(User user) {
		if (user !=null && user.getName() !=null) {
				int effectedNum = userDao.insertUser(user);
				if (effectedNum>0) {
					return true;
				}else{
					return false;
				}
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
