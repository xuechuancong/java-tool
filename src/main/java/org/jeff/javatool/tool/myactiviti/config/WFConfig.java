package org.jeff.javatool.tool.myactiviti.config;

/**
 * 工作流常量
 * Created by weijianfu on 2016/11/29.
 */
public class WFConfig {

    /** 流程变量集-任务操作类型的key **/
    public static final String V_TASK_OPERATION_KEY = "pass";

    /** 流程变量集-历史任务Key、操作值，用于显示动态流程图 **/
    public static final String V_HIS_TASK_OPERATION = "his_task_operation";

    /** 任务定义ID的统一前缀 */
    public static final String TASK_DEFINITION_KEY_PREFIX = "taskDefinitionKey";

    /** 自定义流程定义KEY的统一开头 */
    public static final String CUSTOM_PROC_HEAD_OF_KEY = "CUSTOM_PROC_";

    /** 任务第一步的执行UserId，用于任务回退操作，存储于{@link org.jeff.javatool.tool.myactiviti.entity.TNextTaskInfo#roleIdList} */
    public static final String USER_ID_FOR_FIRST_TASK = "USER_ID_FOR_FIRST_TASK";

    /** 流程变量集-变量KEY、VALUE都等于该值时，说明该流程实例为异常结束 **/
    public static final String V_ABNORMAL_PROC = "ABNORMAL_PROC";




    /** true的string值 */
    public static final String TRUE_STRING = "TRUE";
    /** false的string值 */
    public static final String FALSE_STRING = "FALSE";



    /** 会签任务定义中使用，会签任务执行者集合 */
    public static final String V_COUNTER_SIGN_ACTIVITI_COLLECTION = "assigneeList";
    /** 会签任务定义中使用，存在表act_ru_variable中的key */
    public static final String V_COUNTER_SIGN_ELEMENT_VARIABLE = "assignee";



    /** activiti在多实例任务中固定生成的变量key：实例总数 */
    public static final String V_NR_OF_INSTANCES = "nrOfInstances";
    /** activiti在多实例任务中固定生成的变量key：当前还没有完成的实例 */
    public static final String V_NR_OF_ACTIVE_INSTANCES = "nrOfActiveInstances";
    /** activiti在多实例任务中固定生成的变量key：已经完成的实例个数 */
    public static final String V_NR_OF_COMPLETED_INSTANCES = "nrOfCompletedInstances";
    /** 在多实例任务中自定义生成的变量key：已经同意的个数 */
    public static final String V_SELF_AGREE_INSTANCES = "selfOfAgreeInstances";
    /** 在多实例任务中自定义生成的变量key：已经完成的个数 */
    public static final String V_SELF_COMPLETED_INSTANCES = "selfOfCompletedInstances";



    /**BPMN节点自定义属性中包含的KEY,当前任务的执行角色Id **/
    public static final String CUSTOM_PROPERTY_CUR_ROLE_ID = "CUSTOM_PROPERTY_CUR_ROLE_ID";
    /**BPMN节点自定义属性中包含的KEY,下一个任务的信息 **/
    public static final String CUSTOM_PROPERTY_NEXT_TASK_INFO = "CUSTOM_PROPERTY_NEXT_TASK_INFO";
    /**BPMN节点自定义属性中包含的KEY,是否能编辑本任务表单 **/
    public static final String CUSTOM_PROPERTY_CAN_EDIT_PAGE = "CUSTOM_PROPERTY_CAN_EDIT_PAGE";

