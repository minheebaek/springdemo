package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Long> { //<관리대상 entity, 관리대상 entity에서 대표값의 타입>
//extends를 통해 Article을 crud 기본동작을 추가코드 구현없이 확장 받아서 사용할 수 있게 됨
}
