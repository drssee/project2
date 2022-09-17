package com.example.demo2.domain;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    //    @DateTimeFormat //스프링용 어노테이션
    private Date datetime;

    public String getDatetimeS() {//시간을 가져올때도 db와 형식이 같아야함
        SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm");
        return df.format(this.datetime);
    }
}
