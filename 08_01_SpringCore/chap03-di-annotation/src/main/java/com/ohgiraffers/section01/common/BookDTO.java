package com.ohgiraffers.section01.common;

import java.util.Date;

public class BookDTO {

    private int sequence;
    private int isbn;
    private String title;
    private String awthor;
    private String publisher;
    private Date createdDate;

    public BookDTO(int sequence, int isbn, String title, String awthor, String publisher, Date createdDate) {
        this.sequence = sequence;
        this.isbn = isbn;
        this.title = title;
        this.awthor = awthor;
        this.publisher = publisher;
        this.createdDate = createdDate;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAwthor() {
        return awthor;
    }

    public void setAwthor(String awthor) {
        this.awthor = awthor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "sequence=" + sequence +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", awthor='" + awthor + '\'' +
                ", publisher='" + publisher + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
