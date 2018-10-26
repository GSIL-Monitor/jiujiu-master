package com.jiu.common.aspect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author lujj
 * @Date 2018/10/26 22:00
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect {

    private final static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    //切点
    @Pointcut(value = ("@annotation(com.jiu.common.annotation.LogAnnotation)"))
    public void annotation() {}

    @Before("annotation()")
    public void before(JoinPoint joinPoint) {
        log.info("前置通知开始.....");
        log.info("请求方法=>"+joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");

        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                if(joinPoint.getArgs()[i] instanceof String){
                    params += joinPoint.getArgs()[i] + ";";
                    continue;
                }else if(joinPoint.getArgs()[i] instanceof Integer){
                    params += joinPoint.getArgs()[i] + ";";
                    continue;
                }else if(joinPoint.getArgs()[i] instanceof Double){
                    params += joinPoint.getArgs()[i] + ";";
                    continue;
                }else{
                    params += gson.toJson(joinPoint.getArgs()[i]) + ";";
                }
            }
        }
        log.info("请求参数=>"+params);
    }

    @After("annotation()")
    public void after(JoinPoint joinPoint) {
        log.info("后置通知。。。");
    }

    @AfterReturning("annotation()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("返回通知。。。");
    }

    @AfterThrowing(value = "annotation()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Throwable e) {
        log.info("异常通知。。。");
    }
}
