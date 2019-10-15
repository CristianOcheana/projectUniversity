package com.project.university.repositories;

import com.project.university.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EntityRepository extends CrudRepository<Student, Long> {

    List<Student> findAll();
}
