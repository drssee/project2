package com.example.demo2.controller;

import com.example.demo2.dto.TodoListDTO;
import com.example.demo2.util.TodoServiceutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="todoUpdateController",value="/todo/update")
public class TodoUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //모든 todolist를 false로
        try {
            HttpSession session = req.getSession();
            TodoServiceutil.INSTANCE.get().updateAllTodo();
            List<TodoListDTO> todoList = TodoServiceutil.INSTANCE.get().selectAllFromList();
            session.setAttribute("todoList",todoList);
        } catch (SQLException e) {
            e.printStackTrace();
            PrintWriter out = resp.getWriter();
            out.println("<script>");
            out.println("alert('reset failed')");
            out.println("</script>");

        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = resp.getWriter();
            out.println("<script>");
            out.println("alert('can`t load list')");
            out.println("</script>");
        }
        resp.sendRedirect("/todo/list");
    }
}
