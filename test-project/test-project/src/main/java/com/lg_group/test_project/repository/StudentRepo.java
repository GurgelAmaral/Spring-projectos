package com.lg_group.test_project.repository;

import com.lg_group.test_project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
