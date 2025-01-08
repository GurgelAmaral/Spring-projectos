package com.lg_group.test_project;

import com.lg_group.test_project.DTO.StudentDTO;
import com.lg_group.test_project.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-23T18:30:10-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public List<StudentDTO> listStudentToListDTO(List<Student> student) {
        if ( student == null ) {
            return null;
        }

        List<StudentDTO> list = new ArrayList<StudentDTO>( student.size() );
        for ( Student student1 : student ) {
            list.add( studentToDTO( student1 ) );
        }

        return list;
    }

    @Override
    public StudentDTO studentToDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setStudentName( student.getStudentName() );
        studentDTO.setStudentAge( student.getStudentAge() );

        return studentDTO;
    }
}
