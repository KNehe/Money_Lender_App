package com.Dimes;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;



public abstract class JsonManager {

    Gson gson = new Gson(); //short cut to do all below

    protected String mapToJson(Object obj) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(obj);
    }


    protected  <T> T mapFromJson(String json, Class<T> clazz)
        throws JsonParseException, JsonMappingException, IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.readValue(json,clazz);
    }
}
