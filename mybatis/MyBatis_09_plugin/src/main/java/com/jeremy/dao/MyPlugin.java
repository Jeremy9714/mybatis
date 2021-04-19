package com.jeremy.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @author Chenyang
 * @create 2021-04-19-17:27
 */

//使用@Intercepts注解完成插件签名
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class MyPlugin implements Interceptor {

    //拦截目标对象的目标方法的执行
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获取目标方法的名称
        System.out.println("方法名: " + invocation.getMethod());

        //执行目标方法
        Object proceed = invocation.proceed();

        //返回执行后的返回值
        return proceed;
    }

    //包装目标对象，即为目标对象创建一个代理对象
    @Override
    public Object plugin(Object target) {
        System.out.println("正在包装对象: " + target);

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
