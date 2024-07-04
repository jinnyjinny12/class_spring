package com.ohgiraffers.section01.manytoone;


import jakarta.persistence.*;
import lombok.Data;

/*
* @JoinColumn은 다대일 연관 관계에서 사용되는 어노테이션이다
* @JoinColumn 에서 사용할 수 있는 속성들은 다음과 같아
*
* - name : 참조하는 테이블의 칼럼명을 저장한다
* - referenceColumnName : 참조되는 칼럼명을 저장한다
* - nullable : 참조하는 테이블의 칼럼에  null 값을 허용할지 여부를 지정한다
* - insertable : 새로운 엔터티가 저장될 때 이 참조 칼럼이 sql insert 문에 포함될지 여부를 저장한다
* - updatable : 엔터티가 업데이트 될 때, 이 참조 칼럼이 SQL update 문에 포함될지 여부를 저장한다.
* - table : 참조하는 테이블의 이름을 저장한다
* - foreignkey : 참조하는 테이블에서 생성될 외래키에 대한 추가정보를 저장한다
*
* @manyToOne 은 다대일 연관관계에서 사용되는 어노테이션이다
* @manyToOne 에서 사용할 수 있는 속성들을 다음과 같다
* - casecade: 연관된 엔터티에 대한 영속성 전이를 설정한다
* - fetch: 연관된 엔터티를 로딩하는 전략을 설정한다
* - optional : 연관된 엔터티가 필수인지 선택적인지 설정한다.
*
*
* */
@Data
@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class MenuAndCategory {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_Price")
    private int menuPrice;


    // Jpa

    @JoinColumn(name = "category_code")
    @ManyToOne(cascade = CascadeType.PERSIST)
    // category 가 필요한 시점에 불러온다
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;


}
