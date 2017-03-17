package com.eureka.util;

import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Created by andy.wu
 */


public class PropertyUtil {
    private static Map<String, String> propertiesMap = new HashMap<String,String>();
    // Default as in PropertyPlaceholderConfigurer


    public static void processProperties( Properties props) throws BeansException {

        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();

            try {
                //PropertiesLoaderUtils��Ĭ�ϱ�����ISO-8859-1,������ת��һ��
                propertiesMap.put(keyStr, new String(props.getProperty(keyStr).getBytes("ISO-8859-1"),"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }catch (java.lang.Exception e){
                e.printStackTrace();
            };
        }
        System.out.println(propertiesMap);
    }
    public static void loadAllProperties(){
        try {

            Properties properties = PropertiesLoaderUtils.loadAllProperties("String.properties");
            processProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name).toString();
    }
}