package io.github.andr3s010607.academic_management.entity;

import java.util.Objects;

import io.github.andr3s010607.academic_management.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "admin")
public class Admin extends User{
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public Admin(String name, String userName, String password, UserType userType) {
		super(name, userName, password, userType);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
		Admin other = (Admin) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + "]";
	}
	
	
}
