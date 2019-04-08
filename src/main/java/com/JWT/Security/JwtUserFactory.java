package com.JWT.Security;

import java.util.HashSet;
import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.JWT.Models.User;

public final class JwtUserFactory {
	
    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole().getRole()),
                true,
                user.getLastPasswordResetDate()
        );
    }

    private static Set<GrantedAuthority> mapToGrantedAuthorities(String role) {
    	Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	    grantedAuthorities.add(new SimpleGrantedAuthority(role));
    	return grantedAuthorities;
    }
}
