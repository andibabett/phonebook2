package org.fasttrackit.phonebook.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperConfiguration {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static{
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
