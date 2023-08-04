package com.example.demo.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest //컨테이너를 테스트에서 사용하기 위함
class ChefTest {

    @Autowired //IoC 컨테이너가 객체를 DI하게 함
    IngredientFactory ingredientFactory; //IngredientFactory를 스프링부트 ioc 컨테이너에서 가져올 수 있게 함
    //IoC컨테이너가 IngredientFactory를 @Component를 통해 등록하고  @Autowired를 통해 땡겨옴

    @Autowired
    Chef chef;
    @Test
    void 비빔밥_요리하기() {
        //준비
        //IngredientFactory ingredientFactory = new IngredientFactory(); //재료공장 만들기
        //Chef chef = new Chef(ingredientFactory); //재료공장 정보를 DI(의존성 주입함)
        String menu = "비빔밥";

        //수행
        String food = chef.cook(menu);

        //예상
        String expected = "채소로 만든 비빔밥";

        //검증
        assertEquals(expected, food);
        System.out.println(food);
    }
    @Test
    void 파스타_요리하기() {
        //준비
        //IngredientFactory ingredientFactory = new IngredientFactory(); //재료공장 만들기->의존성 낮춤
        //Chef chef = new Chef(ingredientFactory);
        String menu = "파스타";

        //수행
        String food = chef.cook(menu);

        //예상
        String expected = "면로 만든 파스타";

        //검증
        assertEquals(expected, food);
        System.out.println(food);
    }
    @Test
    void 크리스피_비건치킨_요리하기(){
        //준비
        //IngredientFactory ingredientFactory = new IngredientFactory(); //재료공장 만들기->의존성 낮춤
        //Chef chef = new Chef(ingredientFactory);
        String menu = "비건 치킨";

        //수행
        String food = chef.cook(menu);

        //예상
        String expected = "국내산 콩고기로 만든 비건 치킨";

        //검증
        assertEquals(expected, food);
        System.out.println(food);
    }
}