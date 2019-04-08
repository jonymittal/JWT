package com.JWT.Daoimpl;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.JWT.Dao.IUserDao;
import com.JWT.Models.User;

@Repository
@Transactional
public class UserDao implements IUserDao {
	
	/** Created by Jony Mittal on 04.04.19. **/

	@Autowired
	private EntityManager entityManager;

	@Override
	public User getUserById(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User getUserByEmail(String email) {
		try {
			return entityManager.createQuery("from User where email ='" + email + "'", User.class).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User saveUser(User user) {
		return entityManager.merge(user);
	}
}
