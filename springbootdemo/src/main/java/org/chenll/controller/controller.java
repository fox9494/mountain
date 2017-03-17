package org.chenll.controller;

import org.chenll.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenlile on 17-3-15.
 */

//结合了 @ResponseBody 与 @Controller 注解的功能
//表示这个controller提供json，xml或其他自定义的内容媒体返回
@RestController
public class controller {

    @Autowired
    private MyService myService;


    @Value(value = "${app.name}")//获取配置
    private String appName;


    @RequestMapping("/")
    public Person show(){
        return new Person("cll",123);
    }

    @RequestMapping("/service")
    public String service(){
        System.out.println(appName);
        return myService.doService();
    }


    @RequestMapping("/person/add")
    public String personAdd(){
        myService.addPerson();
        return "ok";
    }


    public class Person{
        private String name;
        private  int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
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
    }
}
