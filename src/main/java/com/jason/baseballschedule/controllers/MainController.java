package com.jason.baseballschedule.controllers;

import java.util.List;

import com.jason.baseballschedule.models.Course;
import com.jason.baseballschedule.models.Dorm;
import com.jason.baseballschedule.models.Student;
import com.jason.baseballschedule.services.CourseService;
import com.jason.baseballschedule.services.DormService;
import com.jason.baseballschedule.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class MainController {

    @Autowired
    DormService dormService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @GetMapping("/")
    public String index() {
        return "redirect:/dorms";
    }

    @GetMapping("/dorms")
    public String dorms(Model model) {

        model.addAttribute("dorms", dormService.allDorms());

        return "dorms.jsp";
    }

    @GetMapping("/dorms/new")
    public String addDorm(@ModelAttribute("dorm") Dorm dorm) {
        return "new_dorm.jsp";
    }

    @PostMapping("/dorms/new")
    public String addDorm(@ModelAttribute("dorm") Dorm dorm, BindingResult result) {
        if(result.hasErrors()) {
            return "new_dorm.jsp";
        }else {
            dormService.addDorm(dorm);
            return "redirect:/dorms";
        }
    }

    @GetMapping("/dorms/{id}")
    public String dormStudents(@PathVariable("id") Long id, @ModelAttribute("student") Student student, Model model) {
        List<Student> students = studentService.dormStudents(id);
        List<Student> allStudents = studentService.allStudents();
        model.addAttribute("students", students);
        model.addAttribute("allStudents", allStudents);
        model.addAttribute("dormId", id);
        return "students.jsp";
    }

    @RequestMapping("/dorms/{id}")
    public String addStudentToDorm(@PathVariable("id") Long id, @RequestParam(value="studentId", required=false) Long studentID) {
        Student student = studentService.findStudent(studentID);
        studentService.addToDorm(student, dormService.findDorm(id));
        return "redirect:/dorms/{id}";
    }

    @GetMapping("/students/add")
    public String addStudent(@ModelAttribute("student") Student student, Model model) {
        List<Student> students = studentService.allStudents();
        model.addAttribute("students", students);
        model.addAttribute("dorms", dormService.allDorms());
        return "new_student.jsp";
    }

    @PostMapping("/students/add")
    public String addNewStudent(@ModelAttribute("student") Student student, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("dorms", dormService.allDorms());
            return "new_student.jsp";
        }else {
            studentService.addStudent(student);
            return "redirect:/dorms/" + student.getDorm().getId();
        }
    }

    @RequestMapping("/students/remove/{id}")
    public String removeStudent(@PathVariable("id") Long id) {
        Student student = studentService.findStudent(id);
        Long dormId = student.getDorm().getId();
        studentService.removeFromDorm(student);
        return "redirect:/dorms/" + dormId;
    }

    // Student Roster n:m

    @GetMapping("/students/{id}")
    public String viewStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findStudent(id);
        model.addAttribute("student", student);
        model.addAttribute("allCourses", courseService.getUnassignedStudents(student));
        return "view_student.jsp";
    }

    @PostMapping("/students/{id}")
    public String assignCourse(@PathVariable("id") Long id, @RequestParam(value="courseId") Long courseId, Model model) {
        Student student = studentService.findStudent(id);
        model.addAttribute("student", student);
        model.addAttribute("allCourses", courseService.getUnassignedStudents(student));
        student.getCourses().add(courseService.findById(courseId));
        studentService.updateStudent(student);
        return "redirect:/students/"+id;
    }

    @GetMapping("/classes/new")
    public String addCourse(@ModelAttribute("course") Course course) {
        return "new_course.jsp";
    }

    @PostMapping("/classes/new")
    public String addCourse(@ModelAttribute("course") Course course, BindingResult result) {
        if(result.hasErrors()) {
            return "new_course.jsp";
        }else {
            courseService.addCourse(course);
            return "redirect:/dorms";
        }
    }

    @GetMapping("/classes")
    public String viewCourses(Model model) {
        model.addAttribute("courses", courseService.allCourses());
        return "courses.jsp";
    }

    @GetMapping("/classes/{id}")
    public String viewCourses(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.findById(id));
        return "view_course.jsp";
    }

    @RequestMapping("/classes/remove/{id}/{courseId}")
    public String dropCourse(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId, Model model) {
        Student student = studentService.findStudent(id);
        student.getCourses().remove(courseService.findById(courseId));
        studentService.updateStudent(student);
        model.addAttribute("student", student);
        model.addAttribute("allCourses", courseService.getUnassignedStudents(student));
        return "redirect:/students/{id}";
    }

}