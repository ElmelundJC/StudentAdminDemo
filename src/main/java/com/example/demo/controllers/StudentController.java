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
        studentRepository = new InMemoryStudentRepositoryImpl();
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("students" , studentRepository.readAll());
        return "index";
    }
    @RequestMapping("/student/detail")
    public String getStudentByParameterDetai(@RequestParam int id,Model model) {
        model.addAttribute("students",studentRepository.read(id));
        return "student/detail";
    }

    @GetMapping("/delete")
    public String deleteStudentByParam(@RequestParam int id,Model model) {
        model.addAttribute("students",studentRepository.read(id));
        return "delete";
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
    public String saveProduct(@ModelAttribute("student") Student student) {
        studentRepository.create(student);

        return "redirect:/";
    }

}