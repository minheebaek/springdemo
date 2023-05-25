package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Slf4j //로깅을 위한 골뱅이(어노테이션)
@Controller
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
        System.out.println(form.toString());

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

        return "redirect:/articles/"+saved.getId();
    }
    @GetMapping("/articles/{id}") //<---이게 url path
    public String show(@PathVariable Long id, Model model) { //@PathVariable 뜻: url path로부터 입력된다라는 걸 의미함
        log.info("id = "+ id);

        //데이터 조회 과정
        //1.id로 데이터를 가져옴, 데이터를 가져오는 주체:repository
        Article articeEntity=articleRepository.findById(id).orElse(null); //articleRepository가 findById로 값을 반환할때
        //리턴타입이 Article 타입이 아니라 Optional<Article>로 반환함
        //findById로 id를 찾았는데 값이 없으면 null을 반환, articleEntity에는 id값이 들어가거나 id값이 없으면 null이 들어감

        //2.가져온 데이터(articeEntity)를 모델에 등록! ->왜? 페이지로 설정하기 위해서
        model.addAttribute("article",articeEntity); //article이라는 이름으로 articeEntity를 등록했었다, 이러면
        //articles/show인 뷰페이지에서 쓸 수 있다.

        //3.보여줄 페이지를 설정!

        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model) {
        //1.모든 Article을 가져온다! -> 리파지토리 필요
        List<Article> articleEntityList = articleRepository.findAll();

        //2.가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList); //(이름, 던져줄 데이터(앞에서 한 articleEntityList)) 이름은 던져줄 데이터를 저 이름으로 던진다는 말
        //3.뷰 페이지를 설정
        return "articles/index"; //articles/index.mustache
    }
   @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model ){
        //수정할 데이터를 가져오기 리파지토리를 통해 디비에서 꺼내옴
      Article articleEntity= articleRepository.findById(id).orElse(null);
       //모델에 데이터 등록
        model.addAttribute("article",articleEntity);
        //뷰페이지 설정
        return "articles/edit";
   }

   @PostMapping("/articles/update")
   public String update(ArticleForm form){ //form데이터를 dto로 받는다

        //data를 db에 저장하는 과정
       //1,DTO를 엔티티로 변환한다
      Article articleEntity= form.toEntity();
      log.info(articleEntity.toString());

       //2.엔티티를 db로 저장한다
       //2-1:db에서 기존 데이터를 가져온다
       Article target =articleRepository.findById(articleEntity.getId()).orElse(null);

       //2-2 기존 데이터가 있다면 값을 갱신한다.
       if(target != null){
           articleRepository.save(articleEntity); //엔티티가 db로 갱신

       }

       //3.수정 결과 페이지로 리다이렉트 한다.

        return "redirect:/articles/"+articleEntity.getId();
   }

}