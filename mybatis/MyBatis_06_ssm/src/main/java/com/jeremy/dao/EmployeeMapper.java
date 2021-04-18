package com.jeremy.dao;

import com.jeremy.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口式编程
 *
 * @author Chenyang
 * @create 2021-04-14-17:18
 */

public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    List<Employee> getEmps();

}
