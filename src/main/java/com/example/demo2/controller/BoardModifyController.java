package com.example.demo2.controller;

import com.example.demo2.dto.BoardDTO;
import com.example.demo2.util.BoardServiceUtil;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="boardModifyController",value="/board/modify")
@Log4j2
public class BoardModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bno = Integer.parseInt(req.getParameter("bno"));
        String writer = req.getParameter("writer");
        //bno와 writer가 일치하는 게시물을 가져와서 수정한다
        BoardDTO boardDTO = null;
        try {
            boardDTO = BoardServiceUtil.INSTANCE.get().selectOne(bno,writer);
        } catch (SQLException e) {
            e.printStackTrace();
            //오류페이지 만들어서 파람 에러? 내일 테스트한번해보자
            throw new RuntimeException(e);
        }
        req.setAttribute("boardDTO",boardDTO);

        //일치하는 게시물이 없을경우 알림 뜨고 리스트로(post)에서 처리
        req.getRequestDispatcher("/WEB-INF/modify.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer bno = Integer.valueOf(req.getParameter("bno"));
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        log.info("ccc+"+content);
        BoardDTO boardDTO=null;
        try {
            boardDTO = BoardServiceUtil.INSTANCE.get().selectOne(bno,writer);
            boardDTO.setTitle(title);
            boardDTO.setContent(content);
            BoardServiceUtil.INSTANCE.get().update(boardDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/todo/list");
    }
}
