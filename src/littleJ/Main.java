package littleJ;

import org.apache.log4j.Logger;

import littleJ.processors.raspberry.service.PinStatusHandler;
import littleJ.processors.schedule.service.ScheduleHandler;
import littleJ.processors.statuschange.service.StatusChangeHandler;
import littleJ.processors.temperatureMonitor.service.TemperatureMonitorHandler;
import littleJ.service.LittleJHandler;

public class Main {
	final static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		new Main().startup();
	}
	
	public void startup(){
		Preloader preloader = new Preloader();
		preloader.preload();
		
		log.info("");
		log.info("************************************************************");
		log.info("*** Starting up device : " + LittleJHandler.getInstance().getSettingsDTO().getDeviceCode());
		log.info("*** DebugMode:" + LittleJHandler.getInstance().getSettingsDTO().isDebugMode());
		log.info("************************************************************");
		
		StatusChangeHandler statusChangeHandler = new StatusChangeHandler();
		Thread statusChangeHandlerThread = new Thread(statusChangeHandler);
		statusChangeHandlerThread.start();
		
		ScheduleHandler scheduleHandler = new ScheduleHandler();
		Thread scheduleHandlerThread = new Thread(scheduleHandler);
		scheduleHandlerThread.start();
		
		if (LittleJHandler.getInstance().getSettingsDTO().isHasRasberry()){
			PinStatusHandler pinStatusHandler = new PinStatusHandler();
			Thread pinStatusHandlerThread = new Thread(pinStatusHandler);
			pinStatusHandlerThread.start();
			
			TemperatureMonitorHandler temperatureMonitorHandler = new TemperatureMonitorHandler();
			Thread temperatureMonitorHandlerThread = new Thread(temperatureMonitorHandler);
			temperatureMonitorHandlerThread.start();
		}
		
		Test t = new Test();
		t.test();
		
	}


}
