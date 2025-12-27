package com.example.sms.service;

import com.example.sms.entity.Student;
import com.example.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;

    public void saveStudents(Student students){
        Student stud=new Student();
        if(repo.findByEmail(students.getEmail()).isPresent()
//                && repo.findByName(students.getName()).isPresent()
//                && repo.findByCourse(students.getCourse()).isPresent()
                )
        {
            System.out.println("Student with email "+students.getEmail()+" already exists.");
        }
        else repo.save(students);
    }
    public List<Student> findAll(){
        return repo.findAll();
    }
    public Student findById(long id){
        return repo.findById(id).orElse(null);
    }
    public void deleteById(long id){
        repo.deleteById(id);
    }
    public void deleteAll(){
        repo.deleteAll();
    }
    public String updateById(long id, Student student){
        if(repo.findById(id).isPresent()){
            Student existingStudent=repo.findById(id).get();
            existingStudent.setName(student.getName());
            existingStudent.setAge(student.getAge());
            existingStudent.setCourse(student.getCourse());
            existingStudent.setEmail(student.getEmail());
            repo.save(existingStudent);
            return "Student with id "+id+" updated successfully.";
        }
        else{
            return "Student with id "+id+" not found.";
        }
    }

}
