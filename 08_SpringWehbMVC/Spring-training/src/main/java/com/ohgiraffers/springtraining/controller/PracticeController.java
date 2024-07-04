package com.ohgiraffers.springtraining.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/*")
public class PracticeController {

    @GetMapping("/mession01")
    public ModelAndView mession01(ModelAndView mv, @RequestParam("pathValue") String value) {

        mv.addObject("message", value);
        mv.setViewName("mession01");
        return mv;

    }

    @GetMapping("/mession02")
    public String mession02(@RequestParam("message") String message, Model model) {
        model.addAttribute("message", message);
        return "mession02";
    }


}
