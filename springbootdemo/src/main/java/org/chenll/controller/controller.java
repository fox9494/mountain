package org.chenll.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.chenll.entity.TPerson;
import org.chenll.entity.TStudent;
import org.chenll.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiIgnore
    @RequestMapping("/")
    public Person show(){
        return new Person("cll",123);
    }

    @ApiIgnore
    @RequestMapping("/service")
    public String service(){
        System.out.println(appName);
        return myService.doService();
    }


    @ApiOperation(value = "添加测试用例,参数是对象",notes = "插入并测试事务,参数是对象")
    @ApiImplicitParam(name = "person",value = "TPerson实体类",required = true,dataType = "TPerson")
    @RequestMapping(value = "/person/add",method = RequestMethod.POST)
    public String personAdd(@RequestBody TPerson person){
        myService.addPerson(person);
        return "ok";
    }

    @ApiOperation(value = "添加测试用例,参数是对象",notes = "插入并测试事务,参数是对象")
    @ApiImplicitParam(name = "student",value = "TStudent",required = true,dataType = "TStudent")
    @RequestMapping(value = "/student/add",method = RequestMethod.POST)
    public String studentAdd(@RequestBody TStudent student){
        myService.addStudent(student);
        return "ok";
    }


    @ApiOperation(value = "添加测试用例,参数是字段",notes = "插入并测试事务,参数是字段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String",paramType = "query"),//paramType = "query"会影响参数的注入
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "Integer",paramType = "query")
    })
    @RequestMapping(value = "/person/add-field",method = RequestMethod.GET)
    public String personAdd( @RequestParam String name, @RequestParam int age){
        TPerson person = new TPerson();
        person.setName(name);
        person.setAge(age);
        myService.addPerson(person);
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
