package com.vstore.framework.base.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;



public class JsonUtils {
    private static final Logger logger= LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(dateFormat);
    }

    public static <T> String convert2String(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("convert object to json failed");
        }
    }

    public static <T> T convert2bean(String str, Class<T> clazz) {
        try {
            return mapper.readValue(str, clazz);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("convert json to object failed");
        }
    }

    public static <T> T convert2bean(String str, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(str, typeReference);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("convert json to object failed");
        }
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateToken() {
        return uuid();
    }











}
