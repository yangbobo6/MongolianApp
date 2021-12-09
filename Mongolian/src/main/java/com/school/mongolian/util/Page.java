package com.school.mongolian.util;

import lombok.Data;

import java.util.List;

@Data
public class Page<T>{
    public static final int DEFAULT_PAGE_SIZE = 10;

    //每页现实的个数
    private int pageSize;

    //当前页数
    private int currentPage;

    //总页数
    private int totalPage;

    //总数居个数
    private int total;

    //样本总数  总页数
    private List<T> content;

    public Page() {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.currentPage = 1;
    }

    //自己规定页面大小， 当前页
    public Page(int pageSize,int currentPage){
        this.pageSize = pageSize<=0? 1 : pageSize;
        this.currentPage = currentPage <=0? 1:currentPage;
    }

    public void build(List<T> content){
        this.total = content.size();
        if(this.total == 0){
            this.totalPage =0;
            this.currentPage = 1;
            this.setContent(content);
            return;
        }
        //页码数
        this.totalPage = this.total%this.pageSize == 0 ? this.total/this.pageSize : this.total/this.pageSize+1;
        //当前页
        this.currentPage = this.currentPage > this.totalPage ? this.totalPage : this.currentPage;
        //
        this.setContent(content.subList(this.pageSize*(this.currentPage-1),this.currentPage==this.totalPage?content.size():this.pageSize*this.currentPage));
    }
}
