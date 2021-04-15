package com.jeremy;

import com.jeremy.bean.Employee;
import com.jeremy.dao.EmployeeMapper;
import com.jeremy.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Chenyang
 * @create 2021-04-14-19:18
 */
public class EmployeeTest {

    private static SqlSessionFactory sqlSessionFactory;

    //抽取出获取SqlSessionFactory的步骤
    static{
        //全局配置文件的路径
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            //获取接口的实现类对象(自动创建的代理对象)
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployee(1);
            System.out.println(employee);
        }
    }
}
