package com.example.demo.repositories;

import com.example.demo.models.Student;
import com.example.demo.util.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class StudentRepositoryImpl implements IStudentRepository {
    private Connection conn;

    public StudentRepositoryImpl(){
        this.conn = DatabaseConnectionManager.getDatabaseConnection();
    }

    @Override
    public boolean create(Student student) {
        String sql = "INSERT INTO students (id, fname, lname, enrollmentDate, cpr) VALUES (default,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            //preparedStatement.setInt(1 , student.getId());
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setDate(3, Date.valueOf(student.getEnrollmentDate()));
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
            PreparedStatement getSingleStudent = conn.prepareStatement("SELECT * FROM students WHERE id=" + id);
            ResultSet rs = getSingleStudent.executeQuery();
            while(rs.next()){
                studentToReturn.setId(rs.getInt(1));
                studentToReturn.setFirstName(rs.getString(2));
                studentToReturn.setLastName(rs.getString(3));
                studentToReturn.setEnrollmentDate(String.valueOf(rs.getDate(4)));
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student tempStudent = new Student();
                tempStudent.setId(rs.getInt(1));
                tempStudent.setFirstName(rs.getString(2));
                tempStudent.setLastName(rs.getString(3));
                tempStudent.setEnrollmentDate(String.valueOf(rs.getDate(4)));
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
        try {

            PreparedStatement myStmt = conn.prepareStatement("UPDATE students SET fname = ?, lname = ?, enrollmentDate = ?, cpr = ? WHERE id =?;");

            myStmt.setString(1, student.getFirstName());
            myStmt.setString(2, student.getLastName());
            myStmt.setString(3, student.getEnrollmentDate());
            myStmt.setString(4, student.getCpr());
            myStmt.setInt(5, student.getId());

            myStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to execute update " + e);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if(Student.getId() == id) {
            String sql = "DELETE FROM students WHERE id = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);

                // set the corresponding param
                pstmt.setInt(1, id);
                // execute the delete statement
                pstmt.executeUpdate();


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println("Fail");
        }
        return false;
    }
}
