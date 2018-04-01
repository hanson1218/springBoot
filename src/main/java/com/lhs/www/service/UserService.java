package com.lhs.www.service;

import com.lhs.www.entity.User;

public interface UserService {
	
	public User getUserInfoById(String id);
	
	public boolean insertUser(User user);
	
	public boolean updateUser(User user);

}
