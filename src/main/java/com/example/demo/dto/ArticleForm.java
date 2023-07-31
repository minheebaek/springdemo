package com.example.demo.dto;

import com.example.demo.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    //articleform 객체를 가지고 Article toEntity()를 반환하는 파일
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id,title,content); //객체를 새로 생성해서 반환한다.
    }
}
