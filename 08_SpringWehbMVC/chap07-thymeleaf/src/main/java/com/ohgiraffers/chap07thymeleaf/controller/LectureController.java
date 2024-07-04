package com.ohgiraffers.chap07thymeleaf.controller;

import com.ohgiraffers.chap07thymeleaf.model.dto.MemberDTO;
import com.ohgiraffers.chap07thymeleaf.model.dto.SelectCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
// 사용자의 요청을 받을 수 있는 상태가 된다
@RequestMapping("lecture")
//사용자의 요청이 lecture 라는 곳으로 들어오면 여기서 찾겠다
public class LectureController {

    @RequestMapping(value = "expression", method = RequestMethod.GET)
    //@GetMapping("expression") 으로 간단하게 표현할 수도 있다
    //사용자가 expression(lecture/expression) 이라는 요청을 보면 ResquestMethod.Get 을 실행시키겟다

    public ModelAndView expression(ModelAndView mv) {
        mv.addObject("member", new MemberDTO("홍길동", 20, '남',"서울시 서초구"));
        mv.addObject("hello", "hello!<h3>Thymeleaf</h3>");
        mv.setViewName("lecture/expression");
        return mv;
    }



    @GetMapping("conditional")
    public ModelAndView conditional(ModelAndView mv) {
        mv.addObject("num", 1);
        mv.addObject("str", "사과");
        mv.addObject("str", "바나나");

        List<MemberDTO> memberDTOList = new ArrayList<>();
        memberDTOList.add(new MemberDTO("홍길동", 19, '남', "서초구"));
        memberDTOList.add(new MemberDTO("홍길순", 20, '남', "서초구"));
        memberDTOList.add(new MemberDTO("홍동길", 25, '남', "서초구"));
        memberDTOList.add(new MemberDTO("홍도윤", 20, '남', "서초구"));
        memberDTOList.add(new MemberDTO("홍목", 20, '남', "서초구"));
        memberDTOList.add(new MemberDTO("홍보고", 20, '남', "서초구"));
        mv.addObject("memberList", memberDTOList);

        mv.setViewName("lecture/conditional");
        return mv;
    }


    @GetMapping("etc")
    public ModelAndView etc(ModelAndView mv) {
        SelectCriteria selectCriteria = new SelectCriteria(1, 10, 5);
        mv.addObject("selectCriteria", selectCriteria);

        MemberDTO member = new MemberDTO("홍길동", 20, '남', "서울시 서초구");

        mv.addObject("member", member);

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("홍길동", 20, '남', "서울시 서초구"));
        memberList.add(new MemberDTO("유관순", 22, '여', "서울시 노원구"));
        memberList.add(new MemberDTO("장보고", 40, '남', "서울시 종로구"));
        memberList.add(new MemberDTO("신사임당", 30, '여', "서울시 성북구"));

        mv.addObject("memberList", memberList);

        Map<String, MemberDTO> memberMap = new HashMap<>();
        memberMap.put("m01", new MemberDTO("홍길동", 20, '남', "서울시 서초구"));
        memberMap.put("m02", new MemberDTO("유관순", 22, '여', "서울시 노원구"));
        memberMap.put("m03", new MemberDTO("장보고", 40, '남', "서울시 종로구"));
        memberMap.put("m04", new MemberDTO("신사임당", 30, '여', "서울시 성북구"));

        mv.addObject("memberMap", memberMap);
        mv.setViewName("/lecture/etc");
        return mv;

    }


    @GetMapping("/fragment")
    public ModelAndView fragment(ModelAndView mv) {
        mv.addObject("test1","value1");
        mv.addObject("test2","value2");
        mv.setViewName("/lecture/fragment");

        return mv;
    }





}
