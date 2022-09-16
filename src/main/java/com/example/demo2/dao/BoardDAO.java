package com.example.demo2.dao;

import com.example.demo2.dto.BoardDTO;
import com.example.demo2.dto.PageRequestDTO;
import com.example.demo2.dto.PageResponseDTO;
import com.example.demo2.util.ConnectionUtil;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BoardDAO {

    //2.게시판 작성
    //3.게시판 수정/삭제
    //4.게시판 검색

    //0 (전체갯수)
    public Integer getCount() throws SQLException {
        String sql = "select count(*) from board";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Integer result = rs.getInt(1);
        rs.close();
        pstmt.close();
        conn.close();
        return result;
    }

    //1.(전체목록)
    public List<BoardDTO> selectAll(PageRequestDTO pageRequestDTO, HttpSession session) throws SQLException {
        String sql = "select * from board limit ?,?;";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,pageRequestDTO.getSkip());
        pstmt.setInt(2,pageRequestDTO.getSize());
        ResultSet rs = pstmt.executeQuery();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        while(rs.next()) {
            BoardDTO boardDTO = BoardDTO.builder()
                    .bno(rs.getInt(1))
                    .title(rs.getString(2))
                    .content(rs.getString(3))
                    .writer(rs.getString(4))
                    .datetime(rs.getTimestamp(5))
                    .build();
            log.info("selectAll_dto:" + boardDTO);
            boardDTOList.add(boardDTO);
        }
        PageResponseDTO pageResponseDTO = PageResponseDTO
                .builder()
                .pageRequestDTO(pageRequestDTO)
                .build();
        log.info("bdbdbdbd:"+pageResponseDTO);
        session.setAttribute("pageResponseDTO",pageResponseDTO);

        rs.close();
        pstmt.close();
        conn.close();
        return boardDTOList;
    }

    //2.
    public int insert(BoardDTO boardDTO) throws SQLException {
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
        return rowCnt;
    }

    public int delete(Integer bno) throws SQLException{
        String sql="delete from board where bno = ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,bno);
        int rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return rowCnt;
    }
}
