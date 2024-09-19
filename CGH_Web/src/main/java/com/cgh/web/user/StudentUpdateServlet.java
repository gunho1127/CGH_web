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

        StudentVO student = new StudentVO();
        student.setStudentid(studentId);
        student.setMajor(major);
        student.setPhonenumber(phoneNumber);

        studentDAO.updateStudent(student);

        response.sendRedirect("listStudents.do");
    }
}
