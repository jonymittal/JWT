package com.JWT.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.JWT.Dao.IRoleDao;
import com.JWT.Models.Role;
import com.JWT.Service.IRoleService;

@Service
public class RoleService implements IRoleService {
	
	/** Created by Jony Mittal on 04.04.19. **/

	@Autowired
	private IRoleDao roleDao;

	@Override
	public Role getRoleById(Integer id) {
		return roleDao.getRoleById(id);
	}
	
	@Override
	public void persist(Role role) {
		roleDao.persist(role);
		
	}
	
	@Override
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}
}
