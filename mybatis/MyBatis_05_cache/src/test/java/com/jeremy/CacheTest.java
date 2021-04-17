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
 * @create 2021-04-17-17:02
 */
public class CacheTest {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //一级缓存
    @Test
    public void testFirstLevelCache() {
        SqlSession session1 = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper1 = session1.getMapper(EmployeeMapper.class);
            Employee emp1 = mapper1.getEmp(1);
            System.out.println(emp1);

            EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class);
            Employee emp2 = mapper2.getEmp(1);
            System.out.println(emp2);

            System.out.println(emp1 == emp2);
        } finally {
            session1.close();
            session2.close();
        }
    }

    //二级缓存
    @Test
    public void testSecondLevelCache() {
        SqlSession session1 = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper1 = session1.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class);

            Employee emp1 = mapper1.getEmp(1);
            System.out.println(emp1);
            session1.close();

            //第二次查询，是从二级缓存中获取的数据，并未发送新的sql
            Employee emp2 = mapper2.getEmp(1);
            System.out.println(emp2);
            session2.close();

            System.out.println(emp1 == emp2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
