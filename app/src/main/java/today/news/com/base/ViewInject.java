package today.news.com.base;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
//@Retention：注解的保留期 @Target：作用域
@Retention(RUNTIME) //注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
@Target(TYPE)//可以给一个类型进行注解，比如类、接口、枚举
public @interface ViewInject {
    int mainlayoutid() default  -1;
}
