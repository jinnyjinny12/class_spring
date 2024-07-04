package com.ohgiraffers.chap02handler;




@Controller
@RequestMapping("/first/")
public class FirstController {
    /*
    * Default Request URL 매핑 : 반환 타입이 반환 타입이 void 인 경우, Spring 은 요청 url 을 기반으로
    * 뷰를 해석하려고 하는데 예를 들어 "/example" 이라는 url로 요청이 들어오고 그 해당하는 컨트롤러가
    * 'void' 를 반환한다면 spring 은 요청 url ('/example') 을 뷰의 이름으로 간주한다
    *
    * spring mvc 는 RequestToViewNameTranslator 인터페이스를 사용하여 요청 url 을 뷰의 이름으로 변환하게 되는데
    * 기본적으로 DefqultRequestToViewNameTranslator 가 사용된다. 이 트랜스레이터는 요청 경로를 기반으로 뷰 이름을 생성하게 된다
    * */



    @GetMapping("regist")
    public void regist(){

        return "first/regist";
    }

}
