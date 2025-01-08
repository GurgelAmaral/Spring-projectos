package com.lg_group.test_project.controller;

import com.lg_group.test_project.DTO.StudentDTO;
import com.lg_group.test_project.model.Student;
import com.lg_group.test_project.repository.StudentRepo;
import com.lg_group.test_project.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        try {
            List<StudentDTO> listOfStudents = studentService.findAllStudents();
            return new ResponseEntity<>(listOfStudents, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Long id) {
        try {
            StudentDTO studentDTOObj = studentService.findStudentById(id);
            return new ResponseEntity<>(studentDTOObj, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<StudentDTO> postNewStudent(@RequestBody @Valid Student student) {
        try {
            StudentDTO studentDTOObj = studentService.saveNewStudent(student);
            return new ResponseEntity<>(studentDTOObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/student-update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody @Valid Student stu) {
        try {
            StudentDTO objStudentDTO = studentService.updateStudent(id, stu);
            return new ResponseEntity<>(objStudentDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/student-removal/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        try {
            studentService.removeStudentById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
