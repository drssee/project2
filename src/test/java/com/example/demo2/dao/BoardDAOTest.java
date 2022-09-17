package com.example.demo2.dao;

import com.example.demo2.dto.BoardDTO;
import com.example.demo2.dto.PageRequestDTO;
import com.example.demo2.util.ConnectionUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
class BoardDAOTest {

    @Test
    void insert() throws SQLException {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("title1")
                .content("content2")
                .writer("writer3")
                .build();
        String sql = "insert into board (title, content, writer)\n" +
                "values (?,?,?);";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,boardDTO.getTitle());
        pstmt.setString(2,boardDTO.getContent());
        pstmt.setString(3,boardDTO.getWriter());
        pstmt.executeUpdate();
    }

    @Test
    public void selectAll() throws SQLException {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        String sql = "select * from board limit ?,?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,pageRequestDTO.getSkip());
        pstmt.setInt(2,pageRequestDTO.getPage());
        ResultSet rs = pstmt.executeQuery();
        List<BoardDTO> dtoList = new ArrayList<>();
        while(rs.next()){
            BoardDTO boardDTO = BoardDTO.builder()
                    .bno(rs.getInt(1))
                    .title(rs.getString(2))
                    .content(rs.getString(3))
                    .writer(rs.getString(4))
                    .datetime(rs.getTimestamp(5))
                    .build();
            log.info("selectAll_dto:"+boardDTO);
            dtoList.add(boardDTO);
        }
//        PageResponseDTO pageResponseDTO = PageResponseDTO
//                .builder()
//                .pageRequestDTO(pageRequestDTO)
//                .build();
//        log.info("bdbdbdbd:"+pageResponseDTO);
    }

    @Test
    public void deleteTest() throws SQLException {
        String sql = "delete\n" +
                "from board\n" +
                "where bno = 14;";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Test
    public void insertTest() throws Exception{
        String sql = "insert into board (title, content, writer)\n" +
                "values (?,?,?);";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,"title");
        pstmt.setString(2,"content");
        pstmt.setString(3,"writer");
        int rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}