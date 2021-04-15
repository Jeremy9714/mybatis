package com.jeremy.dao;

import com.jeremy.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 接口式编程
 *
 * @author Chenyang
 * @create 2021-04-14-17:18
 */
public interface EmployeeMapper {

    //查
    Employee getEmp(Integer id);

    //传入多个参数的sql
    //方式一: 通过@Param注解实现命名参数，指定将多个参数封装到map时的key值
    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    //方式二: 直接传入map集合
    Employee getEmpByMap(Map<String, Object> map);

    //增
    void addEmp(Employee employee);

    //删
    Boolean deleteEmp(Integer id);

    //改
    Integer updateEmp(Employee employee);
}
