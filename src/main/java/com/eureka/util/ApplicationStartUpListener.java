package com.eureka.util;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartUpListener implements ApplicationListener<ApplicationPreparedEvent> {


    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        PropertyUtil.loadAllProperties();
        System.out.println("ApplicationStartUpListener EXEC");
    }
}