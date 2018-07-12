package com.sz.entity;

import java.util.List;

public class Person {

    private Integer person_id;

    private String person_name;

    private Long person_number;

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public Long getPerson_number() {
        return person_number;
    }

    public void setPerson_number(Long person_number) {
        this.person_number = person_number;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", person_name='" + person_name + '\'' +
                ", person_number='" + person_number + '\'' +
                '}';
    }
}
