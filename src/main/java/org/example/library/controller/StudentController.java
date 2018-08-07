package org.example.library.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.library.model.Student;
import org.example.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@RestController
public class StudentController {

	@Autowired
	StudentService service;
	@Autowired
	JdbcTemplate jdbc;

	Student student;
	List<Map<String, Object>> list;
	
	
	public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, TemplateEngine templateEngine) throws IOException {
		WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("students", list);
		templateEngine.process("students/list", ctx, response.getWriter());
	}

	// Get all students
	@RequestMapping("/getstudents")
	public List<Map<String, Object>> getStudents() {
		String sql = "Select * from student_details;";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		printList(list);
		return list;
	}

	// Get specific student by ID
	@RequestMapping("/getstudent/{id}")
	public Map<String, Object> getStudent(@PathVariable String id) {
		String sql = "Select * from student_details where id=?;";
		List<Map<String, Object>> list = jdbc.queryForList(sql, id);
		printList(list);
		return list.get(0);
	}

	// Create student using post
	@PostMapping(value = "/insert")
	public List<Map<String, Object>> insert(@RequestParam HashMap<String, String> hmap) {
		String name = hmap.get("name");
		String courses = hmap.get("courses");
		String sql = "Insert into student_details(name,email) values(?,?);";
		try {
			jdbc.update(sql, name, courses);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getStudents();
	}

	// Delete all students
	@RequestMapping("/delstudents")
	public String removeStudents() {
		String sql = "Truncate table student_details;";
		jdbc.execute(sql);
		return "Deleted all students";
	}

	// Delete specific student by ID
	@RequestMapping("/delstudent/{id}")
	public String removeStudent(@PathVariable int id) {
		String sql = "Delete from students_details where id=?;";
		jdbc.update(sql, id);
		jdbc.execute(sql);
		return "Deleted 1 entry";
	}

	// Delete specific student by ID
	@RequestMapping("/updatestudent/{id}")
	public String updateStudent(@RequestParam HashMap<String, String> hmap) {
		String id = hmap.get("id");
		String name = hmap.get("name");
		String courses = hmap.get("courses");
		String sql = "Update students_details set (name=?, courses=?) where id=?;";
		jdbc.update(sql, name, courses, id);
		jdbc.execute(sql);
		return "Updated 1 entry";
	}
	
	public void printList(List<Map<String, Object>> list2) {
		System.out.println(list2);
	}

}
