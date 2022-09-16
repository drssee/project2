package com.example.demo2.service;

import com.example.demo2.dao.BoardDAO;
import com.example.demo2.dto.BoardDTO;
import com.example.demo2.dto.PageRequestDTO;
import com.example.demo2.util.BoardDAOUtil;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class BoardService {
    BoardDAO boardDAO;
    public BoardService(){
        boardDAO= BoardDAOUtil.INSTANCE.get();
    }

    public List<BoardDTO> selectAll(PageRequestDTO pageRequestDTO, HttpSession session) throws SQLException {
//        return boardDAO.selectAll();
        return boardDAO.selectAll(pageRequestDTO,session);
    }

    public Integer getCount() throws SQLException {
        return boardDAO.getCount();
    }

    public int delete(Integer bno) throws SQLException{
        return boardDAO.delete(bno);
    }
}
