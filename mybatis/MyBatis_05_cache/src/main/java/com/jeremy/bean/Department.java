package com.jeremy.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chenyang
 * @create 2021-04-16-16:09
 */
@Alias("dept")
public class Department implements Serializable {
    private static final long serialVersionUID = 123L;

    private Integer id;
    private String departmentName;
    private List<Employee> emps;

    public Department() {
    }

    public Department(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
