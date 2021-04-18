package com.jeremy;

import com.jeremy.bean.Employee;
import com.jeremy.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Chenyang
 * @create 2021-04-14-18:49
 */
public class EmployeeTest {

    @Test
    public void test1() throws IOException {
        //根据全局配置文件得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //获取SqlSession实例，可以直接执行已经映射的sql语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployee(1);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        }
    }

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        //全局配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
