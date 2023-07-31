package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j //로깅을 위한 골뱅이(어노테이션)
public class ArticleController {

    @Autowired //스프링 부트 미리 만들어놓은 객체를 가져다가 자동 연결
   private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    //페이지 보여주기위해서 메소드 추가
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());

        //1.dto를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        //2.Repository에게 Entity를 DB 안에 저장하게 함!
        Article saved=articleRepository.save(article);
        //articleRepository 객체를 따로 만들지 않아도 스프링부트가 만들어줌
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId(); //이 부분에 redirect를 넣으면 된다
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){//id라는 변수를 받아올건데 url 주소로부터 입력된다는 @PathVariable 추가
        log.info("id = "+id);

        //1.id로 데이터를 가져옴
        Article articleEntity=articleRepository.findById(id).orElse(null);//id값을 찾았는데 해당 값이 없다면 null반환

        //2.가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity); //article라는 이름으로 articleEntity를 등록
        //3.보여즐 페이지를 설정!
        return "articles/show";

        //2번을 통해 3번 뷰페이지에서 쓸 수 있다.
    }



    @GetMapping("/articles")
    public String index(Model model){
        //1.모든 article을 가져온다!
        List<Article> articleEntityList =articleRepository.findAll(); //findAll 해당 리파지토리에 있는 데이터를 모두 가져오는 것
        //list에 담겨진 article 형식으로 가져옴

        //2.가져온 Article 묶음을 뷰로 전달 -> 뷰로 전달할 때는 Model 사용
        model.addAttribute("articleList",articleEntityList); //던져줄 데이터와 그 이름 정하기
        //3.뷰페이지를 설정
        return"articles/index"; //articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){ //{id}여기서 가져오고 싶으니까 @PathVariable 추가
        //수정할 데이터를 가져오기!
       Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록!
        model.addAttribute("article",articleEntity);
        //뷰페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update") //폼데이터가 던진 걸 얘가 받음
    public String update(ArticleForm form){ //폼데이터를 dto로 받는다, edit파일에서 input id가 추가됐으니까  dto도 추가
        log.info(form.toString());

        //1.dto를 엔티티로 변환한다.
        Article articleEntity = form.toEntity(); //dtod/articleform 파일일
        log.info(articleEntity.toString());

        //2.엔티티를 db로 저장한다.
        //2-1:db에서 기존 데이터를 가져온다. ->db는 리파지토리를 통해서 일을 한다
        Article target=articleRepository.findById(articleEntity.getId()).orElse(null);//articleEntity.getId()얘를 호출해서 그 대상(id)을 받아온다

        //2-2:기존 데이터가 있다면 값을 갱신한다!
        if(target != null){
            articleRepository.save(articleEntity); //엔티티가 db로 갱신!
        }
        log.info(articleEntity.toString());

        //3.수정 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles/"+articleEntity.getId();
    }
}
