package com.jeremy.dao;

import com.jeremy.bean.Employee;

import java.util.List;

/**
 * @author Chenyang
 * @create 2021-04-16-20:42
 */
public interface EmployeeMapperDynamicSQL {

    //if判断
    List<Employee> getEmpListByIf(Employee employee);

    //trim截取
    List<Employee> getEmpListByTrim(Employee employee);

    //choose分支选择
    List<Employee> getEmpListByChoose(Employee employee);

    //set封装修改条件
    int updateEmpBySet(Employee employee);

}
