package com.jeremy.dao;

import com.jeremy.bean.Employee;

/**
 * 接口式编程
 * @author Chenyang
 * @create 2021-04-14-17:18
 */
public interface EmployeeMapper {

    Employee getEmployee(Integer id);
}
