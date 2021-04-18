package com.jeremy;

import com.jeremy.bean.Employee;
import com.jeremy.bean.EmployeeExample;
import com.jeremy.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenyang
 * @create 2021-04-17-17:02
 */
public class CacheTest {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputstream = null;
        try {
            inputstream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMbg() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

//    @Test
//    public void testMyBatis3Simple() {
//        try (SqlSession session = sqlSessionFactory.openSession()) {
//            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//            List<Employee> employees = mapper.selectAll();
//            employees.forEach(System.out::println);
//        }
//    }

    @Test
    public void testMyBatis3() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            /**
             * select * from tb_employee
             * where
             * (last_name like 't%' and gender = '0')
             * or
             * email like '%gmail%'
             */

            //xxxEmample类用于封装查询条件
            EmployeeExample employeeExample = new EmployeeExample();

            //xxxExample类的内部类Criteria就是拼装查询条件
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();

            //Criteria类拥有javaBean类中各种属性的各种拼接条件
            criteria.andLastNameLike("t%");
            //将多个条件拼装
            criteria.andGenderEqualTo("0");

            //实现or条件，需要新建一个Criteria对象设定or的条件
            EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();
            criteria1.andEmailLike("%gmail%");

            //通过or(Criteria)方法加入or条件
            employeeExample.or(criteria1);

            List<Employee> employees = mapper.selectByExample(employeeExample);
            employees.forEach(System.out::println);
        }
    }

}
