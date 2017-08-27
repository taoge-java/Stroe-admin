package com.stroe.admin.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jfinal.log.Log;
import com.stroe.admin.spring.SpringBeanManger;

public class WebAppListener implements ServletContextListener{

	private static final Log LOG=Log.getLog(WebAppListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		LOG.info("spring-context start........");
		ApplicationContext app=new ClassPathXmlApplicationContext("spring-context.xml");
		SpringBeanManger.initContext(app);
		LOG.info("spring-context.xml加载完成.......");
	}

}
