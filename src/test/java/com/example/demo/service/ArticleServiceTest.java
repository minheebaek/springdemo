package com.example.demo.service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //해당 클래스는 스프링부트와 연동되어 테스팅된다
class ArticleServiceTest {

    @Autowired ArticleService articleService; //ArticleService 가져오기
    @Test //해당메소드가 테스트를 위한 코드다 라는 선언
    void index() {
        //예상
        Article a = new Article(1L, "가가가", "111");
        Article b = new Article(2L,"나나나", "222");
        Article c = new Article(3L,"다다다", "333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        //위의 데이터들을 list에 넣어줌        //실제
        List<Article> articles= articleService.index(); //article 묶음을 반환할 것임

        //비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공___존재하는_id_입력() { //존재하는 아이디를 입력해서 성공한 경우를 테스트
    //예상
    Long id = 1L;
    Article expected = new Article(id, "가가가", "111");
    //실제 //실제 서비스가 반환하는 값
    Article article = articleService.show(id);
    //비교
    assertEquals(expected.toString(),article.toString());
   }
    @Test
    void show_실패___존재하지_않은_id_입력() {
        //id를 article로 가져왔는데 그게 존재하지 않는 id값일때를 예상코드로 작성해야함
        //예상
        Long id = -1L;
        Article expected = null;
        //실제 //실제 서비스가 반환하는 값
        Article article = articleService.show(id);
        //비교
        assertEquals(expected,article); //null은 toString 메서드를 호출 못함
    }

    @Test
    @Transactional //테스트 종료후 변경된 데이터를 롤백 처리, 생성,변경, 삭제될 수 있는 경우에 함
    void create_성공___title과_content만_있는_dto_입력() {
        //예상
        String title = "라라라";
        String content ="444";
        ArticleForm dto=new ArticleForm(null, title, content); //예상되는 값 dto를 만듦
        Article expected = new Article(4L, title, content);

        //실제 //실제 서비스가 반환하는 값
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected.toString(),article.toString());
    }
    @Test
    void create_실패___id가_포함된_dto_입력(){//create할때 id 못 넣으니까
        //예상
        String title = "라라라";
        String content ="444";
        ArticleForm dto=new ArticleForm(4L, title, content); //예상되는 값 dto를 만듦
        Article expected = null;

        //실제 //실제 서비스가 반환하는 값
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected,article);
    }
    //
}