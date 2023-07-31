package com.example.demo.repository;

import com.example.demo.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> { //관리대상 entity,대표값 타입
    @Override
    ArrayList<Article> findAll();
}
