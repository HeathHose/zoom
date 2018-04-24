package com.zoom.mongo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by liangbo.zhou on 18-4-24.
 */
public class DynamicMongoDBDataSourceAspect {
    private static final String PREFIX = ".dao.mongo.";

    @Pointcut("execution(* com.zoom.mongo..*.*(..))")
    private void daoMethod() {
        // do nothing
    }

    @Before("daoMethod()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        for (MongoDBDataSource mongoDBDataSource : MongoDBDataSource.values()) {
            if (signature.getDeclaringTypeName().indexOf(PREFIX + mongoDBDataSource.getValue()) > -1) {
                MongodbTemplateContextHolder.setMongoDBTemplate(mongoDBDataSource.getValue());
                break;
            }
        }
    }
}
