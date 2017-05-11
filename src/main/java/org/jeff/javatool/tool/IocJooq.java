package org.jeff.javatool.tool;

import org.joor.Reflect;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * 简洁、好用的反射库
 * Created by weijianfu on 2017/5/11.
 */
@Service
public class IocJooq {

    private static final String CLASS_NAME = "org.jeff.javatool.tool.IocEntity";

    /**
     * 旧的反射方法
     */
    public void oldMethod() {
        try {
            Class<?> clazz = Class.forName(CLASS_NAME);
            final Method m = IocEntity.class.getMethod("get", String.class);
            m.setAccessible(true);
            m.invoke(clazz.newInstance(), "hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新的反射方法
     */
    public void newMethod() {
        Reflect.on(CLASS_NAME).create().call("get", "hello");
    }

    /**
     * 新的反射方法更多用处
     */
    public void more(){
        //调用重载构造函数，并获取返回值
        Reflect.on(CLASS_NAME).create("My name").call("getName").get();
    }

    /**
     * Reflect.on 包裹一个类或者对象，表示在这个类或对象上进行反射，类的值可以使Class,也可以是完整的类名（包含包名信息）
     * Reflect.create 用来调用之前的类的构造方法，有两种重载，一种有参数，一种无参数
     * Reflect.call 方法调用，传入方法名和参数，如有返回值还需要调用get
     * Reflect.get 获取（field和method返回）值相关，会进行类型转换，常与call和field组合使用
     * Reflect.field 获取属性值相关，需要调用get获取该值
     * Reflect.set 设置属性相关。
     */
}
