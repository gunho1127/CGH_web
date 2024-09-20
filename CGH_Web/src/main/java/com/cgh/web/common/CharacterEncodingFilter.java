package com.cgh.web.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;

/**
 * 서블릿 필터 구현 클래스: 요청과 응답의 문자 인코딩을 설정하는 필터
 */
@WebFilter(
    urlPatterns = { "*.do" }, // *.do 패턴에 매칭되는 모든 요청에 대해 이 필터가 적용됨
    initParams = @WebInitParam(name = "studentEncoding", value = "UTF-8") // 초기화 파라미터로 문자 인코딩을 설정
)
public class CharacterEncodingFilter extends HttpFilter implements Filter {

    private static final long serialVersionUID = 1L; 
    private String encoding; 

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 요청의 문자 인코딩 설정
        request.setCharacterEncoding(encoding);

        // 응답의 Content-Type 설정
        response.setContentType("text/html;charset=" + encoding);

        // 필터 체인으로 요청을 전달
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        // 초기화 파라미터에서 문자 인코딩 값을 읽어옴
        encoding = config.getInitParameter("studentEncoding");
    }
}
