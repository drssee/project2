package com.example.demo2.domain;

import lombok.*;

@Getter
//@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoListVO {
    private int tno;
    private String title;
    private boolean isFinished;
}
