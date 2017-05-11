package org.jeff.javatool.tool;

/**
 * Created by weijianfu on 2017/5/11.
 */
public class IocEntity {

    private String name;

    public IocEntity() {

    }

    public IocEntity(String name) {
        this.name = name;
    }


    public void get(String some){
        System.out.println(String.format("IocTest.get(): %s", some));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
