package com.example.demo2.dao;

import com.example.demo2.domain.BoardVO;
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
import java.util.Date;
import java.util.List;

@Log4j2
public class BoardDAO {

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

    public List<BoardVO> selectAll(PageRequestDTO pageRequestDTO, HttpSession session) throws SQLException {
        String sql = "select * from board limit ?,?;";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,pageRequestDTO.getSkip());
        pstmt.setInt(2,pageRequestDTO.getSize());
        ResultSet rs = pstmt.executeQuery();
        List<BoardVO> boardVOList = new ArrayList<>();
        while(rs.next()) {
            BoardVO boardVO = BoardVO.builder()
                    .bno(rs.getInt(1))
                    .title(rs.getString(2))
                    .content(rs.getString(3))
                    .writer(rs.getString(4))
                    .datetime(rs.getTimestamp(5))
                    .build();
            boardVOList.add(boardVO);
        }
        rs.close();
        pstmt.close();
        conn.close();

        PageResponseDTO pageResponseDTO = PageResponseDTO
                .builder()
                .pageRequestDTO(pageRequestDTO)
                .build();
        session.setAttribute("pageResponseDTO",pageResponseDTO);
        //들어옴
        return boardVOList;
    }

    public BoardVO selectOne(Integer bno,String writer) throws SQLException{
        String sql = "select * from board where bno=? and writer=?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,bno);
        pstmt.setString(2,writer);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        BoardVO vo  = BoardVO.builder()
                    .bno(rs.getInt(1))
                    .title(rs.getString(2))
                    .content(rs.getString(3))
                    .writer(rs.getString(4))
                    .datetime(rs.getTimestamp(5))
                    .build();
        rs.close();
        pstmt.close();
        conn.close();
        return vo;
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

    public int insert(BoardVO vo) throws SQLException {
        String sql = "insert into board (title, content, writer)\n" +
                "values (?,?,?);";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,vo.getTitle());
        pstmt.setString(2,vo.getContent());
        pstmt.setString(3,vo.getWriter());
        int rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return rowCnt;
    }

    public int update(BoardVO vo) throws SQLException {
        String sql = "update board\n" +
                "set  title = ? , content = ?\n" +
                "where bno=? and writer=?;";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,vo.getTitle());
        pstmt.setString(2,vo.getContent());
        pstmt.setInt(3,vo.getBno());
        pstmt.setString(4,vo.getWriter());
        int rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return rowCnt;
    }
}
