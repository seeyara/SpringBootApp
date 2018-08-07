package org.example.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.example.library.model.Student;
import org.springframework.stereotype.Component;


@Component
public class StudentService {
	
	private static List<Student> students = new ArrayList<Student>();
	
	
	//get all students
	public static List<Student> getStudents(){
		return students;
	}
	
	//create student
	public static void addStudent(String name, String courses) {
		Student s=new Student(name, courses);
		students.add(s);
	}
	
	//get one student
	public static Student getStudent(int id) {
		for(Student student: students) {
			if(student.getStudentID()==id)
				return student;
		}
		return null;
	}
	
	//create a student
	
	
	//update a student
	public static String updateStudent(int id) {
		return null;
//		for(Student student:students)
//			if(student.getStudentID()==id)
//				
	}
	
	
	//delete a student
	public static String removeStudent(int id) {
		for(Student student:students) {
			if(student.getStudentID()==id)
				students.remove(id);
			else return "Student doesnt exist";
		}
		
		return "Done";
	}
}
