package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString //toString 지우고 이거 설정
public class ArticleForm {
    private String title;
    private String content;

    /*
 리팩토링 하면서 지워야 하는데 필기한거때문에 못지움
 생성자 지우는 거 @AllArgsConstructor
    //생성자 생성
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
    //데이터가 잘 받아졌는지 확인하기 위해 toString 생성
*/


    public Article toEntity() {
        return new Article(null,title,content);
        //entity Article클래스의 객체를 생성해야함(Article.java)
    }
}
