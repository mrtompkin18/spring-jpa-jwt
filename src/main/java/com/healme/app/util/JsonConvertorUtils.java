package com.healme.app.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Slf4j
@UtilityClass
public class JsonConvertorUtils {

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, Boolean.FALSE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
        return mapper;
    }

    public String toJson(Object obj) throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(obj);
    }

    public <T> T fromJsonToObject(String json, Class<T> objectClass) throws JsonProcessingException {
        T data = null;
        ObjectMapper mapper = getObjectMapper();
        if (json != null || objectClass != null) {
            data = mapper.readValue(json, objectClass);
        }
        return data;
    }

    public static <T> List<T> fromJsonToList(String json) throws JsonProcessingException {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        ObjectMapper mapper = getObjectMapper();
        List<T> ts = mapper.readValue(json, new TypeReference<>() {
        });
        return ts;
    }
}
