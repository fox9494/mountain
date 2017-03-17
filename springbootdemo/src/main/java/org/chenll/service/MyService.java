package org.chenll.service;


import org.chenll.dao.TPersonMapper;
import org.chenll.entity.TPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by chenlile on 17-3-15.
 */

@Service
public class MyService {

    @Autowired
    private TPersonMapper tPersonMapper;


    public String doService(){
        return "doService method";
    }


    @Transactional
    public void addPerson(){
        TPerson person = new TPerson();
        person.setAge(20);
        person.setBirth(new Date());
        person.setName("cll");
        tPersonMapper.insert(person);


        TPerson person2 = new TPerson();
        person2.setAge(12);
        person2.setBirth(new Date());
        person2.setName("cd");

        tPersonMapper.insert(person);
    }
}
