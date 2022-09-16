package com.example.demo2.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Getter
@ToString
@Log4j2
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private Integer page=1;//현재페이지
    @Builder.Default
    private Integer size=5;//페이지당 사이즈
    public Integer getSkip(){
        return (page-1)*size;
    }
}
