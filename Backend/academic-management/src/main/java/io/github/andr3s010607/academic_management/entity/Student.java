package io.github.andr3s010607.academic_management.entity;

import java.util.List;
import java.util.Objects;

import io.github.andr3s010607.academic_management.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "student")
public class Student extends User{

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	
	@ManyToMany
	@JoinTable(name = "student_subject", joinColumns = @JoinColumn(name = "student_name"), inverseJoinColumns = @JoinColumn(name = "subject_name"))
	private List<Subject> subjects;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(List<Subject> subjects) {
		super();
		this.subjects = subjects;
	}

	public Student(String name, String userName, String password, UserType userType, List<Subject> subjects) {
		super(name, userName, password, userType);
		this.subjects = subjects;
	}

	public Student(String name, String userName, String password, UserType userType) {
		super(name, userName, password, userType);
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
		Student other = (Student) obj;
		return id == other.id && Objects.equals(subjects, other.subjects);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", subjects=" + subjects + "]";
	}
	
	
	
}
