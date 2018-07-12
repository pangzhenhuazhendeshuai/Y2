package com.sz.entity;

import java.util.List;

public class BookInfo {
    private Integer id;

    private Integer ISBN;

    private String name;

    private double price;

    private double discount;

    private String publisher;

    private Integer person_id;

    private Person personList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public Person getPersonList() {
        return personList;
    }

    public void setPersonList(Person personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +
                ", ISBN=" + ISBN +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", publisher='" + publisher + '\'' +
                ", person_id=" + person_id +
                ", personList=" + personList +
                '}';
    }
}
