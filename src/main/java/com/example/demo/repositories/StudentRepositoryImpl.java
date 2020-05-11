package com.example.demo.repositories;

import com.example.demo.models.Student;
import com.example.demo.util.DatabaseConnectionManager;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;



public class StudentRepositoryImpl implements IStudentRepository {
    private Connection conn;

    public StudentRepositoryImpl(){
        this.conn = DatabaseConnectionManager.getDatabaseConnection();
    }

    @Override
    public boolean create(Student student) {

        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            //preparedStatement.setInt(1 , student.getId());
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setDate(3, student.getEnrollmentDate());
            preparedStatement.setString(4,student.getCpr());
            preparedStatement.executeUpdate();
            System.out.println("Succesfully added Student");
        }
        catch (SQLException e){
            System.out.println("Failed to insert data " + e);
        }
        return false;
    }

    @Override
    public Student read(int id) {
        Student studentToReturn = new Student();
        try {
            PreparedStatement getSingleStudent = conn.prepareStatement("SELECT * FROM student WHERE id=" + id);
            ResultSet rs = getSingleStudent.executeQuery();
            while(rs.next()){
                studentToReturn.setId(rs.getInt(1));
                studentToReturn.setFirstName(rs.getString(2));
                studentToReturn.setLastName(rs.getString(3));
                studentToReturn.setEnrollmentDate(rs.getDate(4));
                studentToReturn.setCpr(rs.getString(5));

            }
        }
        catch(SQLException s){
            s.printStackTrace();
        }
        return studentToReturn;
    }

    @Override
    public List<Student> readAll() {
        List<Student> allStudents = new ArrayList<Student>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM student");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student tempStudent = new Student();
                tempStudent.setId(rs.getInt(1));
                tempStudent.setFirstName(rs.getString(2));
                tempStudent.setLastName(rs.getString(3));
                tempStudent.setEnrollmentDate(rs.getDate(4));
                tempStudent.setCpr(rs.getString(5));
                allStudents.add(tempStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;
    }

    @Override
    public boolean update(Student student) {

        return false;
    }

    @Override
    public boolean delete(int id) {

        try {

            PreparedStatement deleteSingleStudent = conn.prepareStatement("DELETE FROM student where id =?");
            deleteSingleStudent.setInt(1,id);
            deleteSingleStudent.executeUpdate();
            return true;
        }
        catch(SQLException s){
            s.printStackTrace();
        }
        return false;


    }
}
