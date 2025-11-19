package com.example.sms.controller;

import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;
    @GetMapping
    public ResponseEntity<String> getStudentPage() throws IOException {
        // Read the HTML content from static folder
        Path path = new ClassPathResource("static/index.html").getFile().toPath();
        String htmlContent = new String(Files.readAllBytes(path));
        return ResponseEntity.ok().body(htmlContent);
    }

    @PostMapping("/save")
    public void save(@Valid @RequestBody Student syudent){
        service.saveStudents(syudent);
    }
    @GetMapping("/all")
    public List<Student> findAll(){
        return service.findAll();
    }
    @GetMapping("/find/{id}")
    public Student findById(@PathVariable long id){
        return service.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        service.deleteById(id);
    }
    @DeleteMapping("/delete")
    public void deleteAll(){
        service.deleteAll();
    }
}
