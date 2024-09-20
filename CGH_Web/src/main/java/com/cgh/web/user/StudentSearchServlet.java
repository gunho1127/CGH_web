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

/**
 * 학생 정보를 조회하는 서블릿
 */
@WebServlet("/searchStudent.do") // URL 패턴이 /searchStudent.do인 요청을 처리하는 서블릿
public class StudentSearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = new StudentDAO(); // 학생 데이터 액세스 객체

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 학생 ID를 추출
        int studentId = Integer.parseInt(request.getParameter("student_id"));

        // 학생 ID로 학생 정보를 조회
        StudentVO student = studentDAO.getStudentById(studentId);

        // 응답의 콘텐츠 유형과 문자 인코딩 설정
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // HTML 페이지 작성
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
        
        // 학생 정보를 테이블 형식으로 출력
        if (student != null) {
            out.println("            <table>");
            out.println("                <tr><th>이름:</th><td>" + student.getName() + "</td></tr>");
            out.println("                <tr><th>학번:</th><td>" + student.getStudentid() + "</td></tr>");
            out.println("                <tr><th>학과:</th><td>" + student.getMajor() + "</td></tr>");
            out.println("                <tr><th>전화번호:</th><td>" + student.getPhonenumber() + "</td></tr>");
            out.println("            </table>");
        } else {
            // 학생 정보를 찾을 수 없을 경우 메시지와 다시 조회 버튼 출력
            out.println("            <p>학생 정보를 찾을 수 없습니다.</p>");
            out.println("            <a href='student_search.html'><button>다시조회</button></a>");
        }

        out.println("        </div>");
        out.println("    </main>");
        out.println("</body>");
        out.println("</html>");
    }
}
