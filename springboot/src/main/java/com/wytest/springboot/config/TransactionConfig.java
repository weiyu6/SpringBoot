package com.wytest.springboot.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * TODO 事务管理配置，拦截service包下所有的方法
 * @author weiyu
 * @date 2021/2/4
 */
@Aspect
@Configuration
public class TransactionConfig {


}
