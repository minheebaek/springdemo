package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //해당 클래스를 서비스로 인식하여 스프링부트에 객체를 생성(등록)
public class ArticleService {
    //ArticleService와 repository가 협업할 수 있게 필드 추가
    @Autowired //DI
    private ArticleRepository articleRepository;

    public List<Article> index() {
       return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }
}
