package littleJ.processors.raspberry.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import littleJ.database.DBConnection;
import littleJ.database.DBDevice;
import littleJ.database.DBRaspberryStatus;
import littleJ.hardware.dto.DeviceDTO;
import littleJ.raspberry.dto.RaspberryStatusDTO;
import littleJ.raspberry.service.GPIOHandler;
import littleJ.service.LittleJHandler;

public class PinStatusHandler implements Runnable {
	final static Logger log = Logger.getLogger(PinStatusHandler.class);
	
	@Override
	public void run() {
		if (LittleJHandler.getInstance().getSettingsDTO().isDebugMode()){
			log.info("Not running PinStatusHandler, due to debug mode");
			return;
		} 
		log.info("Starting up process: PinStatusHandler");
		boolean doRun = true;
		while (doRun) {
			try {
				updatePinStatusses();
				TimeUnit.MILLISECONDS.sleep(LittleJHandler.getInstance().getSettingsDTO().getProcessorDelayPinStatus());
			} catch (Exception e) {
				log.error("Error sleeping", e);
			}

			// Remove comment for testing to run only once
			// doRun = false;
		}
	}
	
	public void updatePinStatusses() throws Exception  {
		boolean doUpadate = true;
		ArrayList <DeviceDTO> deviceDTOlist = new DBDevice(DBConnection.getConnection()).getRaspberryDevices();
		
		GPIOHandler gpioHandler = new GPIOHandler();
		for (DeviceDTO deviceDTO : deviceDTOlist){
			RaspberryStatusDTO raspberryStatusDTO = new DBRaspberryStatus(DBConnection.getConnection()).getByDevideId(deviceDTO.getIdDevice());
			if (raspberryStatusDTO == null){
				raspberryStatusDTO = new RaspberryStatusDTO();
			}			
			raspberryStatusDTO.setIdDevice(deviceDTO.getIdDevice());
			raspberryStatusDTO.setPin00(gpioHandler.getPinStatus(0));
			raspberryStatusDTO.setPin01(gpioHandler.getPinStatus(1));			
			raspberryStatusDTO.setPin02(gpioHandler.getPinStatus(2));
			raspberryStatusDTO.setPin03(gpioHandler.getPinStatus(3));
			raspberryStatusDTO.setPin04(gpioHandler.getPinStatus(4));
			raspberryStatusDTO.setPin05(gpioHandler.getPinStatus(5));
			raspberryStatusDTO.setPin06(gpioHandler.getPinStatus(6));
			raspberryStatusDTO.setPin07(gpioHandler.getPinStatus(7));
			raspberryStatusDTO.setPin08(gpioHandler.getPinStatus(8));
			raspberryStatusDTO.setPin09(gpioHandler.getPinStatus(9));
			raspberryStatusDTO.setPin10(gpioHandler.getPinStatus(10));
			raspberryStatusDTO.setPin11(gpioHandler.getPinStatus(11));
			raspberryStatusDTO.setPin12(gpioHandler.getPinStatus(12));
			raspberryStatusDTO.setPin13(gpioHandler.getPinStatus(13));
			raspberryStatusDTO.setPin14(gpioHandler.getPinStatus(14));
			raspberryStatusDTO.setPin15(gpioHandler.getPinStatus(15));
			raspberryStatusDTO.setPin15(gpioHandler.getPinStatus(16));
			
			if (doUpadate){			
				new DBRaspberryStatus(DBConnection.getConnection()).updateItem(raspberryStatusDTO);
			} else {
				new DBRaspberryStatus(DBConnection.getConnection()).addItem(raspberryStatusDTO); 
			}
		}
		 
	}

}
