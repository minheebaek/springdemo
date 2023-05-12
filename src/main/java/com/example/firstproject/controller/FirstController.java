package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 선언
public class FirstController {
    @GetMapping("/hi") //hi를 받았을때 return을 반환함
    public String niceToMeetYou(Model model){
        model.addAttribute("username","hongpark");
        return "greetings"; //templates/greetings.mustache 파일 찾아서 브라우저로 전송
    }
    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","홍길동");
        return "goodbye";
    }
}


