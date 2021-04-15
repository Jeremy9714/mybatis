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
    public void test2() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee tom = mapper.getEmpByIdAndLastName(1, "tom");
            System.out.println(tom);
        }
    }

    @Test
    public void test1() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            Employee emp = new Employee(null, "Jack", "1", "Jack@qq.com");

            //添加测试
            mapper.addEmp(emp);

            //获取自增的主键值
            Integer id = emp.getId();
            System.out.println("主键值为: " + id);

            //修改测试
            Integer res2 = mapper.updateEmp(new Employee(id, "Casey", "0", "Casey@gmail.com"));
            System.out.println("受影响行数: " + res2);

            //查询测试
            emp = mapper.getEmp(id);
            System.out.println(emp);

            //删除测试
            Boolean isDeleted = mapper.deleteEmp(3);
            System.out.println("是否成功删除: " + isDeleted);

            //openSession需要手动提交
            session.commit();
        }
    }
}
