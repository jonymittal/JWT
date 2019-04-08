package com.JWT.Daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.JWT.Dao.IRoleDao;
import com.JWT.Models.Role;

@Repository
@Transactional
public class RoleDao implements IRoleDao {
	
	/** Created by Jony Mittal on 04.04.19. **/
	
	@Autowired	
	private EntityManager entityManager;

	@Override
	public Role getRoleById(Integer id) {
		return entityManager.find(Role.class, id);
	}
	
	@Override
	public void persist(Role role) {
		entityManager.persist(role);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() {
		return entityManager.createQuery("from Role").getResultList();
	}
}
