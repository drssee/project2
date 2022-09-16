package com.example.demo2.util;

import com.example.demo2.service.TodoService;

public enum TodoServiceutil{
    INSTANCE;
    private TodoService todoService;
    TodoServiceutil(){
        todoService=new TodoService();
    }
    public TodoService get(){
        return todoService;
    }
}
