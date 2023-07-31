package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor //디폴트 생성자 추가
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue
    private Long id; //주민번호같은거
    @Column
    private String title;
    @Column
    private String content;


}
