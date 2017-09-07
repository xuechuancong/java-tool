package org.jeff.javatool.tool.myactiviti.entity.vo;

import com.google.common.collect.Lists;
import org.jeff.javatool.tool.myactiviti.entity.TProcessDefinitionList;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weijianfu on 2017/5/16.
 */
public class TProcessDefinitionListVO implements Serializable {

    private String id;

    private String category;

    private String name;

    private String key;

    private String description;

    private int version;
    
    

    public static List<TProcessDefinitionListVO> parse(List<TProcessDefinitionList> tProcessDefinitionListList) {
        List<TProcessDefinitionListVO> resultList = Lists.newArrayList();
        if(tProcessDefinitionListList == null || tProcessDefinitionListList.size() <= 0){
            return resultList;
        }
        for (TProcessDefinitionList tProcessDefinitio : tProcessDefinitionListList) {
            TProcessDefinitionListVO vo = new TProcessDefinitionListVO();
            vo.setId(tProcessDefinitio.getProcessDefinition().getId());
            vo.setCategory(tProcessDefinitio.getProcessDefinition().getCategory());
            vo.setName(tProcessDefinitio.getProcessDefinition().getName());
            vo.setKey(tProcessDefinitio.getProcessDefinition().getKey());
            vo.setDescription(tProcessDefinitio.getProcessDefinition().getDescription());
            vo.setVersion(tProcessDefinitio.getProcessDefinition().getVersion());
            resultList.add(vo);
        }
        return resultList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
