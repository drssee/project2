package com.example.demo2.controller;

import com.example.demo2.service.TodoService;
import com.example.demo2.util.TodoServiceutil;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name="modifyController",value="/todo/modify")
@Log4j2
public class TodoModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoService todoService= TodoServiceutil.INSTANCE.get();
        log.info("/todo/modify (get) ............");
        try {
            if(!(todoService.updateTodo(Integer.parseInt(req.getParameter("tno"))))){
                PrintWriter out = resp.getWriter();
                out.println("<script>");
                out.println("alert('modify failed');");
                out.println("window.location = '/todo/list';");
                out.println("</script>");
                out.flush();
            }
            HttpSession session = req.getSession();
            session.setAttribute("todoList",todoService.selectAllFromList());//list의 모든 리스트
            resp.sendRedirect("/todo/list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
