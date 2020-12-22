package com.lhs.www.dao;

import java.io.Serializable;
import java.util.Map;

import com.lhs.www.entity.User;

public interface UserDao {
	
	public User getUserInfoById(String id);
	
	public int insertUser(User user);
	
	public boolean updateUser(User user);
	
	String getNameByProcedure(Integer id);
	
	Map<String, Object> getUserByProcedure(Integer id);

}
