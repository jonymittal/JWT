package com.JWT.Config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.JWT.Models.Role;
import com.JWT.ServiceImpl.RoleService;

@Component
public class DatabaseLoader implements CommandLineRunner{
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public void run(String... strings) throws Exception {
		addRoles();
	}
	
	void addRoles() {
		List<Role> roles = roleService.getRoles();
		if (roles == null || roles.size() == 0) {
			roleService.persist(new Role("ROLE_ADMIN"));
			roleService.persist(new Role("ROLE_USER"));
		}
	}
}
