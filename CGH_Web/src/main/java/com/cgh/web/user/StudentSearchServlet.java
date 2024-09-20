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

@WebServlet("/searchStudent.do")
public class StudentSearchServlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("student_id"));

        StudentVO student = studentDAO.getStudentById(studentId);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='ko'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>학생 조회</title>");
        out.println("    <link rel='stylesheet' href='css/styles.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <header>");
        out.println("        <nav>");
        out.println("            <ul>");
        out.println("                <li><a href='student_insert.html'>학생 등록</a></li>");
        out.println("                <li><a href='student_list.html'>전체 학생 목록</a></li>");
        out.println("                <li><a href='student_search.html'>학생 조회</a></li>");
        out.println("                <li><a href='student_update.html'>학생 정보 수정</a></li>");
        out.println("            </ul>");
        out.println("        </nav>");
        out.println("    </header>");
        out.println("    <main>");
        out.println("        <div class='container'>");
        out.println("            <h1>학생 조회</h1>");
        
        if (student != null) {
            out.println("            <table>");
            out.println("                <tr><th>이름:</th><td>" + student.getName() + "</td></tr>");
            out.println("                <tr><th>학번:</th><td>" + student.getStudentid() + "</td></tr>");
            out.println("                <tr><th>학과:</th><td>" + student.getMajor() + "</td></tr>");
            out.println("                <tr><th>전화번호:</th><td>" + student.getPhonenumber() + "</td></tr>");
            out.println("            </table>");
        } else {
            out.println("            <p>학생 정보를 찾을 수 없습니다.</p>");
            out.println("            <a href='student_search.html'><button>다시조회</button><a>");
        }

        out.println("        </div>");
        out.println("    </main>");
        out.println("</body>");
        out.println("</html>");
    }
}
