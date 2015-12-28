package com.listener;

import java.util.TimerTask;

import com.apl.sipg.service.ScanService;

/**
 * timer事件调用类
 * @author Administrator
 *
 */
public class TimerManager  extends TimerTask {
	ScanService scan = new ScanService();
	@Override
	public void run() {
		try {
			scan.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
