package net.datasa.web5.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;





@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUser implements UserDetails {
	
	String id;
	String password;
	String roleName;
	boolean enabled;
	String name;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//  "ROLE_USER", "ROLE_ADMIN-
		return Collections.singletonList(new SimpleGrantedAuthority(roleName));
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}



}
