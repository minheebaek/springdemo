package com.example.demo.ioc;

import org.springframework.stereotype.Component;

@Component//해당 클래스를 객체로 만들고 IoC 컨테이너에 등록
public class IngredientFactory {
    public Ingredient get(String menu) {
        switch (menu){
            case "비빔밥":
                return new Vege("채소");

            case "파스타":
                return new Noodle("면");

            case "비건 치킨" :
                return new Chicken("국내산 콩고기");

            default:
                return null;
        }
    }
}
