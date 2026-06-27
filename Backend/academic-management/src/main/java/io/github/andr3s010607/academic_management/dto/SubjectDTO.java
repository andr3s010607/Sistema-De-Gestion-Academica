package io.github.andr3s010607.academic_management.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.andr3s010607.academic_management.entity.Student;
import io.github.andr3s010607.academic_management.entity.Teacher;

public class SubjectDTO {
	
	private Long id;
	private String name;
	private int subjectCode;
	private String classRoom;
	private Teacher teacher;
	private List<Student> students;
	
	public SubjectDTO() {
		// TODO Auto-generated constructor stub
	}

	public SubjectDTO(String name, int subjectCode, String classRoom, Teacher teacher, List<Student> students) {
		super();
		this.name = name;
		this.subjectCode = subjectCode;
		this.classRoom = classRoom;
		this.teacher = teacher;
		this.students = students != null ? new ArrayList<>(students) : new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		this.students = students != null ? new ArrayList<>(students) : new ArrayList<>();
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
		SubjectDTO other = (SubjectDTO) obj;
		return Objects.equals(classRoom, other.classRoom) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(students, other.students)
				&& subjectCode == other.subjectCode && Objects.equals(teacher, other.teacher);
	}

	@Override
	public String toString() {
		return "SubjectDTO [id=" + id + ", name=" + name + ", subjectCode=" + subjectCode + ", classRoom=" + classRoom
				+ ", teacher=" + teacher + ", students=" + students + "]";
	}
	
	
}
