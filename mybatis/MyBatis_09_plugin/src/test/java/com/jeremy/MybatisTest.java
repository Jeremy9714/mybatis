package com.jeremy;

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

/**
 * @author Chenyang
 * @create 2021-04-14-18:49
 */
public class MybatisTest {


    /**
     * 四大对象创建时不是直接返回的，而是interceptorChain.pluginAll(对象)
     *      public Object pluginAll(Object target){
     *          for(Interceptor interceptor: interceptors){
     *              target = interceptor.plugin(target);
     *          }
     *          return target;
     *      }
     * 该方法会获取到所有Interpretor(插件需要实现的接口)
     *      *调用interceptor.plugin(对象)，并返回包装后的对象
     * 插件机制，允许使用插件来为目标对象创建一个代理对象，该代理对象可以拦截到四大对象的每一个执行
     */

    //编写插件
    @Test
    public void testPlugin() {
        //编写接口Interceptor的实现类
        //多个插件存在时，会产生多层代理
    }

    @Test
    public void test() throws IOException {
        /**
         * 解析配置文件的每一个信息并保存到Configuration中，返回包含Configuration的DefaultSqlSession
         *      Configuration中的MapperStatement代表一个增删改查标签的全部信息
         */
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        /**
         * 返回一个DefaultSqlSession对象，包含Executor和Configuration
     *          在这一步中会创建Executor对象，并用每一个拦截器来包装该Executor对象
         */
        try (SqlSession session = sqlSessionFactory.openSession()) {

            /**
             * getMapper方法中，会根据接口类型从KnownMapper中获取MapperProxyFactory
             *      通过MApperProxyFactory创建MapperProxy代理对象, 该对象包含了DefaultSqlSession对象
             */
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
