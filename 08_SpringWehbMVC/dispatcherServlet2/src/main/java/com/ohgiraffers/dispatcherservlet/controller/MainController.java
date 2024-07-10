package com.ohgiraffers.dispatcherservlet.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
       request.setAttribute("message", "main message");
       return "main";
    }
}
