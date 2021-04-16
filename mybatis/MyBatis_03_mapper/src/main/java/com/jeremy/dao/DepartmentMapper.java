package com.jeremy.dao;

import com.jeremy.bean.Department;

/**
 * @author Chenyang
 * @create 2021-04-16-17:31
 */
public interface DepartmentMapper extends EmployeeMapperPlus {

    Department getDeptById(Integer id);

    Department getDeptByIdPlus(Integer id);
}
