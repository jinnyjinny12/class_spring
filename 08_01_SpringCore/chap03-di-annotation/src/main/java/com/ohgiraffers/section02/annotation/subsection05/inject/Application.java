package com.ohgiraffers.section02.annotation.subsection05.inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section02");

        String[] beanNames = context.getBeanDefinitionNames();

        for (String bean : beanNames) {
            System.out.println(bean);
        }

        PoketmonService poketmonService = context.getBean(PoketmonService.class);
        poketmonService.poketmonAttack();;

    }

}
