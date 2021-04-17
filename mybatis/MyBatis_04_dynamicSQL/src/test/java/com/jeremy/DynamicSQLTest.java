package com.jeremy;

import com.jeremy.bean.Employee;
import com.jeremy.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Chenyang
 * @create 2021-04-16-20:44
 */
public class DynamicSQLTest {
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

    @Test
    public void chooseSet() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee();
            employee.setId(4);
            employee.setEmail("new@qq.com");
            int res = mapper.updateEmpBySet(employee);
            System.out.println(res);
        }
    }

    @Test
    public void chooseTest() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee();
            List<Employee> list = mapper.getEmpListByChoose(employee);
            list.forEach(System.out::println);
        }
    }

    @Test
    public void trimTest() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee();
            List<Employee> list = mapper.getEmpListByTrim(employee);
            list.forEach(System.out::println);
        }
    }

    @Test
    public void ifTest() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee();
//            employee.setId(1);
            employee.setLastName("%t%");
            List<Employee> list = mapper.getEmpListByIf(employee);
            list.forEach(System.out::println);
        }
    }
}
