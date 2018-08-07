package org.example.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Student {
	private static final AtomicInteger count = new AtomicInteger(1); 
	private int StudentID;
	private String name;
	private String courses;
	private List<Student> listofStudents;
	public Student() {
		
	}
	public Student( String name, String courses){
		this.StudentID=count.getAndIncrement();
		this.name=name;
		this.courses=courses;
	}

	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	@Override
	public String toString() {
		return String.format("Student[id=%s name=%s courses=%s]", StudentID, name, courses );
	}
	
}
