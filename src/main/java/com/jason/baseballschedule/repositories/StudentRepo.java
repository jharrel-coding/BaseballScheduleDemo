package com.jason.baseballschedule.repositories;

import com.jason.baseballschedule.models.Course;
import com.jason.baseballschedule.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {
    List<Student> findAll();
    List<Student> findByDormIdIs(Long dormId);
    List<Student> findAllByCourses(Course course);
    List<Student> findByCoursesNotContains(Course course);
}