package org.jeff.javatool.tool.myactiviti.entity;

import com.google.common.collect.Lists;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 流程定义列表传输对象
 * Created by weijianfu on 2016/11/30.
 */
public class TProcessDefinitionList implements Serializable {

    /**
     * 流程定义实例
     */
    private ProcessDefinition processDefinition;
    /**
     * 流程定义实例所属的deployment
     */
    private Deployment deployment;

    public TProcessDefinitionList(){

    }

    public TProcessDefinitionList(ProcessDefinition processDefinition, Deployment deployment){
        this.processDefinition = processDefinition;
        this.deployment = deployment;
    }

    public static List<TProcessDefinitionList> parseList(List<ProcessDefinition> processDefinitionList, Map<String, Deployment> mapProcessDefinition2Deployment) {
        List<TProcessDefinitionList> tProcessDefinitionListList = Lists.newArrayList();
        if(processDefinitionList == null || processDefinitionList.size() <= 0){
            return tProcessDefinitionListList;
        }
        for (ProcessDefinition processDefinition : processDefinitionList) {
            tProcessDefinitionListList.add(new TProcessDefinitionList(processDefinition
                    , mapProcessDefinition2Deployment == null ? null : mapProcessDefinition2Deployment.get(processDefinition.getId())));
        }
        return tProcessDefinitionListList;
    }

    public ProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }

    public Deployment getDeployment() {
        return deployment;
    }

    public void setDeployment(Deployment deployment) {
        this.deployment = deployment;
    }
}
