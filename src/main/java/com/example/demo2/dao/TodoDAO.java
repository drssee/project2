package com.example.demo2.dao;

import com.example.demo2.dto.TodoListDTO;
import com.example.demo2.domain.TodoListVO;
import com.example.demo2.domain.TodoVO;
import com.example.demo2.util.ConnectionUtil;
import com.example.demo2.util.MapperUtil;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TodoDAO {
    //1.로그인체크
    public TodoVO selectUser(String id) throws SQLException {
        String sql = "select * from todo.user where id=?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        TodoVO vo = TodoVO.builder()
                .id(rs.getString(1))
                .pwd(rs.getString(2))
                .build();
        pstmt.close();
        conn.close();
        return vo;//vo를 변환해서 dto로 만들고 pwd 비교
    }
    //2.list.jsp에 전체list 출력
    public List<TodoListVO> selectAllFromList() throws Exception{
        String sql = "select * from list";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<TodoListVO> list = new ArrayList<>();
        while(rs.next()){
            TodoListVO todoListVO = TodoListVO.builder()
                    .tno(rs.getInt("tno"))
                    .title(rs.getString("title"))
                    .isFinished(rs.getBoolean("isFinished"))
                    .build();
            list.add(todoListVO);
        }
        pstmt.close();
        conn.close();
        return list;
    }

    //3.todolist 추가
    public boolean registerList(TodoListDTO todoListDTO) throws SQLException {
        if(rowCnt()>=5){
            log.info(rowCnt());
            log.info("todo list가 가득 찼습니다");
            return false;
        }
        //넘어온 dto를 db를 위한 vo로 변경
        TodoListVO todoListVO = MapperUtil.INSTANCE.get().map(todoListDTO, TodoListVO.class);
        String sql = "insert into list (title, isFinished) values (?,?); ";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, todoListVO.getTitle());
        pstmt.setBoolean(2, todoListVO.isFinished());
        boolean result = pstmt.executeUpdate()==1?true:false;
        pstmt.close();
        conn.close();
        return result;
    }
    public int rowCnt() throws SQLException {
        String sql = "select count(*) from list";
        Connection conn=ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int result = rs.getInt(1);
        rs.close();
        pstmt.close();
        conn.close();
        return result;
    }

    //4.선택한 list 삭제
    public int deleteTodo(int tno) throws SQLException {
        String sql = "delete from list where tno=?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,tno);
        int result = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return result;
    }

    //5.선택한 list의 isfinished를 업데이트
    public int updateTodo(int tno) throws SQLException {
        int isFinished = selectIsFinished(tno);
        int setInt=0;
        if(isFinished==1){
            setInt=0;
        }
        else if(isFinished==0){
            setInt=1;
        }
        String sql = "update list set isFinished = ? where tno = ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setInt(1,isFinished==1?0:1);
        pstmt.setInt(1,setInt);
        pstmt.setInt(2,tno);
        int result = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return result;
    }

    public void updateAllTodo() throws SQLException {
        String sql = "update list set isFinished = ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setInt(1,isFinished==1?0:1);
        pstmt.setInt(1,0);
        int result = pstmt.executeUpdate();
        pstmt.close();
    }

    public int selectIsFinished(int tno) throws SQLException {
        String sql = "select isFinished from list where tno = ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,tno);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int result = rs.getInt(1);
        rs.close();
        pstmt.close();
        conn.close();
        return result;
    }
}
