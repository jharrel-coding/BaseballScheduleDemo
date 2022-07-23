package com.jason.baseballschedule.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jason.baseballschedule.models.Course;
import com.jason.baseballschedule.models.Student;
import com.jason.baseballschedule.repositories.CourseRepo;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> allCourses(){
        return courseRepo.findAll();
    }

    public List<Course> getAssignedStudents(Student student){
        return courseRepo.findAllByStudents(student);
    }

    public List<Course> getUnassignedStudents(Student student){
        return courseRepo.findByStudentsNotContains(student);
    }

    public List<Course> dropCourse(Student student, Course course){
        List<Course> oldList = courseRepo.findAllByStudents(student);
        ArrayList<Course> newList = new ArrayList<>();
        for(Course c:oldList) {
            if(c.getId()!=course.getId()) {
                newList.add(course);
            }
        }
        return newList;
    }

    public Course findById(Long id) {
        Optional<Course> optionalCourse = courseRepo.findById(id);
        if(optionalCourse.isPresent()) {
            return optionalCourse.get();
        }else {
            return null;
        }
    }

    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }
}