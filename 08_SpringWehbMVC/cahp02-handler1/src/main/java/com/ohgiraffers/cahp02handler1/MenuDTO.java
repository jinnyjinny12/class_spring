package com.ohgiraffers.cahp02handler1;

/*
* DTO를 작성할 때 커맨트객체로 이용하기 위해서는 Form의 name 과 필드가 일치하게 만들어야 한다.
*
*
* */


public class MenuDTO {

    private String name;
    private int price;
    private int category;
    private String orderableStatus;


    // 1. 기본생성자를 이용하여 인스턴스를 생성한다
    public MenuDTO() {
    }

    public MenuDTO(String name, int price, int category, String orderableStatus) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    public String getName() {
        return name;
    }

    //2. 요청 파라미터의 name 과 일치하는 필드의 setter 를 이용하여 값을 초기화한다.
    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
