package io.github.andr3s010607.academic_management.dto;

import java.util.Objects;

import io.github.andr3s010607.academic_management.enums.UserType;

public class AdminDTO extends UserDTO{
	
	private Long id;
	
	public AdminDTO() {
		// TODO Auto-generated constructor stub
	}

	public AdminDTO(String name, String userName, String password, UserType userType) {
		super(name, userName, password, userType);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminDTO other = (AdminDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "AdminDTO [id=" + id + "]";
	}
	
	
}
