package com.ohgiraffers.section02.annotation.subsection02.qualifier;

import com.ohgiraffers.section02.common.Poketmon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pokemonServiceQualifier")
public class PoketmonService {

    /*
    * @Qualifier 어노테이션은 여러 개의 빈 객체 중에서 특정 빈 객체를 이름으로 지정하는 어노테이션이다.
    * @Primary 어노테이션과 @Qualifier 어노테이션이 함꼐 쓰였을 때 @Qualifier가 우선한다.
    * */


    // 1. 필드 주입 방식
    //@Autowired
    //@Qualifier("charamander")

    private Poketmon poketmon;

    // 2. 생성자 주입방식
    @Autowired
    public PoketmonService(@Qualifier("charamander") Poketmon poketmon) {

        this.poketmon = poketmon;
    }

    // 3. setter 주입 방식
    //@Autowired
    //@Qualifier("charmander")
    //public void setPoketmon(Poketmon poketmon) {
    //    this.poketmon = poketmon;
    //}




    public void poketmonAttack(){
        poketmon.attack();
    }
}

