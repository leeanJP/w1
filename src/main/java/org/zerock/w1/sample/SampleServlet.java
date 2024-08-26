package org.zerock.w1.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/sample")
public class SampleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SampleServlet do Get");

        req.getHeaderNames(); // HTTP 헤더 내용을 찾아내는 기능
        //req.getHeader("name");

        System.out.println(req.getRemoteAddr()); // 접속한 사용자의 IP 주소

        req.getMethod();
        req.getRequestURL();
        req.getCookies();

        req.setAttribute("test", "test");
        req.getRequestDispatcher("/sample.jsp").forward(req, resp);

        resp.setHeader("name", "value");
        resp.setStatus(404);
        //resp.addCookie();
        resp.sendRedirect("/index.jsp");
    }
}
