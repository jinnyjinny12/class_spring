package com.ohgiraffers.section02.annotation.subsection05.inject;


import com.ohgiraffers.section02.common.Poketmon;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

@Service("pokemonServiceInject")
public class PoketmonService {

    // 인젝트는 의존성을 주입해준다
//    //1. 필드주입방식
//    @Inject
//    @Qualifier("charamander")
//


    private Poketmon pocketmon;

    @Inject
    public PoketmonService(Poketmon poketmon) {
        this.pocketmon = poketmon;
    };



    public void poketmonAttack(){
        pocketmon.attack();
    }

}
