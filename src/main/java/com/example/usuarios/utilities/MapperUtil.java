package com.example.usuarios.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {
    private MapperUtil(){}

    public static final ObjectMapper mapper = new ObjectMapper();

    public static String serialize (Object data) throws JsonProcessingException {
        return mapper.writeValueAsString(data);
    }

    public static <T> T deserialize(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, clazz);
    }
}
