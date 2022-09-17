package com.example.demo2.service;

import com.example.demo2.dao.BoardDAO;
import com.example.demo2.domain.BoardVO;
import com.example.demo2.dto.BoardDTO;
import com.example.demo2.dto.PageRequestDTO;
import com.example.demo2.util.BoardDAOUtil;
import com.example.demo2.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class BoardService {
    BoardDAO boardDAO;
    ModelMapper modelMapper;
    public BoardService(){
        boardDAO= BoardDAOUtil.INSTANCE.get();
        modelMapper= MapperUtil.INSTANCE.get();
    }

    public List<BoardDTO> selectAll(PageRequestDTO pageRequestDTO, HttpSession session) throws SQLException {
        List<BoardVO> boardVOList = boardDAO.selectAll(pageRequestDTO,session);
        List<BoardDTO> boardDTOList = boardVOList.stream().map(vo->modelMapper.map(vo,BoardDTO.class)).collect(Collectors.toList());
        return boardDTOList;
    }

    public BoardDTO selectOne(Integer bno,String writer) throws SQLException {
        BoardVO vo = boardDAO.selectOne(bno,writer);
        log.info("vovovo3 : "+vo);
        BoardDTO dto = modelMapper.map(vo, BoardDTO.class);
        return dto;
    }

    public Integer getCount() throws SQLException {
        return boardDAO.getCount();
    }

    public int delete(Integer bno) throws SQLException{
        return boardDAO.delete(bno);
    }

    public int insert(BoardDTO boardDTO) throws SQLException {
        log.info("ininin2+"+boardDTO);
        BoardVO vo = modelMapper.map(boardDTO, BoardVO.class);
        log.info("ininin2+"+vo);
        return boardDAO.insert(vo);
    }

    public int update(BoardDTO boardDTO) throws SQLException {
        BoardVO vo = modelMapper.map(boardDTO, BoardVO.class);
        return boardDAO.update(vo);
    }
}
