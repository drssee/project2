package com.example.demo2.controller;

import com.example.demo2.dto.TodoListDTO;
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

@WebServlet(name="todoRegister",value="/todo/register")
@Log4j2
public class TodoRegister extends HttpServlet {
    //list에서 get으로 도착->register.jsp로 보내야함

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/register (get) .......");
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/register (post) .......");
        HttpSession session = req.getSession();
        String title = req.getParameter("title");
        boolean isFinished = req.getParameter("isFinished").equals("1")?true:false;

        if(req.getParameter("title")==null||req.getParameter("title")==""){
            resp.sendRedirect("/todo/register?error=register error(title is null)");
        }

        //register.jsp 에서 넘어온 파라미터 내용을 db에 추가
        TodoListDTO todoListDTO = TodoListDTO.builder()
                        .title(title)
                        .isFinished(isFinished)
                        .build();
        try {
            if(TodoServiceutil.INSTANCE.get().registerList(todoListDTO)){
                //추가하면 list로 리다이렉트
                session.setAttribute("todoList", TodoServiceutil.INSTANCE.get().selectAllFromList());
                resp.sendRedirect("/todo/list");
            }
            else{//추가 실패하면
                resp.setContentType("text/html; charset=UTF-8");
                PrintWriter out = resp.getWriter();

                out.println("<script language='javascript'>");
                        out.println("alert('목록이 가득찼습니다')");
                        out.println("window.location = '/todo/list';");
                out.println("</script>");
                out.flush();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception n){
            n.printStackTrace();
        }
    }
}
