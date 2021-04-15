package com.jeremy.dao;

import com.jeremy.bean.Employee;

/**
 * 接口式编程
 *
 * @author Chenyang
 * @create 2021-04-14-17:18
 */
public interface EmployeeMapper {

    //查
    Employee getEmp(Integer id);

    //增
    void addEmp(Employee employee);

    //删
    Boolean deleteEmp(Integer id);

    //改
    Integer updateEmp(Employee employee);
}
