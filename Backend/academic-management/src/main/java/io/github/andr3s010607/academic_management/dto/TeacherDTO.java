package io.github.andr3s010607.academic_management.dto;

import java.util.List;
import java.util.Objects;

import io.github.andr3s010607.academic_management.entity.Subject;
import io.github.andr3s010607.academic_management.enums.UserType;

public class TeacherDTO extends UserDTO{
	
	private Long id;
	private List<Subject> subjects;
	
	public TeacherDTO() {
		// TODO Auto-generated constructor stub
	}

	public TeacherDTO(List<Subject> subjects) {
		super();
		this.subjects = subjects;
	}

	public TeacherDTO(String name, String userName, String password, UserType userType, List<Subject> subjects) {
		super(name, userName, password, userType);
		this.subjects = subjects;
	}

	public TeacherDTO(String name, String userName, String password, UserType userType) {
		super(name, userName, password, userType);
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, subjects);
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
		TeacherDTO other = (TeacherDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(subjects, other.subjects);
	}

	@Override
	public String toString() {
		return "TeacherDTO [id=" + id + ", subjects=" + subjects + "]";
	}
	
	
}
