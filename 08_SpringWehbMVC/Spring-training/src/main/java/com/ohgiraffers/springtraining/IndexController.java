package com.ohgiraffers.springtraining;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


// 총 3개의 컨트롤러를 만든다.
// index를 연결하는 컨트롤러 ?
// 사용자가 입력한 쿼리스트링 파라미터 값을 h1 태그를 이용해서 표시하는 컨트롤러

// 사용자가 입력한

//
//@Controller
//public class IndexController {
//
//    @GetMapping("/")
//    public String index(Model model) {
//        return "index";
//    }
////    인덱스 페이지로 이동할 수 있는 컨트롤러
//
//
//    @GetMapping("/display")
//    public String DisplayIndex(Model model) {
//        model.addAttribute("message", "Hello World!");
//        return "index";
//
//    }
//
//
//}
