package com.jeremy;

import com.jeremy.bean.Department;
import com.jeremy.bean.Employee;
import com.jeremy.dao.DepartmentMapper;
import com.jeremy.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Chenyang
 * @create 2021-04-16-15:31
 */
public class EmployeeTest2 {

    private static SqlSessionFactory sqlSessionFactory;

    //抽取出获取SqlSessionFactory的步骤
    static {
        //全局配置文件的路径
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //关联查询定义collection集合对象
    @Test
    public void resultMapTest2() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
            Department dept = mapper.getDeptByIdPlus(1);
            System.out.println(dept);
            dept.getEmps().forEach(System.out::println);
        }
    }

    //resultMap实现高级结果集映射
    @Test
    public void resultMapTest1() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
//            Employee emp = mapper.getEmpById(2);
//            Employee emp = mapper.getEmpAndDept(2);
            Employee emp = mapper.getEmpByIdStep(2);
            System.out.println(emp);
            System.out.println(emp.getDepartment());
        }
    }

}
