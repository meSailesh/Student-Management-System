package com.javaWithSpringBoot.studentmanagementsystem.studentResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="student_result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
            @SequenceGenerator(
                    name = "student_result_sequence",
                    sequenceName = "student_result_sequence",
                    allocationSize = 1
            )
            @GeneratedValue(
                    strategy = SEQUENCE,
                    generator = "student_result_sequence"

            )
    Long id;
    @Column(name="subject_mark", nullable = false)
    Integer subjectMarks;
    @Column(name="student_id")
    Long studentId;
    @Column(name = "subject_id")
    Long subjectId;
}
