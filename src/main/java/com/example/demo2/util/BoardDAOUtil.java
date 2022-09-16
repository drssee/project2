package com.example.demo2.util;

import com.example.demo2.dao.BoardDAO;

public enum BoardDAOUtil {
    INSTANCE;
    private BoardDAO boardDAO;
    private BoardDAOUtil(){
        boardDAO=new BoardDAO();
    }

    public BoardDAO get(){
        return this.boardDAO;
    }
}
