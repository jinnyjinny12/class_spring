package com.ohgiraffers.section01.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("bookDAO")
//@Primary
////자동으로 빈의 우선순위를 1순위로 해줌
public class BookDAOImpl implements BookDAO{

    private Map<Integer, BookDTO> bookList;

    public BookDAOImpl() {
        this.bookList = new HashMap<>();
        bookList.put(1, new BookDTO(1, 12354, "자바의 정석", "남궁성", "도우출판", new Date()));
        bookList.put(2, new BookDTO(2, 232323, "칭고의 정석", "고래", "고래출판", new Date()));
    }

    @Override
    public List<BookDTO> selectBookList() {
        return new ArrayList<>(bookList.values());
    }

    @Override
    public BookDTO selectOneBook(int sequence) {
        return bookList.get(sequence);
    }
}
