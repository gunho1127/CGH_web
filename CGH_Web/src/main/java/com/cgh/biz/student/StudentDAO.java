package com.cgh.biz.student;

import com.cgh.biz.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final String STUDENT_INSERT = "INSERT INTO STUDENTS (studentid, name, major, phonenumber) VALUES (?, ?, ?, ?)";
    private static final String STUDENT_LIST = "SELECT * FROM STUDENTS";
    private static final String STUDENT_GET = "SELECT * FROM STUDENTS WHERE studentid = ?";
    private static final String STUDENT_UPDATE = "UPDATE STUDENTS SET major = ?, phonenumber = ? WHERE studentid = ?";

    public void insertStudent(StudentVO student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(STUDENT_INSERT);
            stmt.setInt(1, student.getStudentid());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getMajor());
            stmt.setString(4, student.getPhonenumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public List<StudentVO> getAllStudents() {
        List<StudentVO> studentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(STUDENT_LIST);
            rs = stmt.executeQuery();
            while (rs.next()) {
                StudentVO student = new StudentVO();
                student.setStudentid(rs.getInt("studentid"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setPhonenumber(rs.getString("phonenumber"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return studentList;
    }

    public StudentVO getStudentById(int studentId) {
        StudentVO student = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(STUDENT_GET);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                student = new StudentVO();
                student.setStudentid(rs.getInt("studentid"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setPhonenumber(rs.getString("phonenumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return student;
    }

    public void updateStudent(StudentVO student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(STUDENT_UPDATE);
            stmt.setString(1, student.getMajor());
            stmt.setString(2, student.getPhonenumber());
            stmt.setInt(3, student.getStudentid());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }
}
