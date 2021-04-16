package com.jeremy.dao;

import com.jeremy.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 接口式编程
 *
 * @author Chenyang
 * @create 2021-04-14-17:18
 */
public interface EmployeeMapper {

    //传入多个参数的sql
    //方式一: 通过@Param注解实现命名参数，指定将多个参数封装到map时的key值
    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    //方式二: 直接传入map集合
    Employee getEmpByMap(Map<String, Object> map);

    //返回集合
    List<Employee> getEmpListByLastName(String lastName);

    //返回一条记录的map: key是属性名，value是属性值
    Map<String, Object> getEmpAsMapByLastName(String lastName);

    //返回封装多条记录的map: value是记录
    @MapKey("id") //指定封装时使用哪个属性作为key
    Map<Integer, Employee> getEmpMapByLastName(String lastName);

    Employee getEmp(Integer id);

    void addEmp(Employee employee);

    Boolean deleteEmp(Integer id);

    Integer updateEmp(Employee employee);
}
