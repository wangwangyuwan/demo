package git.wangwangyuwan.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDTO> list;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalCount;
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer pageSize) {

        this.totalCount = totalCount;
        if(totalCount % pageSize >0 ){
            totalPage = totalCount/pageSize+1;
        }else {
            totalPage = totalCount/pageSize;
        }

        if(page<0){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }
        this.page = page;
        pages.add(page);
        for(int i=1 ;i<=3; i++){
            if(page-i > 0){
                pages.add(0,page-i);
            }
            if(page+i <= totalPage){
                pages.add(page+i);
            }
        }
        showFirstPage = true;
        showPrevious = true;
        showEndPage = true;
        showNext = true;

        if (pages.contains(1)){
            showFirstPage = false;
        }
        if(page == totalPage){
            showNext = false;
        }
        if(1 == page){
            showPrevious = false;
        }
        if(pages.contains(totalPage)){
            showEndPage = false;
        }

    }
}
