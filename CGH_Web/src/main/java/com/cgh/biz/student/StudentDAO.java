package com.cgh.biz.student;

import com.cgh.biz.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // SQL 명령어
    private static final String STUDENT_INSERT = "INSERT INTO STUDENT (studentid, name, major, phonenumber) VALUES (?, ?, ?, ?)";
    private static final String STUDENT_LIST = "SELECT * FROM STUDENT";
    private static final String STUDENT_GET = "SELECT * FROM STUDENT WHERE studentid = ?";
    private static final String STUDENT_UPDATE = "UPDATE STUDENT SET major = ?, phonenumber = ? WHERE studentid = ?";

    // 학생 등록 메서드
    public void insertStudent(StudentVO student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConnection(); // 데이터베이스 연결
            stmt = conn.prepareStatement(STUDENT_INSERT); // SQL 쿼리 준비
            // 학생 정보 설정
            stmt.setInt(1, student.getStudentid());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getMajor());
            stmt.setString(4, student.getPhonenumber());
            stmt.executeUpdate(); // 쿼리 실행
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리
        } finally {
            JDBCUtil.close(stmt, conn); // 자원 해제
        }
    }

    // 전체 학생 목록 조회 메서드
    public List<StudentVO> getAllStudents() {
        List<StudentVO> studentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection(); // 데이터베이스 연결
            stmt = conn.prepareStatement(STUDENT_LIST); // SQL 쿼리 준비
            rs = stmt.executeQuery(); // 쿼리 실행
            while (rs.next()) {
                // 학생 정보 객체 생성 및 설정
                StudentVO student = new StudentVO();
                student.setStudentid(rs.getInt("studentid"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setPhonenumber(rs.getString("phonenumber"));
                studentList.add(student); // 리스트에 추가
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리
        } finally {
            JDBCUtil.close(rs, stmt, conn); // 자원 해제
        }
        return studentList; // 학생 목록 반환
    }

    // 학생 ID로 학생 정보 조회 메서드
    public StudentVO getStudentById(int studentId) {
        StudentVO student = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection(); // 데이터베이스 연결
            stmt = conn.prepareStatement(STUDENT_GET); // SQL 쿼리 준비
            stmt.setInt(1, studentId); // 학생 ID 설정
            rs = stmt.executeQuery(); // 쿼리 실행
            if (rs.next()) {
                // 학생 정보 객체 생성 및 설정
                student = new StudentVO();
                student.setStudentid(rs.getInt("studentid"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setPhonenumber(rs.getString("phonenumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리
        } finally {
            JDBCUtil.close(rs, stmt, conn); // 자원 해제
        }
        return student; // 학생 정보 반환
    }

    // 학생 정보 수정 메서드
    public void updateStudent(StudentVO student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConnection(); // 데이터베이스 연결
            stmt = conn.prepareStatement(STUDENT_UPDATE); // SQL 쿼리 준비
            // 수정할 정보 설정
            stmt.setString(1, student.getMajor());
            stmt.setString(2, student.getPhonenumber());
            stmt.setInt(3, student.getStudentid());
            stmt.executeUpdate(); // 쿼리 실행
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리
        } finally {
            JDBCUtil.close(stmt, conn); // 자원 해제
        }
    }
}
