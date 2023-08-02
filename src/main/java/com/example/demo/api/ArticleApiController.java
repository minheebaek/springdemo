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
    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) { //묶음이 아닌 단일 article을 반환할거니까 List no
        //article이 없으니까 dto로 받아와야함
        Article created = articleService.create(dto);
        return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
//ResponseEntity<Article>이렇게 ResponseEntity로 담아 보내야 여기에 상태코드(400번)를 실을 수 있다.
        //ResponseEntity에 <Article>이 담겨 JSON으로 보내게 된다
        Article updated = articleService.update(id,dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        //웨이터는 어떤 주문이 오고 뭐를 반환한다 이것만 알면 된다
         Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    //트랜잭션 -> 실패 ->롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        //웨이터가 데이터를 받고 응답으로 무엇을 던질지만 적으면 된다. 자세한건 서비스가.
       List<Article> createdList = articleService.createArticles(dtos);
       return (createdList != null) ?
               ResponseEntity.status(HttpStatus.OK).body(createdList):
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
