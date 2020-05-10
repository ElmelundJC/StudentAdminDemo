package com.example.demo.repositories;

import com.example.demo.models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service
public class InMemoryStudentRepositoryImpl implements IStudentRepository{
    private List<Student> inMemoryDatabase;

    public InMemoryStudentRepositoryImpl(){
        this.inMemoryDatabase = new ArrayList<Student>(
                Arrays.asList(
                        new Student(1, "Nicklas","Frederiksen", new Date(12312), "31134115-1231"),
                        new Student(2, "Bent","Karlsen", new Date(2141241), "31134115-4112"),
                        new Student(3, "Bob","Bobber",new Date(12424141), "233124f14-5551"),
                        new Student(4, "Bob","Bobbistan",new Date(12424141), "233124f14-5551"),
                        new Student(5, "Bob","Bob",new Date(12424141), "233124f14-5551"),
                        new Student(6, "Bob","Bobbington",new Date(12424141), "233124f14-5551"),
                        new Student(7, "Bob","Bobsem",new Date(12424141), "233124f14-5551")
                )
        );
    }

    @Override
    public boolean create(Student student) {
        System.out.println(student.toString());
        inMemoryDatabase.add(student);
        System.out.println(inMemoryDatabase.get(7));
        System.out.println(inMemoryDatabase.toString());

        return false;
    }

    @Override
    public Student read(int id) {
        for(Student stu : inMemoryDatabase){
            if(stu.getId() == id){
                return stu;
            }
        }
        return null;
    }

    @Override
    public List<Student> readAll() {
        return inMemoryDatabase;
    }

    @Override
    public boolean update(Student student) {
        for(int i = 0; i < inMemoryDatabase.size(); i++){
            if(inMemoryDatabase.get(i).getId() == student.id){
                inMemoryDatabase.set(i, student);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        System.out.println(id);
        for (Student student : inMemoryDatabase){
            if(student.id == id){
                System.out.println(id);
                inMemoryDatabase.remove(student);
                return true;
            }


        }
        return false;
    }
}
