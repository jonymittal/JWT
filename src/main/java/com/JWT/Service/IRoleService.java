package com.JWT.Service;

import java.util.List;

import com.JWT.Models.Role;

public interface IRoleService {
	
	/** Created by Jony Mittal on 04.04.19. **/
	
	public Role getRoleById(Integer id);
	
	void persist(Role role);
	
	List<Role> getRoles();

}
