package com.lg_group.test_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "id_student")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;

    @Column(nullable = false, name = "student_name")
    @NotBlank
    @Size(min = 2, max = 256)
    private String studentName;

    @Column(nullable = false, name = "student_age")
    @NotNull
    @Min(value = 16)
    private Integer studentAge;

    @Column(nullable = false, name = "student_score")
    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double studentScore;
}
