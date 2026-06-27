package io.github.andr3s010607.academic_management.entity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.github.andr3s010607.academic_management.enums.UserType;
import jakarta.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class User implements UserDetails{
	
	private String name;
	private String userName;
	private String password;
	private UserType userType;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String name, String userName, String password, UserType userType) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	

	public void setName(String name) {
		this.name = name;
	}



	public UserType getUserType() {
		return userType;
	}



	public void setUserType(UserType userType) {
		this.userType = userType;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + userType.name()));
	}

	@Override
	public @Nullable String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, password, userName, userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(userName, other.userName) && userType == other.userType;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", userName=" + userName + ", password=" + password + ", userType=" + userType
				+ "]";
	}	
	
	
	
}
