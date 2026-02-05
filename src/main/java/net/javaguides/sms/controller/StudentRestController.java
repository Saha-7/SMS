package net.javaguides.sms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentRestController {
	
	private StudentService studentService;

	public StudentRestController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	// Get all students
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}
	
	// Get student by id
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		Student student = studentService.getStudentById(id);
		return ResponseEntity.ok(student);
	}
	
	// Create new student
	@PostMapping
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
		Student savedStudent = studentService.saveStudent(student);
		return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	}
	
	// Update student
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, 
												  @Valid @RequestBody Student student) {
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		Student updatedStudent = studentService.updateStudent(existingStudent);
		return ResponseEntity.ok(updatedStudent);
	}
	
	// Delete student
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return ResponseEntity.ok("Student deleted successfully");
	}
}