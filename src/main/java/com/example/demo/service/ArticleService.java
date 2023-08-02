package com.example.demo.service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    public Article create(ArticleForm dto) {
        Article article=dto.toEntity();
        return articleRepository.save(article); //데이터 창고에 넣으라고 시키기
    }

    public Article update(Long id, ArticleForm dto) {
        //1.수정용 엔티티 생성 dto->entity로 변환
        Article article =dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        //2.대상 엔티티 조회
        Article target=articleRepository.findById(id).orElse(null);

        //3.잘못된 요청 처리(대상이 없거나, id가 다른 경우(요청은 1인데 json은 3이다))
        if(target == null || id != article.getId()){
            //400, 잘못된 요청 응답해야함
            log.info("잘못된 요청! id: {}, article:{}", id, article.toString());
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);//웨이터 일이니까 안해도 됨
            return null;
        }
        //4.업데이트 (응답은 안해도 됨)
        target.patch(article); //target에 article을 붙여야 title을 빼도 title이 null로 표시되지 않는다
        Article updated=articleRepository.save(target);
        return updated;

    }

    public Article delete(Long id) {
        //1.대상 찾기
        Article target=articleRepository.findById(id).orElse(null);

        //잘못된 요청 처리
        if(target == null){
            return null;
            //정상적으로 처리할때는 body에 데이터를 실어보낼 수 있다
        }

        //2.대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional //해당 메소드를 트랜잭션으로 묶음 -> 이 메소드가 실패하면 이전 상태로 롤백
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dto 묶음을 entity 묶음으로 변환
        //entity의 list를 만들기위해 stream사용
        List<Article>articleList=dtos.stream()
                .map(dto -> dto.toEntity()) //하나하나의 dto가 올때마다 dto.toEntity() 수행해서 맵핑
                .collect(Collectors.toList()); //맵핑된거를 리스트로 변환

        //entity 묶음을 DB로 저장
        articleList.stream()
                //foreach() :하나하나씩 반복한다.
                .forEach(article -> articleRepository.save(article));
        //하나의 article이 올때마다 articleRepository를 통해서 하나하나씩 꺼내진 article을 save함

        //강제 예외 발생
        articleRepository.findById(-1L).orElseThrow( //-1인 id값을 찾아라 -> 당연히 없음
                () -> new IllegalArgumentException("결제실패")
        );

        //결과값 반환
        return articleList; //id가 없으니 형식상 목록 반환
    }
}
