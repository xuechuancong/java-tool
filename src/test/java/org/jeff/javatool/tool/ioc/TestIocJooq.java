package org.jeff.javatool.tool.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 简洁、好用的反射库，对原生java reflect进行封装，屏蔽了无关细节。
 * Created by weijianfu on 2017/5/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestIocJooq {

    @Autowired
    private IocJooq iocJooq;

    @Test
    public void iocJoop() {
        System.out.println("---------------------Old method------------------");
        iocJooq.oldMethod();

        System.out.println("\n-------------------New method------------------");
        iocJooq.newMethod();

        System.out.println("\n----------------------More---------------------");
        iocJooq.more();
    }
}
