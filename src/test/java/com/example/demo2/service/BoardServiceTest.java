package com.example.demo2.service;

import com.example.demo2.dto.PageRequestDTO;
import com.example.demo2.util.BoardServiceUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Log4j2
public class BoardServiceTest {
    BoardService boardService = BoardServiceUtil.INSTANCE.get();
    @Test
    public void selectAllTest() throws SQLException {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
//        List<BoardDTO> dtoList = boardService.selectAll(pageRequestDTO);
//        log.info(dtoList);
    }
}
