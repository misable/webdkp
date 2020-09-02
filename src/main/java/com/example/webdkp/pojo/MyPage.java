package com.example.webdkp.pojo;

import lombok.Data;

/**
 * @Author zyq
 * @Description 分页对象
 **/
@Data
public class MyPage {
    private int pageSize;
    private int curPage;
    private int totalRow;
    private int totalPage;

    public MyPage(int pageSize, int curPage, int totalRow, int totalPage) {
        this.pageSize = pageSize;
        this.curPage = curPage;
        this.totalRow = totalRow;
        this.totalPage = totalPage;
    }

    public MyPage() {
    }
}
