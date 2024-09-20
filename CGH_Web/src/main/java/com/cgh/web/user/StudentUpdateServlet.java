package com.cgh.web.user;

import com.cgh.biz.student.StudentDAO;
import com.cgh.biz.student.StudentVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateStudent.do")
public class StudentUpdateServlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        String major = request.getParameter("department");
        String phoneNumber = request.getParameter("phone");

        // 해당 학번의 학생을 검색
        StudentVO student = studentDAO.getStudentById(studentId);

        if (student == null) {
            // 해당 학번의 학생이 없으면 경고 메시지 출력하고 다시 수정 페이지로 이동
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('일치하는 학생이 없습니다.');");
            response.getWriter().println("location.href='student_update.html';");
            response.getWriter().println("</script>");
        } else {
            // 학생 정보가 있으면 정보 수정
            student.setMajor(major);
            student.setPhonenumber(phoneNumber);
            studentDAO.updateStudent(student);

            // 수정 후 전체 학생 목록 페이지로 리다이렉트
            response.sendRedirect("listStudents.do");
        }
    }
}
