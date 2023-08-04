package com.example.demo.ioc;

import org.springframework.stereotype.Component;

@Component //클래스를 가지고 IoC 컨테이너가 객체를 만들어주고 자동적으로 관리함 외부에서 @Autowired하면 필요한 곳에
//쏙쏙 삽입함(DI함)
public class Chef {
    //셰프는 식재료 공장을 알고있음
    private IngredientFactory ingredientFactory;

    //셰프가 식재료 공장과 협업하기 위한 DI
    public Chef(IngredientFactory ingredientFactory){ //(외부)공장에 대한 정보를 받아옴
    this.ingredientFactory = ingredientFactory;
    }
    public String cook(String menu) {
        //재료 준비
        //메뉴를 넣으면 get에서 식재료를 반환
        Ingredient ingredient = ingredientFactory.get(menu); //비빔밥이든 파스타든 공장에서 알아서 재료를 반환함
        //요리 반환
        return ingredient.getName() + "로 만든 " + menu; //상속을 통해 ingredient가 누들도 될수 있고 베지도 될수 있음
    }
}
