package com.example.demo2.dto;

import com.example.demo2.util.BoardServiceUtil;
import lombok.*;

import java.sql.SQLException;

@Getter
@ToString
public class PageResponseDTO {
    private Integer page;
    private Integer size;
    private Integer skip;
    private Integer totalCnt;
    private Integer start;
    private Integer last;
    private Integer end;
    private Integer prev;
    private Integer next;
    private boolean showPrev;
    private boolean showNext;

    @Builder
    PageResponseDTO(PageRequestDTO pageRequestDTO) throws SQLException {
        this.page=pageRequestDTO.getPage();
        this.size=pageRequestDTO.getSize();
        this.skip=getOffset();
        this.totalCnt= getCount();
        this.start=(page-1)/size*size+1;
        this.last=(int)(Math.ceil(totalCnt/(double)size));
        this.end=last<start+(size-1)?last:start+(size-1);
        this.prev=start-1;
        this.next=end+1;
        this.showPrev=start!=1;
        this.showNext=last>end;
    }


    public Integer getOffset(){
        return (page-1)*size;
    }
    public Integer getCount() throws SQLException {
        return BoardServiceUtil.INSTANCE.get().getCount();
    }
}
