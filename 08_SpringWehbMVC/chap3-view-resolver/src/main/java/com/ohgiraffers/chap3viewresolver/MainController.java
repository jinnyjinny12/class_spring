package com.ohgiraffers.chap3viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"/"})
    public String main() {
        return "main";
    }
    /*
    * RequestToViewNameTranslator
    * spring에서 반환 타입이 void인 경우 요청 url 을 기반으로 뷰를 해석하려고 하는데
    * 이 때 RequestToViewNameTranslator 사용하여 url을 뷰의 이름으로 변환한다.
    *
    * */

    @RequestMapping(value = "/main")
    public void mainPage(){
    // void 일 경우에만 가능하다
    //
    }

}
