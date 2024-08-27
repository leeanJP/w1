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
import java.util.List;

@WebServlet(name = "todoListController" , urlPatterns = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("todo list do get......");

        List<TodoDTO> dtoList = null;
        try {
            dtoList = todoService.listAll();
            req.setAttribute("list", dtoList);
            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException(e);
        }
    }
}
