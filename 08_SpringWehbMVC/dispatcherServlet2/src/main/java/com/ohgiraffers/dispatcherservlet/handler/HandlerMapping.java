package com.ohgiraffers.dispatcherservlet.handler;


import com.ohgiraffers.dispatcherservlet.controller.Controller;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import java.lang.reflect.InvocationTargetException;

public class HandlerMapping {
    // Controller 를 반환하기 위한 변수를 생성함.
    public Controller getController(String url) {
        Controller  controller = null;
        // 전달받은  url 과 매핑될 변수를 생성함
        String type = null;
        // 전달된 url 이 다음 케이스와 같다면 type에 해당하는 클래스 이름으로 초기화함.
        if(url.equals("/test")){
            type = "testController";

        }else if(url.equals("/main")){
            type = "mainController";
        }else if(url.startsWith("/redirect")){
            type = "RedirectController";
        }

        // "com.ohgiraffers.dispatcherservlet" 경로에 있는 모든 클래스를 스캔함.
        try(ScanResult scanResult = new ClassGraph().enableAllInfo().acceptPackages("com.ohgiraffers.dispatcherservlet").scan()){
            // "com.ohgiraffers.dispatcherservlet.controller.Controller" 인터페이스를 상속받은 모든 클래스를 조회하고 classInfos 에 저장함
            // MainController, RedirectController, TestController 가 있다.
            ClassInfoList classInfos = scanResult.getClassesImplementing("com.ohgiraffers.dispatcherservlet.controller.Controller");
            // classInfo의 요소를 순회함
            // MainController, RedirectController, TestController가 순회디ㅗㄹ 것임
            for (Class<?> implClass:classInfos.loadClasses()) {
                // 각 요소가 순회될 때 요소의 이름이 상위에서 정의한 Type 과 일치한다면?
                if(implClass.getName().equals("com.ohgiraffers.dispatcherservlet.controller."+ type)){

                    // 해당 클래스의 생성자를 호출하여 인스턴스를 생성함.
                    controller = (Controller) implClass.getDeclaredConstructor().newInstance();

                }

            }
            // 없으면 에러가 남
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        // Controller 를 반환함
        return controller;

    }

}
