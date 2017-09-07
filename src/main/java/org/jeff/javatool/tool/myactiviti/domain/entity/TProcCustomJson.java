package org.jeff.javatool.tool.myactiviti.domain.entity;

import org.jeff.javatool.tool.myactiviti.entity.TCustomFlow;
import org.jeff.javatool.tool.myactiviti.entity.TCustomTask;

import java.io.Serializable;
import java.util.Map;

/**
 * TProcCustom实体中json字段反序列化的实体
 * Created by weijianfu on 2017/4/26.
 */
public class TProcCustomJson implements Serializable {

    /**
     * 任务信息
     * <p/>
     * key:任务定义KEY
     * value：任务主体
     */
    private Map<String, TCustomTask> tasks;

    /**
     * 流转信息
     * <p/>
     * key:前端自定义流转ID
     * value：流转主体
     */
    private Map<String, TCustomFlow> flows;


    public Map<String, TCustomTask> getTasks() {
        return tasks;
    }

    public void setTasks(Map<String, TCustomTask> tasks) {
        this.tasks = tasks;
    }

    public Map<String, TCustomFlow> getFlows() {
        return flows;
    }

    public void setFlows(Map<String, TCustomFlow> flows) {
        this.flows = flows;
    }
}
