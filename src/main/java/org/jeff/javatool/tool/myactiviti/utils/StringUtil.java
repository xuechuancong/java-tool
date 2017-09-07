package org.jeff.javatool.tool.myactiviti.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by weijianfu on 2017/4/8.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {
        return value == null || value.length() <= 0;
    }

    /**
     * 拆分字符串，获取字符串集合
     *
     * @param value     即将分割字符串，例如 11,11,22
     * @param splitSign 分隔符，例如 ,
     * @return
     */
    public static List<String> split(String value, String splitSign) {
        if (value == null || value.length() <= 0 || splitSign == null || splitSign.length() <= 0) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(value.split(splitSign));
    }

    /**
     * 拼接集合中元素，获取字符串
     *
     * @param valueList 即将拼接结合
     * @param splitSign 分隔符，例如 ,
     * @return
     */
    public static String concat(Collection<String> valueList, String splitSign) {
        if (valueList == null || valueList.size() <= 0 || splitSign == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String value : valueList) {
            sb.append(value).append(splitSign);
        }
        return sb.substring(0, sb.length() - splitSign.length());
    }

    /**
     * list转出成string,用逗号分隔
     *
     * @param list
     * @return
     */
    public static String list2String(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                str += list.get(i);
            } else {
                str += list.get(i) + ",";
            }
        }
        return str;
    }

    /**
     * Collection转出成string,用逗号分隔
     *
     * @param co
     * @return
     */
    public static String collection2String(Collection<String> co) {

        return list2String(Collection2List(co));
    }

    /**
     * Collection转出成List,
     *
     * @param <T>
     * @param co
     * @return
     */
    public static <T> List<T> Collection2List(Collection<T> co) {
        List<T> list = new ArrayList<T>();
        if (CollectionUtils.isEmpty(co)) {
            return list;
        }
        for (T t : co) {
            list.add(t);
        }
        return list;
    }

    /**
     * list转出成string,用逗号分隔
     *
     * @param str
     * @return
     */
    public static List<String> String2List(String str) {
        List<String> list = new ArrayList<String>();
        if (StringUtil.isBlank(str)) {
            return list;
        }

        for (String strs : str.split(",")) {
            list.add(strs);
        }
        return list;
    }
}
