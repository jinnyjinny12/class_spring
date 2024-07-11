package com.ohgiraffers.chap04_exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String NullPointExceptionHandler(NullPointerException exception) {
        System.out.println("global 레벨의  exception 처리");

            return "error/nullpointer";
    }

    @ExceptionHandler(MemberResgistException.class)
    public String userExceptionHandler(Model model, MemberResgistException memberResgistException) {


        System.out.println("global 레벨의 exception 처리");
        model.addAttribute("exception", memberResgistException);

        return "error/memberresgist";
    }

    @ExceptionHandler(Exception.class)
    public String NullPointExceptionHandler(Exception exception) {
        System.out.println("gloabl exception 처리");

        return "error/default";

    }

}
