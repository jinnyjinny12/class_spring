package com.ohgiraffers.springtrainin2;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class IndexController {

    @GetMapping("/mission01")
    public ModelAndView mission01(ModelAndView mv, @RequestParam("pathValue") String value) {
        mv.addObject("message", value);
        mv.setViewName("mission01");
        return mv;
    }

    @GetMapping("/submitOption")
    public ModelAndView mission02(ModelAndView mv, @RequestParam("pathValue") String value,String selectedOption, RedirectAttributes rttr) {
        if ("Option2".equals(selectedOption)) {
            rttr.addFlashAttribute("flashMessage2", "경고: 잘못된 선택입니다");
            mv.setViewName("redirect:/");
            return mv;
        }
        mv.addObject("selectedOption", selectedOption);
        mv.addObject("message", value);
        mv.setViewName("mission01");
        return mv;
    }


}
