package com.jason.baseballschedule.repositories;

import com.jason.baseballschedule.models.Course;
import com.jason.baseballschedule.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends CrudRepository<Course, Long> {
    List<Course> findAll();
    Course findByIdIs(Long id);
    List<Course> findAllByStudents(Student student);
    List<Course> findByStudentsNotContains(Student student);
}