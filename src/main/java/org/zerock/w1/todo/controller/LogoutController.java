package org.zerock.w1.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet("/logout")
@Log4j2
public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("logout doPost");

        HttpSession session = req.getSession();

        session.removeAttribute("loginInfo");
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for(Cookie ck : cookies){
                if("remember-me".equals(ck.getName())){
                    ck.setMaxAge(0);
                    ck.setPath("/");
                    resp.addCookie(ck);
                }
            }
        }

        session.invalidate();

        resp.sendRedirect("/");
    }
}
