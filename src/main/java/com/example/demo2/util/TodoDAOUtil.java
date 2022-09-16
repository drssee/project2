package com.example.demo2.util;

import com.example.demo2.dao.TodoDAO;

public enum TodoDAOUtil {
    INSTANCE;
    private TodoDAO todoDAO;
    private TodoDAOUtil(){
        todoDAO=new TodoDAO();
    }
    public TodoDAO get(){
        return todoDAO;
    }
}
