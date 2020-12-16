package com.zengc.core.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * * @author yc
 * * @data 2018年7月28日
 * * @description 通过AOP切面设置全局事务，拦截service包下面所有方法
 * * AOP术语：通知（Advice）、连接点（Joinpoint）、切入点（Pointcut)、切面（Aspect）、目标(Target)、代理(Proxy)、织入（Weaving）
 */

@Aspect
@Configuration
public class TxConfigBeanName {
    private static final int TX_METHOD_TIMEOUT = 5000;

    private static final String AOP_POINTCUT_EXPRESSION =
            "(execution (* com.zengc.*.service.impl.*.*(..)))" ;

//    @Autowired
//    private PlatformTransactionManager transactionManager;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        Map<String, TransactionAttribute> txMap = new HashMap<>();

        txMap.put("save*", requiredTx);
        txMap.put("remove*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("batch*", requiredTx);
        txMap.put("clear*", requiredTx);
        txMap.put("add*", requiredTx);
        txMap.put("append*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("edit*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("do*", requiredTx);
        txMap.put("create*", requiredTx);
        /*select,count开头的方法,开启只读,提高数据库访问性能*/
        txMap.put("select*", readOnlyTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("valid*", readOnlyTx);
        txMap.put("list*", readOnlyTx);
        txMap.put("count*", readOnlyTx);
        txMap.put("find*", readOnlyTx);
        txMap.put("load*", readOnlyTx);
        txMap.put("search*", readOnlyTx);
        txMap.put("*", requiredTx);

        source.setNameMap(txMap);
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