    /**BPMN节点自定义属性中包含的KEY,是否能修改自己附件 **/
    public static final String CUSTOM_PROPERTY_CAN_UPDATE_ATT = "CUSTOM_PROPERTY_CAN_UPDATE_ATT";
    /**BPMN节点自定义属性中包含的KEY,是否能下载附件 **/
    public static final String CUSTOM_PROPERTY_CAN_DOWNLOAD_ATT = "CUSTOM_PROPERTY_CAN_DOWNLOAD_ATT";
    /**BPMN节点自定义属性中包含的KEY,是否能删除自己附件 **/
    public static final String CUSTOM_PROPERTY_CAN_DELETE_ATT = "CUSTOM_PROPERTY_CAN_DELETE_ATT";
    /**BPMN节点自定义属性中包含的KEY,是否能显示附件 **/
    public static final String CUSTOM_PROPERTY_CAN_READ_ATT = "CUSTOM_PROPERTY_CAN_READ_ATT";
    /**BPMN节点自定义属性中包含的KEY,当前任务的执行组织Id **/
    public static final String CUSTOM_PROPERTY_CUR_ORGANIZATION_ID = "CUSTOM_PROPERTY_CUR_ORGANIZATION_ID";
    /**BPMN节点自定义属性中包含的KEY,是否能上传附件,默认：True */
    public static final String CUSTOM_PROPERTY_CAN_UPLOAD_ATT = "CUSTOM_PROPERTY_CAN_UPLOAD_ATT";
    /**BPMN节点自定义属性中包含的KEY,是否能修改他人附件,默认：False */
    public static final String CUSTOM_PROPERTY_CAN_UPDATE_OTHER_ATT = "CUSTOM_PROPERTY_CAN_UPDATE_OTHER_ATT";
    /**BPMN节点自定义属性中包含的KEY,是否能删除他人附件,默认：False */
    public static final String CUSTOM_PROPERTY_CAN_DELETE_OTHER_ATT = "CUSTOM_PROPERTY_CAN_DELETE_OTHER_ATT";

    /**BPMN节点自定义属性中包含的KEY,是否包含处理意见文本域,默认：True */
    public static final String CUSTOM_PROPERTY_HAVE_OPINION_TEXT = "CUSTOM_PROPERTY_HAVE_OPINION_TEXT";
    /**BPMN节点自定义属性中包含的KEY,是否包含加签处理,默认：True */
    public static final String CUSTOM_PROPERTY_HAVE_ADD_SIGN = "CUSTOM_PROPERTY_HAVE_ADD_SIGN";
    /**BPMN节点自定义属性中包含的KEY,是否包含代理处理,默认：True */
    public static final String CUSTOM_PROPERTY_HAVE_DELEGATE = "CUSTOM_PROPERTY_HAVE_DELEGATE";
    /**BPMN节点自定义属性中包含的KEY,是否包含代理处理,默认：False */
    public static final String CUSTOM_PROPERTY_CAN_APP_EDIT = "CUSTOM_PROPERTY_CAN_APP_EDIT";




    /** 流程变量KEY，业务方存储，任务标题 */
    public static final String V_WAITTING_DEAL_NAME = "WAITTING_DEAL_NAME";
    /** 流程变量KEY，业务方存储，申请人ID */
    public static final String V_PROCESS_START_STAFF_ID = "PROCESS_START_STAFF_ID";
    /** 流程变量KEY，业务方存储，申请人名称 */
    public static final String V_PROCESS_START_STAFF = "PROCESS_START_STAFF";
    /** 流程变量KEY，业务方存储，申请人部门ID*/
    public static final String V_ORG_ID = "ORG_ID";
    /** 流程变量KEY，业务方存储，申请人部门名称 */
    public static final String V_ORG_NAME = "ORG_NAME";
    /** 流程变量KEY，流程引擎存储，任务开启时间（毫秒数,long） */
    public static final String V_START_TIME = "START_TIME";
    /** 流程变量KEY，流程引擎存储，已完成任务的执行者userId集合，例如123,345,312 */
    public static final String V_FINISHED_TASK_USER_LIST = "FINISHED_TASK_USER_LIST";
    /** 流程变量KEY，流程引擎存储，申请时间 */
    public static final String V_REQUEST_TIME = "REQUEST_TIME";
    /** 流程变量KEY，流程引擎存储，第一步环节定义id */
    public static final String V_FIRST_TASK_KEY = "FIRST_TASK_KEY";

    /** 标记任务已超时 */
    public static final String V_MARK_TIMEOUT = "V_MARK_TIMEOUT";


    /** 开始任务定义key */
    public static final String START_EVE_DEF_KEY = "startEvent";
    /** 结束任务定义key */
    public static final String END_EVE_DEF_KEY = "endEvent";



}
