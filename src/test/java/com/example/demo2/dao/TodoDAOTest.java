package com.example.demo2.dao;

import com.example.demo2.dto.TodoListDTO;
import com.example.demo2.domain.TodoListVO;
import com.example.demo2.util.ConnectionUtil;
import com.example.demo2.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoDAOTest {

    @Test
    void selectAllFromList() throws SQLException {
        String sql = "select * from list";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<TodoListDTO> list = new ArrayList<>();
        while(rs.next()){
            TodoListDTO todoListDTO = TodoListDTO.builder()
                    .title(rs.getString("title"))
                    .isFinished(rs.getBoolean("isFinished"))
                    .build();
            list.add(todoListDTO);
        }
        System.out.println(list);
        Assert.isTrue(list.size()>0);
    }
    @Test
    public void registerList() throws SQLException {
        TodoListDTO todoListDTO = TodoListDTO.builder()
                .title("tmp")
                .isFinished(false)
                .build();
        //넘어온 dto를 db를 위한 vo로 변경
        TodoListVO todoListVO = MapperUtil.INSTANCE.get().map(todoListDTO, TodoListVO.class);
        String sql = "insert into list (title, isFinished) values (?,?); ";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, todoListVO.getTitle());
        pstmt.setBoolean(2, todoListVO.isFinished());
        Assert.isTrue(pstmt.executeUpdate()==1);
    }
    @Test
    public void rowCnt() throws SQLException {
        String sql = "select count(*) from list";
        Connection conn=ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        System.out.println(rs.next());
        Assert.isTrue(rs.getInt(1)>=10);
    }

    @Test
    public void deleteTodo() throws SQLException {
        String sql = "delete from list where tno=?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,13);
        assertTrue(pstmt.executeUpdate()==1);
    }

    @Test
    public void updateTodo() throws SQLException {
        int isFinished = selectIsFinished(14);
        String sql = "update list set isFinished = ? where tno = ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,isFinished==1?0:1);
        pstmt.setInt(2,14);
        assertTrue(pstmt.executeUpdate()==1);
    }
    @Test
    public void updateAllTodo() throws SQLException {
        String sql = "update list set isFinished = ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setInt(1,isFinished==1?0:1);
        pstmt.setInt(1,0);
        int result = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public int selectIsFinished(int tno) throws SQLException {
        String sql = "select isFinished from list where tno = ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,tno);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}