package org.chenll.entity;

import java.util.Date;

/**
 * Created by chenlile on 17-3-17.
 *
 *
 * 创建数据库表
 * CREATE TABLE `t_student` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `name` char(20) DEFAULT NULL,
     `age` int(11) DEFAULT NULL,
     `birth` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
     PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf-8;


 */
public class TStudent {

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
