package com.example.demo.api;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //RestAPI용 컨트롤러, 데이터(JSON)을 반환
public class ArticleApiController {
    @Autowired //DI, 생성 객체를 가져와 연결!
    private ArticleService articleService;
//    private ArticleRepository articleRepository;
    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) { //묶음이 아닌 단일 article을 반환할거니까 List no
        return articleService.show(id);
    }
//    //POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) { //묶음이 아닌 단일 article을 반환할거니까 List no
//        //article이 없으니까 dto로 받아와야함
//        Article article = dto.toEntity(); //dto를 entity로 반환하면 된다->얘를 아랫줄에서 db로 저장함
//        return articleRepository.save(article); //클라이언트가 전송한 article을 save해야함
//    }
//    //PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
////ResponseEntity<Article>이렇게 ResponseEntity로 담아 보내야 여기에 상태코드(400번)를 실을 수 있다.
//        //ResponseEntity에 <Article>이 담겨 JSON으로 보내게 된다
//        //1.수정용 엔티티 생성 dto->entity로 변환
//        Article article =dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
//        //2.대상 엔티티 조회
//        Article target=articleRepository.findById(id).orElse(null);
//
//        //3.잘못된 요청 처리(대상이 없거나, id가 다른 경우(요청은 1인데 json은 3이다))
//        if(target == null || id != article.getId()){
//            //400, 잘못된 요청 응답해야함
//            log.info("잘못된 요청! id: {}, article:{}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //HttpStatus.BAD_REQUEST 400번을 뜻함
//        }
//        //4.업데이트 및 정상 응답(200)
//        target.patch(article); //target에 article을 붙여야 title을 빼도 title이 null로 표시되지 않는다
//        Article updated=articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//    //DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        //1.대상 찾기
//        Article target=articleRepository.findById(id).orElse(null);
//
//        //잘못된 요청 처리
//        if(target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//            //정상적으로 처리할때는 body에 데이터를 실어보낼 수 있다
//        }
//
//        //2.대상 삭제
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//
//    }

}
