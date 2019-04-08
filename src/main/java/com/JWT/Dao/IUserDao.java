package com.JWT.Dao;

import com.JWT.Models.User;

public interface IUserDao {
	
	/** Created by Jony Mittal on 04.04.19. **/
	
	public User getUserById(Long id);
	
	public User getUserByEmail(String email);
	
	public User saveUser(User user);
	
}
