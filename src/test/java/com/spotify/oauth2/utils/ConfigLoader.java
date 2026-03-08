package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {
    //singleton approach

    private final Properties properties;       // created this variable that represents object of Properties
    private static ConfigLoader configLoader;  //created this variable that represents object of this class

    //private, can not be accesiable from outside of this class, we shouldnt create a new instance of this class outside of this class
    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    //1- first private static ConfigLoader configLoader; is NULL
    //2- then we created onfigLoader object by using this method, and it is not NULL anymore
    //just loading this only once, not loading over and over again
    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId(){
        String prop = properties.getProperty("client_id");
        if(prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getClientSecret(){
        String prop = properties.getProperty("client_secret");
        if(prop != null) return prop;
        else throw new RuntimeException("property client_secret is not specified in the config.properties file");
    }

    public String getGrantType(){
        String prop = properties.getProperty("grant_type");
        if(prop != null) return prop;
        else throw new RuntimeException("property grant_type is not specified in the config.properties file");
    }

    public String getRefreshToken(){
        String prop = properties.getProperty("refresh_token");
        if(prop != null) return prop;
        else throw new RuntimeException("property refresh_token is not specified in the config.properties file");
    }

    public String getUser(){
        String prop = properties.getProperty("user_id");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }

}
