package com.jeremy.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @author Chenyang
 * @create 2021-04-19-17:52
 */

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = {java.sql.Statement.class})
})
public class MySecondPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MySecondPlugin拦截的方法: " + invocation.getMethod());
        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MySecondPlugin正在包装对象: " + target);
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
//        System.out.println("properties: " + properties);
    }
}
