package com.lg_group.test_project.service;

import com.lg_group.test_project.DTO.StudentDTO;
import com.lg_group.test_project.StudentMapper;
import com.lg_group.test_project.model.Student;
import com.lg_group.test_project.repository.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.swing.text.html.Option;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentMapper studentMapper;

    public List<StudentDTO> findAllStudents(){
        List<Student> listStudent = studentRepo.findAll();
        List<StudentDTO> listSudentDTO = studentMapper.listStudentToListDTO(listStudent);
        return  listSudentDTO;
    }

    public StudentDTO findStudentById(Long id){
        Optional<Student> optionalStudent = studentRepo.findById(id);
        StudentDTO studentDTO = optionalStudent.map(studentMapper::studentToDTO)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return studentDTO;
    }

    public StudentDTO saveNewStudent(@Valid Student newStudent){
        Student student = studentRepo.save(newStudent);
        return studentMapper.studentToDTO(student);
    }

    public StudentDTO updateStudent(Long id, @Valid Student updatedStudent){
        Optional<Student> oldStudent = studentRepo.findById(id);
        if(oldStudent.isPresent()){
                Student oldStudentObj = oldStudent.get();
                BeanUtils.copyProperties(updatedStudent, oldStudentObj, "idStudent", "studentScore");
                Student studentObj = studentRepo.save(oldStudentObj);
                StudentDTO studentDTO = studentMapper.studentToDTO(studentObj);
                return studentDTO;
        }
        else throw new EntityNotFoundException(id + " not found");
    }

    public void removeStudentById(Long id){
        Optional<Student> studentObj = studentRepo.findById(id);
        if (studentObj.isPresent()){
            studentRepo.delete(studentObj.get());
        }
        else throw new EntityNotFoundException(id + " User ID not found");
    }
}
