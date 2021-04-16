package com.jeremy.dao;

import com.jeremy.bean.Employee;

import java.util.List;

/**
 * @author Chenyang
 * @create 2021-04-16-20:42
 */
public interface EmployeeMapperDynamicSQL {

    //if判断测试
    List<Employee> getEmpListByIf(Employee employee);

    //trim截取测试
    List<Employee> getEmpListByTrim(Employee employee);

}
