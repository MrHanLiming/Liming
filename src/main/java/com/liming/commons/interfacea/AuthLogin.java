package com.liming.commons.interfacea;

import java.lang.annotation.*;

/**
 * <p>
 *     此注解是为了校验所有登录身份的
 *     默认是需要登录
 *     可作用于类和方法上
 * </p>
 * <p>
 *     @Retention 元注解
 *     1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 *     2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 *     3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 *
 *      @Target Annotation所修饰的对象范围
 *      METHOD 可用于方法上
 *      TYPE   可用于类或者接口上
 *      ANNOTATION_TYPE 可用于注解类型上（被@interface修饰的类型）
 *      CONSTRUCTOR 可用于构造方法上
 *      FIELD  可用于域上
 *      LOCAL_VARIABLE 可用于局部变量上
 *      PACKAGE 用于记录java文件的package信息
 *      PARAMETER 可用于参数上
 *
 *      @Inherited 阐述了某个被标注的类型是被继承的。
 *      如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 * </p>
 * @author hlm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Documented
@Inherited
public @interface AuthLogin {
    boolean needLogin() default true;
}
