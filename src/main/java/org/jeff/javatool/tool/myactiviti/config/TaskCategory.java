package org.jeff.javatool.tool.myactiviti.config;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;

/**
 * 任务类型
 * Created by weijianfu on 2016/12/7.
 */
public enum TaskCategory {

    NORMAL("NORMAL"),//常规任务
    ADD_SIGN("ADD_SIGN"),//加签任务
    COUNTER_SIGN("COUNTER_SIGN"),//会签任务
    PARALLEL_SIGN("PARALLEL_SIGN"),//并行审签任务
    SERVICE("SERVICE"),//服务任务
    DELEGATE("DELEGATE");//委托任务

    private String category;

    /**
     * 是否是流程定义中的任务
     *
     * @param historicTaskInstance
     * @return
     */

    public static boolean isProcTask(HistoricTaskInstance historicTaskInstance) {
        if (historicTaskInstance == null) {
            return false;
        }
        return isProcTask(historicTaskInstance.getCategory());
    }

    public static boolean isProcTask(Task task) {
        if (task == null) {
            return false;
        }
        return isProcTask(task.getCategory());
    }

    private static boolean isProcTask(String taskCategory) {
        if (StringUtil.isBlank(taskCategory)) {
            return false;
        }
        if (NORMAL.getCategory().equals(taskCategory)
                || COUNTER_SIGN.getCategory().equals(taskCategory)
                || PARALLEL_SIGN.getCategory().equals(taskCategory)
                || DELEGATE.getCategory().equals(taskCategory)) {
            return true;
        }
        return false;
    }


    public static TaskCategory getEntity(String defCategory) {
        if (defCategory == null || defCategory.length() <= 0) {
            return null;
        }

        if (NORMAL.getCategory().equals(defCategory)) {
            return NORMAL;
        } else if (ADD_SIGN.getCategory().equals(defCategory)) {
            return ADD_SIGN;
        } else if (COUNTER_SIGN.getCategory().equals(defCategory)) {
            return COUNTER_SIGN;
        } else if (PARALLEL_SIGN.getCategory().equals(defCategory)) {
            return PARALLEL_SIGN;
        } else if (DELEGATE.getCategory().equals(defCategory)) {
            return DELEGATE;
        } else {
            return null;
        }
    }

    TaskCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
