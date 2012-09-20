/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polidemo.helpers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author x
 */
@WebListener()
public class PersistanceAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        PersistenceManager.getInstance().closeEntityManagerFactory();
    
    }
}
