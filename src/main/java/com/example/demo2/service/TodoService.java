package com.example.demo2.service;

import com.example.demo2.dao.TodoDAO;
import com.example.demo2.dto.TodoListDTO;
import com.example.demo2.domain.TodoListVO;
import com.example.demo2.dto.TodoDTO;
import com.example.demo2.domain.TodoVO;
import com.example.demo2.util.MapperUtil;
import com.example.demo2.util.TodoDAOUtil;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoService {
    //todoDAO주입
    TodoDAO todoDAO;
    //modelMapper주입
    ModelMapper modelMapper;
    public TodoService(){
        todoDAO= TodoDAOUtil.INSTANCE.get();
        modelMapper= MapperUtil.INSTANCE.get();
    }
    //생성자로 주입 util의 생성자에 todoservice생성자가 있음

    //로그인체크
    public TodoDTO select(String id) throws SQLException {
        TodoVO vo = todoDAO.selectUser(id);
        return modelMapper.map(vo,TodoDTO.class);
    }
    //list.jsp로 db의 list 모두 가져오기
    public List<TodoListDTO> selectAllFromList() throws Exception {
//        List<ListVO> listVo = todoDAO.selectAllFromList();
//        ListDTO[] list = new ListDTO[listVo.size()];
//        for(int i=0;i< listVo.size();i++){
//            list[i]=modelMapper.map(listVo.get(i),ListDTO.class);
//        }
//        return list;
        List<TodoListVO> todoListVo = todoDAO.selectAllFromList();
        List<TodoListDTO> todoListDTO = new ArrayList<>();
        for(TodoListVO vo : todoListVo){
            todoListDTO.add(modelMapper.map(vo, TodoListDTO.class));
        }
        return todoListDTO;
    }

    //db에 list 추가
    public boolean registerList(TodoListDTO todoListDTO) throws SQLException {
        if(todoDAO.registerList(todoListDTO)){
            return true;
        }
        return false;
    }

    //선택한 list 삭제
    public boolean deleteTodo(int tno) throws SQLException {
        if(todoDAO.deleteTodo(tno)==1){
            return true;
        }
        return false;
    }

    //선택한 list의 isfinished 변경
    public boolean updateTodo(int tno) throws SQLException {
        if(todoDAO.updateTodo(tno)==1){
            return true;
        }
        return false;
    }

    public void updateAllTodo() throws SQLException {
        todoDAO.updateAllTodo();
    }
}
