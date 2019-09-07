package com.cashsystem.cmd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//源注解
@Target(ElementType.TYPE)//用到类的接口

public @interface CommandMeta {
    String name();//快捷英文字母  如：DL
    String desc();//实际描述      如：登录
    String group();//分组         如：入口命令
}
