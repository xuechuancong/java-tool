package org.jeff.javatool.tool.simplebean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by weijianfu on 2017/5/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSimpleBean {

    @Test
    public void simpleBeanLombok() {
        SimpleBeanLombokEntity simpleBeanLombokEntity = new SimpleBeanLombokEntity();

        simpleBeanLombokEntity.setId("My ID");
        simpleBeanLombokEntity.setName("My Name");

        System.out.println(simpleBeanLombokEntity.getId());
        System.out.println(simpleBeanLombokEntity.getName());
    }

}
