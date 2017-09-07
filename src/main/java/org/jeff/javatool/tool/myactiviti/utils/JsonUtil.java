package org.jeff.javatool.tool.myactiviti.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

import static com.fasterxml.jackson.core.JsonParser.Feature.*;

/**
 * Created by weijianfu on 2016/11/29.
 */
public class JsonUtil {
	private final static Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    public static <T> T deserialize(String jsonStr, Class<T> cla) throws Exception {
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return cla.newInstance();
            }
            return LazyInit.objMapper.readValue(jsonStr, cla);
        } catch (Exception e) {
			LOG.error("jsonStr is :{}", jsonStr, e);
            throw new Exception("json反序列化失败，Json内容为：" + jsonStr);
        }
    }

    public static <T> T deserialize(String jsonStr, TypeReference<T> valueTypeRef) throws Exception {
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return null;
            }
            return LazyInit.objMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
			LOG.error("jsonStr is :{}", jsonStr, e);
            throw new Exception("json反序列化失败，Json内容为：" + jsonStr);
        }
    }

    public static <T> T deserializeUnchecked(String jsonStr, Class<T> cla) throws Exception {
        boolean savedFailOnUnknownProperties = LazyInit.objMapper
                .isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T result = deserialize(jsonStr, cla);
        LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, savedFailOnUnknownProperties);

        return result;
    }

    public static <T> T deserializeUnchecked(String jsonStr, TypeReference<T> valueTypeRef) throws Exception {
        boolean savedFailOnUnknownProperties = LazyInit.objMapper
                .isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T result = deserialize(jsonStr, valueTypeRef);
        LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, savedFailOnUnknownProperties);

        return result;
    }

    public static <T> T deserializeFile(String file, Class<T> cla) {
        return deserializeByInputStream(JsonUtil.class.getResourceAsStream(file), cla);
    }

    public static <T> T deserializeFile(String file, TypeReference<T> valueTypeRef) {
        try {
            return LazyInit.objMapper.readValue(JsonUtil.class.getResourceAsStream(file), valueTypeRef);
        } catch (Exception e) {
			LOG.error("", e);
            return null;
        }
    }

    public static <T> T deserializeByInputStream(InputStream io, Class<T> cla) {
        try {
            return LazyInit.objMapper.readValue(io, cla);
        } catch (Exception e) {
			LOG.error("", e);
            return null;
        }
    }

    public static String serialize(Object obj) {
        try {
            return LazyInit.objMapper.writeValueAsString(obj);
        } catch (Exception e) {
			LOG.error("", e);
            return null;
        }
    }

    private static final class LazyInit {
        private final static ObjectMapper objMapper = new ObjectMapper();

        static {
            objMapper.configure(ALLOW_COMMENTS, true);
            objMapper.configure(ALLOW_UNQUOTED_FIELD_NAMES, true);
            objMapper.configure(ALLOW_SINGLE_QUOTES, true);
            objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        }
    }
}
