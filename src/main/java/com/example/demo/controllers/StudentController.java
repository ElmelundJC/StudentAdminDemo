package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.repositories.IStudentRepository;
import com.example.demo.repositories.InMemoryStudentRepositoryImpl;
import com.example.demo.repositories.StudentRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    private IStudentRepository studentRepository;

    public StudentController() {
        studentRepository = new StudentRepositoryImpl();
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("students" , studentRepository.readAll());
        return "index";
    }
    @RequestMapping("/student/detail")
    public String getStudentByParameterDetail(@RequestParam int id,Model model) {
        model.addAttribute("students", studentRepository.read(id));
        return "student/detail";
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView deleteStudent(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("delete");
        Student student = studentRepository.read(id);
        mav.addObject("student", student);

        return mav;
    }


    @GetMapping("/student")
    @ResponseBody
    public String getStudentByParameter(@RequestParam int id) {
        Student stu = studentRepository.read(id);
        return "The name is: " + stu.getFirstName() + " and the cpr is " + stu.getCpr();

    }
    @GetMapping("/create")
    public ModelAndView create(){
            ModelAndView mav = new ModelAndView("create");
            Student student = new Student();
            mav.addObject("student",student);

        return mav;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit");
        Student student = studentRepository.read(id);
        mav.addObject("student", student);

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentRepository.create(student);
        return "redirect:/";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteStudent(@ModelAttribute("student") Student student) {
        int id = student.id;
        System.out.println(id);
        studentRepository.delete(id);

        return "redirect:/";
    }

}