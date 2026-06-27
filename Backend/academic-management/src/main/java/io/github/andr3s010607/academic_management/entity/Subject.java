package io.github.andr3s010607.academic_management.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "subject")
public class Subject {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	
	private String name;
	private int subjectCode;
	private String classRoom;
	
	@ManyToOne
	@JoinColumn(name = "teacher_name")
	private Teacher teacher;
	
	@ManyToMany(mappedBy = "subjects")
	private List<Student> students;
	
	public Subject() {
		// TODO Auto-generated constructor stub
	}

	public Subject(String name, int subjectCode, String classRoom, Teacher teacher, List<Student> students) {
		super();
		this.name = name;
		this.subjectCode = subjectCode;
		this.classRoom = classRoom;
		this.teacher = teacher;
		this.students = students;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public int hashCode() {
		return Objects.hash(classRoom, id, name, students, subjectCode, teacher);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return Objects.equals(classRoom, other.classRoom) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(students, other.students) && subjectCode == other.subjectCode
				&& Objects.equals(teacher, other.teacher);
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", subjectCode=" + subjectCode + ", classRoom=" + classRoom
				+ ", teacher=" + teacher + ", students=" + students + "]";
	}
	
	
}
