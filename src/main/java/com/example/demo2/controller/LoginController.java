package com.example.demo2.controller;

import com.example.demo2.dto.PageRequestDTO;
import com.example.demo2.dto.TodoDTO;
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

@WebServlet(name="loginController",value="/todo/login")
@Log4j2
public class LoginController extends HttpServlet {
    //get방식 post방식 두개

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/login (get) .........");
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //DB에 저장된 유저가 맞는지확인
        log.info("/todo/login (post) .........");
        String id=req.getParameter("id");
        String pwd=req.getParameter("pwd");
        HttpSession session = req.getSession();
        TodoService todoService = TodoServiceutil.INSTANCE.get();
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        try {
            TodoDTO todoDTO = todoService.select(id);
            log.info("todoDTO:"+todoDTO); //user의 id pwd 를 멤버변수로 가진다
            if(todoDTO==null||!(todoDTO.getPwd().equals(pwd))){
                resp.sendRedirect("/todo/login?error=password error");
            }
            else{
                session.setAttribute("loginInfo",todoDTO);//로그인시도하는 객체정보
                session.setAttribute("todoList",todoService.selectAllFromList());//list의 모든 리스트
//                session.setAttribute("pageRequestDTO",pageRequestDTO);
                resp.sendRedirect("/todo/list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/todo/login?error=id error");
        }
    }
}
