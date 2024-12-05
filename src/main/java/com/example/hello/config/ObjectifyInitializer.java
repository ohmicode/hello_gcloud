package com.example.hello.config;

import com.example.hello.model.KnownUser;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ObjectifyInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ObjectifyService.init();
        ObjectifyService.register(KnownUser.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // do nothing
    }
}
