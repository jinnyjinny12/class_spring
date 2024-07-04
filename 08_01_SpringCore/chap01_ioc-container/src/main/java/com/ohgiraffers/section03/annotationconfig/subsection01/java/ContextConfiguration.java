package com.ohgiraffers.section03.annotationconfig.subsection01.java;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration("configuratonSection03")
/* 1. Test1 */
@ComponentScan(basePackages = "com.ohgiraffers")

/* 2. Test2 */
@ComponentScan(basePackages="com.ohgiraffers",
        excludeFilters= {
                @ComponentScan.Filter(
                        /* 1. 타입으로 설정 */
//                     type= FilterType.ASSIGNABLE_TYPE,
//                     classes= {MemberDAO.class}
                        /* 2. 어노테이션 종류로 설정 */
//                     type=FilterType.ANNOTATION,
//                     classes= {org.springframework.stereotype.Component.class}
                        /* 3. 표현식으로 설정 */
                        type= FilterType.REGEX,
                        pattern= {"com.ohgiraffers.section03.annotationconfig.java.*"}
                )
        })
/* 3. Test3 */
//@ComponentScan(basePackages="com.ohgiraffers",
//        useDefaultFilters=false,
//        includeFilters= {
//                @ComponentScan.Filter(
//                        /* excludeFilters에서 설정하는 방식과 동일하다 */
//                        type=FilterType.ASSIGNABLE_TYPE,
//                        classes= {MemberDAO.class}
//                )
//        })

public class ContextConfiguration {


}
