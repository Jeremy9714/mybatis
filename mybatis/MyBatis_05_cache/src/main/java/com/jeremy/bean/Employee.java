package com.jeremy.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author Chenyang
 * @create 2021-04-14-19:17
 */

@Alias("emp") //批量起别名的情况下，可以通过Alias注解可以给类型起新的别名
public class Employee implements Serializable {
    private static final long serialVersionUID = 123L;

    private Integer id;
    private String lastName;
    private String gender;
    private String email;
    private Department dept;

    public Employee() {
    }

    public Employee(Integer id, String lastName, String gender, String email) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return dept;
    }

    public void setDepartment(Department department) {
        this.dept = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
