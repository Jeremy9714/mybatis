package com.jeremy;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeremy.bean.Employee;
import com.jeremy.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chenyang
 * @create 2021-04-14-18:49
 */
public class MybatisTest {

    @Test
    public void test1() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            //使用PageHelper分页插件
            Page<Object> page = PageHelper.startPage(2, 1);
            List<Employee> emps = mapper.getEmps();

            //可以通过PageInfo包装查询的结果，并且还可以传入一个连续显示页码数
            PageInfo<Employee> pageInfo = new PageInfo<>(emps, 3);

            emps.forEach(System.out::println);

//            //可以通过Page对象获取分页的详细信息
//            System.out.println("当前页数: " + page.getPageNum());
//            System.out.println("总记录数: " +page.getTotal());
//            System.out.println("每页的记录数: " + page.getPageSize());
//            System.out.println("总页数: " + page.getPages());

            //可以通过PageInfo对象获取分页的详细信息
            System.out.println("当前页数: " + pageInfo.getPageNum());
            System.out.println("总记录数: " + pageInfo.getTotal());
            System.out.println("每页的记录数: " + pageInfo.getPageSize());
            System.out.println("总页数: " + pageInfo.getPages());
            System.out.println("当前页是否是第一页: " + pageInfo.isIsFirstPage());
            System.out.println("当前页是否有下一页: " + pageInfo.isHasNextPage());

            //获取真正连续显示的页码
            int[] navigatepageNums = pageInfo.getNavigatepageNums();
            System.out.println(Arrays.toString(navigatepageNums));
        } finally {
            session.close();
        }
    }


    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        //全局配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
