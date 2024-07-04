package com.ohgiraffers.cahp02handler1;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/first/")
@SessionAttributes("id")
public class FirstController {
    /*
    * Default Request URL 매핑 : 반환 타입이 반환 타입이 void 인 경우, Spring 은 요청 url 을 기반으로
    * 뷰를 해석하려고 하는데 예를 들어 "/example" 이라는 url로 요청이 들어오고 그 해당하는 컨트롤러가
    * 'void' 를 반환한다면 spring 은 요청 url ('/example') 을 뷰의 이름으로 간주한다
    *
    * spring mvc 는 RequestToViewNameTranslator 인터페이스를 사용하여 요청 url 을 뷰의 이름으로 변환하게 되는데
    * 기본적으로 DefaultRequestToViewNameTranslator 가 사용된다. 이 트랜스레이터는 요청 경로를 기반으로 뷰 이름을 생성하게 된다
    * */

    @GetMapping("regist")
    public void regist(){

    }

    /*
    * 1. WebRequest 로 요청 파라미터를 전달받기
    *   파라미터 선언부에 WebRequest 타입을 선언하면 해당 메소드 호출 시 인자로 값을 전달해준다.
    *   핸들러 메소드 매개변수로 HttpSerlvetRequestm HttpServleResponse도 사용 가능하다.
    *   상위 타입인 ServletRequestm ServletResponse 도 사용 가능하다.
    *   WebRequest는 HttpServletRequest 의 요청 정보를 거의 대부분 가지고 있는 API 로 Serlvet 에 종속적이지 않다
    *   HttpServlet 은 Servlet API 일부이고
    *   WebRequest는 spring의 일부이기 때문에 spring 기반의 프로젝트에서 더 자주 사용된다,
    *
    * */


    @PostMapping("regist")
    // action regist 와 method post를 반영해서 어노테이션과 "" 를 작성
    public ModelAndView registMenu(ModelAndView modelAndView, WebRequest webRequest){
        String name = webRequest.getParameter("name");
        int price = Integer.parseInt(webRequest.getParameter("price"));
        int category = Integer.parseInt(webRequest.getParameter("category"));

        String message = name + "을 신규 메뉴 목록의 " + category + "번의 카테고리에 " + price + "을 등록했습니다.";
        System.out.println(message);

        modelAndView.addObject("message", message);
        modelAndView.setViewName("first/messagePrinter");
        return modelAndView;

    }

    @GetMapping("modify")
    public void modify(){
    }

    /* 2. @RequestParam로 요청 파라미터 전달 받기
     *    요청 파라미터를 매핑하여 호출 시 값을 넣어주는 어노테이션으로 매개 변수 앞에 작성한다.
     *    form의 name 속성값과 매개변수의 이름이 다른 경우 @RequestParam("name")을 설정하면 된다.
     *    또한 어노테이션은 생략 가능하지만 명시적으로 작성하는 것이 의미 파악에 쉽다.
     *
     *    전달하는 form의 name속성이 일치하는 것이 없는 경우 400에러가 발생하는데 이는 required 속성의 기본 값이 true이기 때문이다.
     *    required 속성을 false로 하게 되면 해당 name값이 존재하지 않아도 null로 처리하며 에러가 발생하지 않는다.
     *
     *    값이 넘어오지 않게 되면 "" 빈 문자열이 넘어오게 되는데, 이 때 parsing 관련 에러가 발생할 수 있다.
     *    값이 넘어오지 않는 경우 defaultValue를 이용하게 되면 기본값으로 사용할 수 있다.
     * */

    @PostMapping("modify")
    public ModelAndView ModifyMenu(ModelAndView mv, @RequestParam(required = false) String modifyName,
                                  @RequestParam(defaultValue = "0") int menuPrice){

        String message = modifyName + "메뉴의 가격을 " + menuPrice + "원으로 가격을 변경했습니다";
        System.out.println(message);

        mv.addObject("message", message);
        mv.setViewName("first/messagePrinter");
        return mv;

    }

    @PostMapping("modifyAll")
    public ModelAndView ModifyMenuAll(ModelAndView mv, @RequestParam Map<String, String> parameters){

        String modifyName = parameters.get("modifyName");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice"));


        String message = "modify all" + modifyName + "메뉴의 가격을 " + modifyPrice + "원으로 가격을 변경했습니다";
        System.out.println(message);

        mv.addObject("message", message);
        mv.setViewName("first/messagePrinter");
        return mv;

    }


    @GetMapping("search")
    public void search(){

    }

    /* 3. @ModelAttribute를 이용하는 방법
     * DTO 같은 모델을 커맨드 객체로 전달받는다.
     * 테스트 하기 위해서는 DTO를 만든다.
     *
     * @ModelAttribute의 경우 커맨드객체를 생성하여 매개변수로 전달해 준 뒤 해당 인스턴스를 model에 담는다.
     * 화면에서 출력해보면 모델에 담겨진 값을 확인할 수 있다.
     *
     * 경우에 따라 폼에서 입력한 값을 다음 화면으로 바로 전달해야 하는 경우가 발생하는데 이 때 유용하게 사용할 수 있다.
     * @ModelAttribute("모델에담을key값")을 지정할 수 있으며, 지정하지 않으면 타입의 앞글자를 소문자로 한 네이밍 규칙을 따른다.
     * menuDTO로 담긴다.
     *
     * 해당 어노테이션은 생략이 가능하지만 명시적으로 작성하는 것이 좋다.
     * */

    @PostMapping("search")
    public ModelAndView searchMenu(@ModelAttribute("menu") MenuDTO menu, ModelAndView mv) {

        System.out.println(menu);
        mv.setViewName("first/searchResult");

        return mv;
    }

    @GetMapping("login")
    public void login(){}

    /* 4-1. session이용하기
     * HttpSession을 매개변수로 선언하면 핸들러 메소드 호출 시 세션 객체를 넣어서 호출한다.
     * */

    @PostMapping("login1")
    public String sesstionTest1(HttpSession session, @RequestParam String id){
        session.setAttribute("id", id);
        return "first/loginResult";
    }

    @GetMapping("logout1")
    public String logoutText2(HttpSession session){
        session.invalidate();
        return "first/loginResult";
    }

    /* 4-2. @SessionAttributes를 이용하여 session에 값 담기
     * 클래스레벨에 @SessionAttributes 어노테이션을 이용하여 세션에 값을 담을 key값을 설정해두면
     * Model영역에 해당 key로 값이 추가되는 경우 session에 자동 등록을 한다.
     * */

    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id){
        model.addAttribute("id",id);

        return "first/loginResult";

    }

    /* SessionAttributes로 등록된 값은 session의 상태를 관리하는 SessionStatus의 setComplete() 메소드를 호출해야 사용이 만료된다. */

    @GetMapping("logout2")
    public String logoutText2(SessionStatus sessionStatus){
        sessionStatus.setComplete();
//        sessionStatus.setComplete(); = a모든 세션을 제거


        return "first/loginResult";
    }

    @GetMapping("body")
    public void body(){}

    @PostMapping("body")
    public void bodyTest(@RequestParam String body,
                         @RequestHeader("content-type") String contentType,
                         @CookieValue(value = "JSESSIONID", required = false) String sessionID) throws UnsupportedEncodingException {

        System.out.println(contentType);
        System.out.println(sessionID);
        System.out.println(body);
        System.out.println(URLDecoder.decode(body, "UTF-8"));

    }
}
