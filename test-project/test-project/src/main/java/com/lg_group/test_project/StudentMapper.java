package com.lg_group.test_project;

import com.lg_group.test_project.DTO.StudentDTO;
import com.lg_group.test_project.model.Student;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    public List<StudentDTO> listStudentToListDTO(List<Student> student);
    public StudentDTO studentToDTO(Student student);
}
