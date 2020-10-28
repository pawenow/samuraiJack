package com.fairytales.samuraiJack2020.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class MemoryOfGameHandler {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static MemoryOfGame get(){
        try {
            return objectMapper.readValue("resources/static/memoryOfGame.json",MemoryOfGame.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOGGER.fine("Can't get object");

        }
        return null;
    }

    public static void save(MemoryOfGame mem){
        try {
            objectMapper.writeValue(new File("resources/static/memoryOfGame.json"),mem);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.fine("Can't save memory");

        }
    }
}
