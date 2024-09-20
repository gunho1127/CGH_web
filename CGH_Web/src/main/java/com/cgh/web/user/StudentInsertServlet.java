package com.cgh.web.user;

import com.cgh.biz.student.StudentDAO;
import com.cgh.biz.student.StudentVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 학생 등록을 처리하는 서블릿
 */
@WebServlet("/insertStudent.do") // URL 패턴이 /insertStudent.do인 요청을 처리하는 서블릿
public class StudentInsertServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = new StudentDAO(); // 학생 데이터 액세스 객체

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼에서 전달된 데이터 추출
        String name = request.getParameter("name");
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        String major = request.getParameter("department");
        String phoneNumber = request.getParameter("phone");

        // StudentVO 객체 생성 및 설정
        StudentVO student = new StudentVO();
        student.setName(name);
        student.setStudentid(studentId);
        student.setMajor(major);
        student.setPhonenumber(phoneNumber);

        // 학생 정보 데이터베이스에 저장
        studentDAO.insertStudent(student);

        // 학생 목록 페이지로 리다이렉트
        response.sendRedirect("listStudents.do");
    }
}
