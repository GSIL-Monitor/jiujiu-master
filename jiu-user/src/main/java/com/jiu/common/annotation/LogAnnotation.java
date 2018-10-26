package com.jiu.common.annotation;

import java.lang.annotation.*;

/**
 * @author lujj
 * @Date 2018/10/26 21:58
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
}
