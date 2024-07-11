package com.ohgiraffers.chap05interceptor;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/*")
public class InterceptTestController {

    @GetMapping("stopwatch")
    public String handlerMethod() throws InterruptedException {
        System.out.println("핸들러 메소드 호출함....");
        Thread.sleep(1000); // 스레드가 1초간 멈춰랏
        return "result";

    }

}
