package com.ohgiraffers.chap01requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
/*
* 클래스 레벨에 @RequestMapping 어노테이션 사용이 가능하다
* 클레스 레벨에 url을 공통 부분을 이용해 설정하고 나면 매번 핸드럴 메소드에 url의 중복되는 내용은 작성되지 않아도 된다
* 이 때 와일드 카드를 이용해서 조금 더 포괄적인 url 패턴을 설정할 수 있다.
* */

//classMappingTestController
//controller: 사용자가 요청을 하면 그걸 처리해줌 이걸 스프링측에서 관리를 함?
// 하나의 객체 -> 우리는 그걸 빈이라고 부름, 빈의 이름을 명시함
// RestController는 http://localhost:8000/orders 이렇게 쓰면 hellow 뜸


// 리퀘스트 어노테이션은 네임을 생략할 수 있다
@Controller
@RequestMapping("/orders/*")
public class ClassMappingTestController {

    @GetMapping("/resgist")
    public String resgistOrder(Model model){
        model.addAttribute("message","get 방식의 주문 등록용 핸들로 메소드를 호출함");
        return "mappingResult";
    }


    @PostMapping("/resgist")
    public String registOrderPost(Model model) {
        model.addAttribute("message", "post 방식의 주문 등록용 핸들러 메소드를 호출함");

        return "mappingResult";
    }

    // 여러 개의 패턴 매핑
    // value 속성이 중괄호를 이용해 매핑할 url을 나열한다
    @RequestMapping(value = {"modify","delete"}, method = RequestMethod.POST)
    public String modifyAndDelete(Model model){
        model.addAttribute("message","post방식의 주문 정보 수정과 주문 정보 삭제 공통 처리용 핸들러 메소드 호출함");
        return "mappingResult";
    }

    // order/1 : get -> pathVariable
    // orders?name=아메리카노 : get 쿼리스트링 파라미터
    // @PathVariable 은 {} 에 쓴 값을 찾는다
    /*
    * pathvariable
    * @PathVariable 어노테이션을 이용해 요청 path로부터 변수를 받아올 수 있다
    * path variable로 전달되는 {변수명} 값은 반드시 매개변수명과 동일해야 한다.
    * 만약 동일하지 안흥면 @PathVariable("이름") 을 설정해주어야 한다
    * 이는 Rest 웹 서비스를 설계할 때유용하게 사용된다
    *
    * */

    @GetMapping("/detail/{orderNo}")
    public String selectOrderDetail( Model model,@PathVariable("orderNo") int orderNo ){
        model.addAttribute("message",orderNo +"번 주문 상세 내용 조회 핸들러");
        return "mappingResult";
    }

    @GetMapping("/")
    public String registOrders(Model model, @RequestParam String test, @RequestParam String value ){

        model.addAttribute("message", test + " : " + value);
        return "mappingResult";

    }

    /*
    * 그 외 다른 요청
    * @RequestMapping 어노테이션에 아무런 url 을 설정하지 않으면 요청 처리에 대한 핸들러 메소드가
    * 준비되지 않았을 때 해당 메소드를 호출한다
    * */

        // 이거 이따가 다시
    @RequestMapping
    public String OtherResquest (Model model){
        model.addAttribute("message","order요청이긴 하지만 아무것도 준비 안함");
        return "orderError";
    }

}
