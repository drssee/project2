package com.example.demo2.service;

import com.example.demo2.dao.TodoDAO;
import com.example.demo2.dto.TodoListDTO;
import com.example.demo2.domain.TodoListVO;
import com.example.demo2.util.MapperUtil;
import com.example.demo2.util.TodoDAOUtil;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;

import java.util.ArrayList;
import java.util.List;

class TodoServiceTest {
    TodoDAO todoDAO= TodoDAOUtil.INSTANCE.get();
    ModelMapper modelMapper= MapperUtil.INSTANCE.get();

    //리스트 가져다주기
    @Test
    public void selectAllFromList() throws Exception {
        List<TodoListDTO> todoListDTO = new ArrayList<>();
        List<TodoListVO> todoListVo = todoDAO.selectAllFromList();
        for(TodoListVO vo : todoListVo){
            todoListDTO.add(modelMapper.map(vo, TodoListDTO.class));
        }
        System.out.println(todoListDTO);
        Assert.isTrue(todoListDTO.size()>0);
    }
}