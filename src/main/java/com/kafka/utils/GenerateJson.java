package com.kafka.utils;

public class GenerateJson {

    public static String generateUser(int i){


        String userJson = "{\"userId\":\""+i+"\","+
                "\"UserName\":\""+"user-name-"+i+"\"}";
        return userJson;
    }
}
