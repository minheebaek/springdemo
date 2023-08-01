package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity //db가 해당 객체를 인식 가능! (해당 클래스로 테이블을 만든다)
@AllArgsConstructor
@NoArgsConstructor //디폴트 생성자 추가
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 id를 자동 생성
    private Long id; //주민번호같은거
    @Column
    private String title;
    @Column
    private String content;


}
