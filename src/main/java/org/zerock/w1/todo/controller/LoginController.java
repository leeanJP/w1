package org.zerock.w1.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;
import org.zerock.w1.todo.dto.MemberDTO;
import org.zerock.w1.todo.service.MemberService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {
    /*
    * 사용자가 로그인 할 때 임의의 문자열을 생성하고 이를 데이터베이스에 보관
    * 쿠키에는 생성된 문자열을 값으로 삼고 유효기간은 1주일로 지정
    *
    * 현재 사용자의 세션에 로그인 정보가 없는 경우에만 쿠키를 확인
    * 쿠키 값과 데이터베이스의 값을 비교하고 같다면 사용자 정보를 읽어와서 세션에 사용자 정보를 추가
    *
    * UUID Universally Unique Identifier  128BIT 고유 식별자
    *
    * */


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login doGet...");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login doPost...");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        String auto = req.getParameter("auto");

        boolean rememberMe = auto != null && auto.equals("on");
        log.info("로그인 정보 저장 여부 : "+ rememberMe);
        try {
            MemberDTO dto = MemberService.INSTANCE.login(mid,mpw);

            if(rememberMe){
                String uuid = UUID.randomUUID().toString();
                MemberService.INSTANCE.updateUUID(mid, uuid);
                dto.setUuid(uuid);

                Cookie rememberCookie = new Cookie ("remember-me", uuid);
                rememberCookie.setMaxAge(60*60*24*7);
                rememberCookie.setPath("/");

                resp.addCookie(rememberCookie);


            }
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", dto);
            resp.sendRedirect("/todo/list");
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("/login?result=error");
        }




    }
}
