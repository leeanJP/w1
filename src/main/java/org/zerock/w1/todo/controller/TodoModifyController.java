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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoModifyController", value = "/todo/modify")
@Log4j2
public class TodoModifyController  extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO dto = todoService.get(tno);
            //데이터 담기
            req.setAttribute("dto" ,dto);
            req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req,resp);

        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServletException("modify get error........");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String finishedStr = req.getParameter("finished"); // on
        log.info("======finishedStr===========" + finishedStr);

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(Long.parseLong(req.getParameter("tno")))
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATEFORMATTER))
                .finished(finishedStr != null && finishedStr.equals("on"))
                .build();

        log.info("/todo/modify Post");
        log.info(todoDTO);


        try {
            todoService.modify(todoDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("/todo/list");
    }
}
