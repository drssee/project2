package com.example.demo2.controller;

import com.example.demo2.dto.TodoDTO;
import com.example.demo2.util.BoardServiceUtil;
import com.example.demo2.util.TodoServiceutil;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="boardWriteController",value="/board/write")
@Log4j2
public class BoardWriteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/board/write (get) .......");
        req.getRequestDispatcher("/WEB-INF/write.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        HttpSession session = req.getSession();
        TodoDTO todoDTO = (TodoDTO) session.getAttribute("loginInfo");
        String writer = todoDTO.getId();

        try {
            BoardServiceUtil.INSTANCE.get().insert(title,content,writer);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/todo/list");
    }
}
