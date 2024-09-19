package com.cgh.web.user;

import com.cgh.biz.student.StudentDAO;
import com.cgh.biz.student.StudentVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listStudents.do")
public class StudentListServlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentVO> studentList = studentDAO.getAllStudents();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='ko'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>전체 학생 목록</title>");
        out.println("    <link rel='stylesheet' href='css/styles.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <header>");
        out.println("        <nav>");
        out.println("            <ul>");
        out.println("                <li><a href='student_insert.html'>학생 등록</a></li>");
        out.println("                <li><a href='listStudents.do'>전체 학생 목록</a></li>");
        out.println("                <li><a href='student_search.html'>학생 조회</a></li>");
        out.println("                <li><a href='student_update.html'>학생 정보 수정</a></li>");
        out.println("            </ul>");
        out.println("        </nav>");
        out.println("    </header>");
        out.println("    <main>");
        out.println("        <div class='container'>");
        out.println("            <h1>전체 학생 목록</h1>");
        out.println("            <table>");
        out.println("                <thead>");
        out.println("                    <tr>");
        out.println("                        <th>학번</th>");
        out.println("                        <th>이름</th>");
        out.println("                        <th>학과</th>");
        out.println("                        <th>전화번호</th>");
        out.println("                    </tr>");
        out.println("                </thead>");
        out.println("                <tbody>");
        
        for (StudentVO student : studentList) {
            out.println("                    <tr>");
            out.println("                        <td>" + student.getStudentid() + "</td>");
            out.println("                        <td>" + student.getName() + "</td>");
            out.println("                        <td>" + student.getMajor() + "</td>");
            out.println("                        <td>" + student.getPhonenumber() + "</td>");
            out.println("                    </tr>");
        }

        out.println("                </tbody>");
        out.println("            </table>");
        out.println("        </div>");
        out.println("    </main>");
        out.println("</body>");
        out.println("</html>");
    }
}
