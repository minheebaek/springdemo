package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j //로깅을 위한 골뱅이(어노테이션)
public class ArticleController {

    @Autowired //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository; //객체 안만들어도 스프링부트가 알아서 해줌

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public  String createArticles(ArticleForm form){
        log.info(form.toString());
        //System.out.println(form.toString()); //println 통해 데이터를 확인 //로깅기능으로 대체

        //1.Dto를 변환! Entity!
        Article article = form.toEntity(); //form에서 Entity객체로 변환하는 메소드 추가
        //form을 가지고 toEntity 메서드를 호출해서 Article 타입의 entity를 반환해오는 형식
        //Article 타입에 에러난거 import com.example.firstproject.entity.Article;import com.example.firstproject.entity.Article;
        //해서 사라짐
        log.info(article.toString());
       // System.out.println(article.toString());
        // 2.Repository에게 Entity를 DB안에 저장하게 함!
        Article saved= articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());

        return "";
    }
}
