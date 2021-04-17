package com.jeremy.dao;

import com.jeremy.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * 注册接口
 * @author Chenyang
 * @create 2021-04-15-14:12
 */
public interface EmployeeMapperAnnotation {

    @Select("select * from tb_employee where id = #{id}")
    Employee getEmployee(Integer id);

}
