package com.ohgiraffers.chap07thymeleaf.model.dto;

public class MemberDTO {

    private String name;
    private int age;
    private char gender;
    private String adderess;

    public MemberDTO() {

    }

    public MemberDTO(String name, int age, char gender, String adderess) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.adderess = adderess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getAdderess() {
        return adderess;
    }

    public void setAdderess(String adderess) {
        this.adderess = adderess;
    }
}
