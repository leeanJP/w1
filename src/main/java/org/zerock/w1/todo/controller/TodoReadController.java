package org.zerock.w1.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // /todo/read?tno=1
        try {
            log.info(req.getParameter("tno"));

            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO dto = todoService.get(tno);

            req.setAttribute("dto", dto);

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req,resp);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServletException("read error");
        }


    }
}
