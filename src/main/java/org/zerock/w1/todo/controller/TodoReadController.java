package org.zerock.w1.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.zerock.w1.todo.dto.TodoDTO;
import org.zerock.w1.todo.service.TodoService;

import java.io.IOException;

@WebServlet(name = "todoReadController" , urlPatterns = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {

    /*
    * todo를 조회 했을 때 tno 값을 쿠키에 저장하고
    * 1-2-3
    * 쿠키 이름은 viewTodos
    * 쿠키 유효기간은 24시간
    * 이미 조회한 번호는 추가 X
    * */

    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            log.info(req.getParameter("tno"));

            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO dto = todoService.get(tno);

            req.setAttribute("dto", dto);

            //쿠키 찾기
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");

            String todoListStr = viewTodoCookie.getValue();

            boolean exist = false;

            if(todoListStr != null && todoListStr.indexOf(tno+"-") >=0){
                exist = true;
            }

            log.info("exist: " + exist);
            log.info("todoListStr 변경 전 : " + todoListStr);
            if(!exist){
                todoListStr += tno+"-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60*60*24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);

            }
            log.info("todoListStr 변경 후 : " + todoListStr);
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req,resp);

        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServletException("read error");
        }


    }

    private Cookie findCookie(Cookie[] cookies ,String cookieName){
        Cookie targetCookie = null;

        if(cookies != null && cookies.length > 0){
            for(Cookie ck : cookies){
                if(ck.getName().equals(cookieName)){
                    targetCookie = ck;
                    break;
                }
            }
        }

        if(targetCookie == null){
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }

        return targetCookie;
    }


}
