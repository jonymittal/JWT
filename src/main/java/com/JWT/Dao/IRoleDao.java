package com.JWT.Dao;

import java.util.List;

import com.JWT.Models.Role;

public interface IRoleDao {
	
	/** Created by Jony Mittal on 04.04.19. **/
	
	public Role getRoleById(Integer id);
	
	void persist(Role role);
	
	List<Role> getRoles();

}
