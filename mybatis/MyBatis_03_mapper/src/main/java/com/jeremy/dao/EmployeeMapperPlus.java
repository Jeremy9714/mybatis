package com.jeremy.dao;

import com.jeremy.bean.Employee;

/**
 * @author Chenyang
 * @create 2021-04-16-15:26
 */
public interface EmployeeMapperPlus {

    Employee getEmpById(Integer id);

    //查询员工信息及所属部门信息
    Employee getEmpAndDept(Integer id);
}
