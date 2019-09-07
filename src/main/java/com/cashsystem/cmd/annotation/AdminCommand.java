package com.cashsystem.cmd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//管理员命令的注解
@Retention(RetentionPolicy.RUNTIME)//源注解，注解不仅被保存到class文件中，jvm加载class文件后依然存在
@Target(ElementType.TYPE)//用到类的接口，说明了annotation所修饰的对象范围；TYPE说明AdminCommand只会注解到类上
public @interface AdminCommand {
}
