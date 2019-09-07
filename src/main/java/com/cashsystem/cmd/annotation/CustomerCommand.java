package com.cashsystem.cmd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//用户命令的注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)//用到类的接口
public @interface CustomerCommand {
}
