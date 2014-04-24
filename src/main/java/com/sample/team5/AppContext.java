package com.sample.team5;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContext implements ApplicationContextAware {
	 
    private static ApplicationContext applicationContext;
 
    public static ApplicationContext getApplicationContext(){
    	return applicationContext;
    }
    
    @Override
    public void setApplicationContext(final ApplicationContext appContext) throws BeansException {	        
        applicationContext = appContext;
    }
 
}
