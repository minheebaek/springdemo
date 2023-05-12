package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

//db에서 인식 가능하게
@Entity //db가 해당 객체를 인식 가능
@AllArgsConstructor
@ToString
public class Article {

    //entity에는 대표값을 넣어야함 주민번호 같은거
    //제목도 같고 내용도 같지만 그것들을 정확하게 구분 짓기 위함
    //자동생성할때 많이 씀
    @Id //대표값을 지정
    @GeneratedValue //1,2,3.. 자동생성 어노테이션!
    private Long id;
    @Column //db라는 테이블 단위에 연결되게 만들어줌
    private String title;
    private String content;

    /* 리팩토링 위해 생성자 지운다.
    //entity를 만들기 위해 생성자 추가
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    */

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

