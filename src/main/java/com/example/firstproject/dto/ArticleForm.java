package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {
    private String title;
    private String content;

    //생성자 생성
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
    //데이터가 잘 받아졌는지 확인하기 위해 toString 생성


    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null,title,content);
        //entity Article클래스의 객체를 생성해야함(Article.java)
    }
}
