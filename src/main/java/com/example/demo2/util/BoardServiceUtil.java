package com.example.demo2.util;

import com.example.demo2.service.BoardService;
import com.example.demo2.service.TodoService;

public enum BoardServiceUtil {
    INSTANCE;
    private BoardService boardService;
    BoardServiceUtil(){
        boardService=new BoardService();
    }
    public BoardService get(){
        return boardService;
    }
}
