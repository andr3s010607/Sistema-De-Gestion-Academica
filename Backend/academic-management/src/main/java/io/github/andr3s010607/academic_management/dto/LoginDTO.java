package io.github.andr3s010607.academic_management.dto;

import java.util.Objects;

import io.github.andr3s010607.academic_management.enums.UserType;

public class LoginDTO {
	
	private String userName;
	private String password;
	private UserType userType;
	
	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}

	

	public LoginDTO(String userName, String password, UserType userType) {
		super();
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}

	
	
	public UserType getUserType() {
		return userType;
	}



	public void setUserType(UserType userType) {
		this.userType = userType;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginDTO other = (LoginDTO) obj;
		return Objects.equals(password, other.password) && Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "LoginDTO [userName=" + userName + ", password=" + password + "]";
	}
	
	
	
}
