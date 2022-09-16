package com.example.demo2.controller;

import com.example.demo2.dto.BoardDTO;
import com.example.demo2.dto.PageRequestDTO;
import com.example.demo2.dto.PageResponseDTO;
import com.example.demo2.util.BoardServiceUtil;
import lombok.SneakyThrows;
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
import java.util.List;

@WebServlet(name="todoListController",value="/todo/list")
@Log4j2
public class ListController extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //로그인체크후
        //list view or /todo/login
        log.info("todo list (get) ..... ");

        HttpSession session = req.getSession();
        if(session.getAttribute("loginInfo")!=null){//로그인on
            //로그인이 확인되면 board 의 리스트를 읽어온다
            PageRequestDTO pageRequestDTO = null;
            List<BoardDTO> boardDtoList = null;
            PageResponseDTO pageResponseDTO = null;
            try {
                if(req.getParameter("page")==null||req.getParameter("size")==null){
                    pageRequestDTO = PageRequestDTO.builder()
                            .build();
                }
                else {
                    pageRequestDTO = PageRequestDTO.builder()
                            .page(Integer.parseInt(req.getParameter("page")))
                            .size(Integer.parseInt(req.getParameter("size")))
                            .build();
                    log.info("lilili:"+pageRequestDTO);
                }

                boardDtoList = BoardServiceUtil.INSTANCE.get().selectAll(pageRequestDTO,session);
                pageResponseDTO = (PageResponseDTO) session.getAttribute("pageResponseDTO");
                log.info("lilili2"+pageResponseDTO);
            } catch (SQLException e) {
                e.printStackTrace();
                //목록을 불러오지 못하면 로그인페이지로 리다이렉트
                PrintWriter out = resp.getWriter();
                out.println("<script>");
                out.println("alert('board목록을 불러오지 못했습니다')");
                out.println("self.location='/todo/login?error=board loading err'");
                out.println("</script>");
                out.flush();
            }
            req.setAttribute("boardDtoList",boardDtoList);
            req.setAttribute("pageResponseDTO",pageResponseDTO);
            req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req,resp);
        }
        resp.sendRedirect("/todo/login");
    }
}
