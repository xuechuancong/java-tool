package org.jeff.javatool.tool.simplebean;

import lombok.Data;

/**
 * 节省Get、Set方法，IDE需要装Lombok插件
 * Created by weijianfu on 2017/5/11.
 */
@Data
public class SimpleBeanLombokEntity {

    private String id;
    private String name;

}
