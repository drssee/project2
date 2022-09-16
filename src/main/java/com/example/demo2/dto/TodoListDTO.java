package com.example.demo2.dto;

import lombok.*;

@Data
//@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoListDTO {
    private int tno;
    private String title;
    private boolean isFinished;

    @Override
    public String toString() {
        return title+" , "+(isFinished?"Finished":"Not yet");
    }
}
