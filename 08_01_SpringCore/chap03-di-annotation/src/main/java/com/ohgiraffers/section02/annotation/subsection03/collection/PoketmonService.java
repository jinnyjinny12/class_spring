package com.ohgiraffers.section02.annotation.subsection03.collection;

import com.ohgiraffers.section02.common.Poketmon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("pokemonServiceCollection")
public class PoketmonService {

//    private List<Poketmon> poketmonList;
//
//    @Autowired
//    public PoketmonService(List<Poketmon> poketmonList) {
//        this.poketmonList = poketmonList;
//    }
//
//
//    public void poketmonAttack(){
//        poketmonList.forEach(Poketmon :: attack);
//    }


    // bean 이름의 사전순으로 list에 추가되어 모든 pokemon 타입의 빈이 주입된다.


    private Map<String, Poketmon> poketmonMap;

    @Autowired
    public PoketmonService(Map<String, Poketmon> poketmonMap) {
        this.poketmonMap = poketmonMap;
    }

    public void poketmonAttack() {
        poketmonMap.forEach((k,v) -> {
            System.out.println("key :" + k);
            System.out.println("value :");
            v.attack();
        });
    }

}

