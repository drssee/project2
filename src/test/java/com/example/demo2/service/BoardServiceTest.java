package com.example.demo2.service;

import com.example.demo2.domain.BoardVO;
import com.example.demo2.dto.BoardDTO;
import com.example.demo2.dto.PageRequestDTO;
import com.example.demo2.util.BoardServiceUtil;
import com.example.demo2.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class BoardServiceTest {
    BoardService boardService = BoardServiceUtil.INSTANCE.get();
    ModelMapper modelMapper= MapperUtil.INSTANCE.get();

    @Test
    public void insert() throws SQLException {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("test2")
                .content("test3")
                .writer("test4")
                .build();
        boardService.insert(boardDTO);
    }
}
