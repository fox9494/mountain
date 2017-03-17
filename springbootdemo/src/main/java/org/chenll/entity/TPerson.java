package org.chenll.entity;

import java.util.Date;

/**
 * Created by chenlile on 17-3-17.
 */
public class TPerson {

    private Long id;


    private String name;

    private Integer age;

    private Date birth;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
