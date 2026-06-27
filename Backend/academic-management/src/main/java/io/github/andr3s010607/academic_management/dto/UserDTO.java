package io.github.andr3s010607.academic_management.dto;

import java.util.Objects;

import io.github.andr3s010607.academic_management.enums.UserType;

public class UserDTO {
	
	private String name;
	private String userName;
	private String password;
	private UserType userType;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(String name, String userName, String password, UserType userType) {
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
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
		UserDTO other = (UserDTO) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(userName, other.userName) && userType == other.userType;
	}

	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", userName=" + userName + ", password=" + password + ", userType=" + userType
				+ "]";
	}
	
	
	
}
