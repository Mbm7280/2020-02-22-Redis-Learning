package com.home.mbm.model;

import java.io.Serializable;

/**
* @Package: com.home.mbm.model
* @ClassName: User
* @Description: model
 *      实体类
* @Author: mbm
* @date: 2020/2/4 20:01
* @Version: 1.0
*/
public class User implements Serializable {
    private Long id;

    private String name;

    private Integer age;

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}