package org.jeff.javatool.tool.myactiviti.tool;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * Created by weijianfu on 2016/12/19.
 */
//@Component
public class TestTransactionalImpl {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TaskService taskService;

    @Transactional(rollbackFor = {Throwable.class})
    public void test() throws Exception {
        jdbcTemplate.update("update act_ru_task set name_ = '3' where id_ = '1370005'");

        try {
            HashMap<String, Object> variables = new HashMap<>();
            variables.put("setVariables", "setVariables");
            taskService.setVariables("1370005", variables);


            variables.put("money", 1.0);
            taskService.complete("1370005", variables);
        }catch (Exception e){

        }



        if(true){
            throw new Exception("!!!!!!!!");
        }

        jdbcTemplate.update("update act_ru_task set name_ = '4' where id_ = '1370005'");

    }
}
