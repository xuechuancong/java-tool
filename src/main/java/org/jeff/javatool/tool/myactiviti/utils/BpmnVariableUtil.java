package org.jeff.javatool.tool.myactiviti.utils;

import com.google.common.collect.Lists;
import org.activiti.bpmn.model.ExtensionElement;
import org.jeff.javatool.tool.myactiviti.config.WFConfig;
import org.jeff.javatool.tool.myactiviti.entity.TNextTaskInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编码时机：开始任务时，将值存入variable，存入流程实例变量
 * 解码时机：将存入流程实例的变量variable中的值解码为变量，给予接口传输对象
 * Created by weijianfu on 2016/12/1.
 */
public class BpmnVariableUtil {

    /**
     * 编码RoleIds
     * @param extensionElementMap
     * @return
     */
    public static List<String> codeRoleIds(Map<String, List<ExtensionElement>> extensionElementMap){
        List<String> curRoleIds = Lists.newArrayList();
        if(extensionElementMap == null){
            return curRoleIds;
        }
        List<ExtensionElement> curRoleIdsEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CUR_ROLE_ID);
        if (curRoleIdsEE != null && curRoleIdsEE.size() > 0) {
            for (ExtensionElement extensionElement : curRoleIdsEE) {
                curRoleIds.add(extensionElement.getElementText());
            }
        }
        return curRoleIds;
    }


    /**
     * 解码RoleIds
     * @param taskVariables
     * @return
     */
    public static Collection<String> encodeRoleIds(Map<String, Object> taskVariables){
        if(taskVariables == null){
            return Lists.newArrayList();
        }
        return (Collection<String>)taskVariables.get(WFConfig.CUSTOM_PROPERTY_CUR_ROLE_ID);
    }

    /**
     * 编码TNextTaskInfos
     * @param extensionElementMap
     * @return
     */
    public static Collection<String> codeTNextTaskInfos(Map<String, List<ExtensionElement>> extensionElementMap){
        Collection<String> nextTaskInfos = Lists.newArrayList();
        if(extensionElementMap == null){
            return nextTaskInfos;
        }
        List<ExtensionElement> nextTaskInfosEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_NEXT_TASK_INFO);
        if (nextTaskInfosEE != null && nextTaskInfosEE.size() > 0) {
            for (ExtensionElement extensionElement : nextTaskInfosEE) {
                nextTaskInfos.add(extensionElement.getElementText());
            }
        }
        return nextTaskInfos;
    }

    /**
     * 解码TNextTaskInfos
     * @param taskVariables
     * @return
     */
    public static Collection<TNextTaskInfo> encodeTNextTaskInfos(Map<String, Object> taskVariables){
        Collection<TNextTaskInfo> nextTaskInfos = Lists.newArrayList();
        if(taskVariables == null){
            return nextTaskInfos;
        }

        Collection<String> nextTaskInfosStr = (Collection<String>) taskVariables.get(WFConfig.CUSTOM_PROPERTY_NEXT_TASK_INFO);
        if(nextTaskInfosStr != null && nextTaskInfosStr.size() > 0){
            for (String nextTaskInfo : nextTaskInfosStr) {
                nextTaskInfos.add(TNextTaskInfo.parse(nextTaskInfo));
            }
        }
        return nextTaskInfos;
    }

    public static boolean codeCanUpdateAtt(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean canUpdateAtt = false;
        if(extensionElementMap == null){
            return canUpdateAtt;
        }
        List<ExtensionElement> curRoleIdsEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CAN_UPDATE_ATT);
        if(curRoleIdsEE == null){
            return canUpdateAtt;
        }
        ExtensionElement extensionElement = curRoleIdsEE.get(0);
        if(extensionElement != null){
            canUpdateAtt = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return canUpdateAtt;
    }

    public static boolean codeCanDownloadAtt(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean canDownloadAtt = false;
        if(extensionElementMap == null){
            return canDownloadAtt;
        }
        List<ExtensionElement> curRoleIdsEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CAN_DOWNLOAD_ATT);
        if(curRoleIdsEE == null){
            return canDownloadAtt;
        }
        ExtensionElement extensionElement = curRoleIdsEE.get(0);
        if(extensionElement != null){
            canDownloadAtt = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return canDownloadAtt;
    }

    public static boolean codeCanDeleteAtt(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean canDeleteAtt = false;
        if(extensionElementMap == null){
            return canDeleteAtt;
        }
        List<ExtensionElement> curRoleIdsEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CAN_DELETE_ATT);
        if(curRoleIdsEE == null){
            return canDeleteAtt;
        }
        ExtensionElement extensionElement = curRoleIdsEE.get(0);
        if(extensionElement != null){
            canDeleteAtt = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return canDeleteAtt;
    }

    public static boolean codeCanReadAtt(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean canReadAtt = false;
        if(extensionElementMap == null){
            return canReadAtt;
        }
        List<ExtensionElement> curRoleIdsEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CAN_READ_ATT);
        if(curRoleIdsEE == null){
            return canReadAtt;
        }
        ExtensionElement extensionElement = curRoleIdsEE.get(0);
        if(extensionElement != null){
            canReadAtt = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return canReadAtt;

    }

    public static boolean codeCanEditPage(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean canEditPage = false;
        if(extensionElementMap == null){
            return canEditPage;
        }
        List<ExtensionElement> elements = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CAN_EDIT_PAGE);
        if(elements == null){
            return canEditPage;
        }
        ExtensionElement extensionElement = elements.get(0);
        if(extensionElement != null){
            canEditPage = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return canEditPage;
    }

    public static boolean encodeCanUpdateAtt(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return false;
        }
        Object value =  (boolean)taskVariables.get(WFConfig.CUSTOM_PROPERTY_CAN_UPDATE_ATT);
        return value == null ? false : (boolean) value;
    }

    public static boolean encodeCanDownloadAtt(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return false;
        }
        Object value =  (boolean)taskVariables.get(WFConfig.CUSTOM_PROPERTY_CAN_DOWNLOAD_ATT);
        return value == null ? false : (boolean) value;
    }

    public static boolean encodeCanDeleteAtt(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return false;
        }
        Object value =  (boolean)taskVariables.get(WFConfig.CUSTOM_PROPERTY_CAN_DELETE_ATT);
        return value == null ? false : (boolean) value;
    }

    public static boolean encodeCanReadAtt(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return false;
        }
        Object value =  (boolean)taskVariables.get(WFConfig.CUSTOM_PROPERTY_CAN_READ_ATT);
        return value == null ? false : (boolean) value;
    }

    public static boolean encodeCanEditPage(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return false;
        }
        Object value = taskVariables.get(WFConfig.CUSTOM_PROPERTY_CAN_EDIT_PAGE);
        return value == null ? false : (boolean) value;
    }

    public static boolean codeCanUploadAtt(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean canUploadAtt = true;//默认值
        if(extensionElementMap == null){
            return canUploadAtt;
        }
        List<ExtensionElement> curRoleIdsEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CAN_UPLOAD_ATT);
        if(curRoleIdsEE == null){
            return canUploadAtt;
        }
        ExtensionElement extensionElement = curRoleIdsEE.get(0);
        if(extensionElement != null){
            canUploadAtt = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return canUploadAtt;
    }

    public static boolean codeCanUpdateOtherAtt(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean canUpdateOtherAtt = false;//默认值
        if(extensionElementMap == null){
            return canUpdateOtherAtt;
        }
        List<ExtensionElement> curRoleIdsEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CAN_UPDATE_OTHER_ATT);
        if(curRoleIdsEE == null){
            return canUpdateOtherAtt;
        }
        ExtensionElement extensionElement = curRoleIdsEE.get(0);
        if(extensionElement != null){
            canUpdateOtherAtt = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return canUpdateOtherAtt;
    }

    public static boolean codeCanDeleteOtherAtt(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean canDeleteOtherAtt = false;//默认值
        if(extensionElementMap == null){
            return canDeleteOtherAtt;
        }
        List<ExtensionElement> curRoleIdsEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CAN_DELETE_OTHER_ATT);
        if(curRoleIdsEE == null){
            return canDeleteOtherAtt;
        }
        ExtensionElement extensionElement = curRoleIdsEE.get(0);
        if(extensionElement != null){
            canDeleteOtherAtt = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return canDeleteOtherAtt;
    }

    public static boolean encodeUploadAtt(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return true;
        }
        Object value = taskVariables.get(WFConfig.CUSTOM_PROPERTY_CAN_UPLOAD_ATT);
        return value == null ? true : (boolean) value;
    }

    public static boolean encodeUpdateOtherAtt(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return false;
        }
        Object value = taskVariables.get(WFConfig.CUSTOM_PROPERTY_CAN_UPDATE_OTHER_ATT);
        return value == null ? false : (boolean) value;
    }

    public static boolean encodeDeleteOtherAtt(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return false;
        }
        Object value = taskVariables.get(WFConfig.CUSTOM_PROPERTY_CAN_DELETE_OTHER_ATT);
        return value == null ? false : (boolean) value;
    }

    public static List<String> codeOrganizationIds(Map<String, List<ExtensionElement>> extensionElementMap) {
        List<String> codeOrganizationIds = Lists.newArrayList();
        if(extensionElementMap == null){
            return codeOrganizationIds;
        }
        List<ExtensionElement> curRoleIdsEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CUR_ORGANIZATION_ID);
        if (curRoleIdsEE != null && curRoleIdsEE.size() > 0) {
            for (ExtensionElement extensionElement : curRoleIdsEE) {
                codeOrganizationIds.add(extensionElement.getElementText());
            }
        }
        return codeOrganizationIds;
    }

    public static Collection<String> encodeCurOrganizationIds(Map<String, Object> taskVariables){
        if(taskVariables == null){
            return Lists.newArrayList();
        }
        return (Collection<String>)taskVariables.get(WFConfig.CUSTOM_PROPERTY_CUR_ORGANIZATION_ID);
    }

    public static boolean encodeHaveOpinionText(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return true;
        }
        Object value = taskVariables.get(WFConfig.CUSTOM_PROPERTY_HAVE_OPINION_TEXT);
        return value == null ? true : (boolean) value;
    }

    public static boolean encodeHaveAddSign(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return true;
        }
        Object value = taskVariables.get(WFConfig.CUSTOM_PROPERTY_HAVE_ADD_SIGN);
        return value == null ? true : (boolean) value;
    }

    public static boolean encodeHaveDelegate(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return true;
        }
        Object value = taskVariables.get(WFConfig.CUSTOM_PROPERTY_HAVE_DELEGATE);
        return value == null ? true : (boolean) value;
    }

    public static boolean encodeCanAppEdit(HashMap<String, Object> taskVariables) {
        if(taskVariables == null){
            return false;
        }
        Object value = taskVariables.get(WFConfig.CUSTOM_PROPERTY_CAN_APP_EDIT);
        return value == null ? false : (boolean) value;
    }

    public static boolean codeHaveOpinionText(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean haveOpinionText = true;//默认值
        if(extensionElementMap == null){
            return haveOpinionText;
        }
        List<ExtensionElement> elementList = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_HAVE_OPINION_TEXT);
        if(elementList == null){
            return haveOpinionText;
        }
        ExtensionElement extensionElement = elementList.get(0);
        if(extensionElement != null){
            haveOpinionText = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return haveOpinionText;
    }

    public static boolean codeHaveAddSign(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean haveAddSign = true;//默认值
        if(extensionElementMap == null){
            return haveAddSign;
        }
        List<ExtensionElement> elementList = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_HAVE_ADD_SIGN);
        if(elementList == null){
            return haveAddSign;
        }
        ExtensionElement extensionElement = elementList.get(0);
        if(extensionElement != null){
            haveAddSign = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return haveAddSign;
    }

    public static boolean codeHaveDelegate(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean haveDelegate = true;//默认值
        if(extensionElementMap == null){
            return haveDelegate;
        }
        List<ExtensionElement> elementList = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_HAVE_DELEGATE);
        if(elementList == null){
            return haveDelegate;
        }
        ExtensionElement extensionElement = elementList.get(0);
        if(extensionElement != null){
            haveDelegate = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return haveDelegate;
    }

    public static boolean codeCanAppEdit(Map<String, List<ExtensionElement>> extensionElementMap) {
        boolean canAppEdit = false;//默认值
        if(extensionElementMap == null){
            return canAppEdit;
        }
        List<ExtensionElement> elementList = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_CAN_APP_EDIT);
        if(elementList == null){
            return canAppEdit;
        }
        ExtensionElement extensionElement = elementList.get(0);
        if(extensionElement != null){
            canAppEdit = WFConfig.TRUE_STRING.equals(extensionElement.getElementText());
        }
        return canAppEdit;
    }
}
