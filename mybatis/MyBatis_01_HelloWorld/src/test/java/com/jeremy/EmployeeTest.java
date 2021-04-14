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

    //接口式编程
    @Test
    public void test2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            //获取接口的实现类对象(自动创建的代理对象)
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployee(1);
            System.out.println(employee);
        }
    }

    /*
      1.根据xml配置文件(全局配置文件),创建一个SqlSessionFactory对象，
      2.sql映射文件配置了每一个sql,以及sql的封装规则等
      3.将sql映射文件注册到全局配置文件中
     */
    @Test
    public void test1() throws IOException {
        //根据全局配置文件得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //获取SqlSession实例，可以直接执行已经映射的sql语句
        //一个SqlSession实例代表和数据库的一次会话，使用完要关闭
        try (SqlSession session = sqlSessionFactory.openSession()) {
            //通过sql的唯一表示来告诉mybatis执行哪个sql
            Employee employee = session.selectOne("com.jeremy.mybatis.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        }
    }

    //抽取出获取SqlSessionFactory的步骤并封装进方法中
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        //全局配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
