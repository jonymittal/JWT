package com.JWT.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.JWT.Dao.IUserDao;
import com.JWT.Models.User;
import com.JWT.Security.JwtUserFactory;
import com.JWT.Service.IUserService;


@Service
public class UserService implements IUserService,UserDetailsService{
	
	/** Created by Jony Mittal on 04.04.19. **/

	@Autowired
	private IUserDao userDao;
	
	@Override
	public User getUserById(Long id) {
		return this.userDao.getUserById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		return this.userDao.getUserByEmail(email);
	}

	@Override
	public User saveUser(User user) {
		return this.userDao.saveUser(user);
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("User name >>> "+username);
		User user = userDao.getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	return JwtUserFactory.create(user);
        }
    }
}
