package com.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.apl.sipg.form.Form;
import com.apl.util.Logger;

/**
 * 启动timer
 * @author Administrator
 *
 */
public class FileListener implements ServletContextListener  {
    private Timer timer;
    Logger logger = Logger.getLogger(FileListener.class);
    
	public void contextDestroyed(ServletContextEvent event) {
		 timer.cancel();  
		 logger.info("定时器销毁");  
	}

	
	public void contextInitialized(ServletContextEvent event) {
		Form form = new Form();
		timer = new Timer(true);  
		logger.info("定时器已启动");  
        // 调用Timer，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时，24*60*60*1000表示一天。  
        int time = form.getTime();
        timer.schedule(new TimerManager(), 0, time * 1000); //每2分钟执行一次Timer类  
        logger.info("已经添加任务");  
	}	
}
