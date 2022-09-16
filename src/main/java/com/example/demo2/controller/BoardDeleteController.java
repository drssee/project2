package com.example.demo2.controller;

import com.example.demo2.dto.PageRequestDTO;
import com.example.demo2.util.BoardServiceUtil;
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

@WebServlet(name="boardRemoveController",value="/todo/board/delete")
@Log4j2
public class BoardDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/board/delte (get) ............");
        Integer bno = Integer.parseInt(req.getParameter("bno"));
        try {
            BoardServiceUtil.INSTANCE.get().delete(bno);
        } catch (SQLException e) {
            e.printStackTrace();
            PrintWriter out = resp.getWriter();
            out.println("<script>");
            out.println("alert('delete failed');");
            out.println("window.location = '/todo/list';");
            out.println("</script>");
            out.flush();
        }
//        PrintWriter out = resp.getWriter();
//        out.println("<script>");
//        out.println("alert('delete succeed');");
//        out.println("self.location='/todo/list'");
//        out.println("</script>");
//        out.close();
        resp.sendRedirect("/todo/list");
    }
}
