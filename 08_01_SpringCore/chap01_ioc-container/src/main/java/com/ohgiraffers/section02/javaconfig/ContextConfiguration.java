package com.ohgiraffers.section02.javaconfig;


import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Member;

@Configuration("configurationSection02")
public class ContextConfiguration {

    @Bean(name = "member")
    public MemberDTO getMember() {

        return new MemberDTO(1,"user01","pass01","홍길동");

    }

}
