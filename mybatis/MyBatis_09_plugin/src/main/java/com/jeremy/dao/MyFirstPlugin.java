package com.jeremy.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * @author Chenyang
 * @create 2021-04-19-17:27
 */

//使用@Intercepts注解完成插件签名
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class MyFirstPlugin implements Interceptor {

    //拦截目标对象的目标方法的执行
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获取目标方法的名称
        System.out.println("MyFirstPlugin拦截的方法: " + invocation.getMethod());

        //获取拦截对象
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象为: " + target);

        //获取拦截对象的元数据
        MetaObject metaObject = SystemMetaObject.forObject(target);

        //获取拦截对象中的指定属性值
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句的参数为: " + value);

        //修改拦截对象中的指定属性值
        metaObject.setValue("parameterHandler.parameterObject",2);

        //执行目标方法
        Object proceed = invocation.proceed();

        //返回执行后的返回值
        return proceed;
    }

    //包装目标对象，即为目标对象创建一个代理对象
    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstPlugin正在包装对象: " + target);

        //使用Plugin的wrap方法来使用当前的Interceptor包装目标对象
        Object wrap = Plugin.wrap(target, this);

        //返回创建的动态代理对象
        return wrap;
    }

    //将插件注册时的property属性注册进来
    @Override
    public void setProperties(Properties properties) {
        System.out.println("properties: " + properties);
    }
}
