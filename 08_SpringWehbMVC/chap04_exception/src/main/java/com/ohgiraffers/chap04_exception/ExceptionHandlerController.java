package com.ohgiraffers.chap04_exception;


import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null")
    public String nullPointerExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0));
        return "/";

    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandlerTest(NullPointerException exception) {
        System.out.println("controller 레벨의  exception 처리");
        return "error/nullPointer";

    }

    @GetMapping("controller-user")
    public String userExceptionTest() throws MemberResgistException {
        boolean chech = true;
        if (chech) {
            throw new MemberResgistException("회원가입이 불가능합니다");
        }
        return "/";
    }


    @ExceptionHandler(MemberResgistException.class)
    public String memberResgistExceptionHandler(Model model, MemberResgistException memberResgistException) {

        System.out.println("controller 레벨의  exception 처리");
        model.addAttribute("exception", memberResgistException);
        return "error/memberResgist";

    }



}
