package littleJ.processors.temperatureMonitor.service;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.pi4j.system.SystemInfo;

import littleJ.database.DBConnection;
import littleJ.database.DBDevice;
import littleJ.hardware.dto.DeviceDTO;
import littleJ.service.LittleJHandler;

public class TemperatureMonitorHandler implements Runnable {
	final static Logger log = Logger.getLogger(TemperatureMonitorHandler.class);

	@Override
	public void run() {
		int temperature = 0;	
		log.info("Starting up process: TemperatureMonitorHandler");
		
		boolean doRun = true;
		while (doRun) {
			try {
				temperature = getTemperature();
				updateTemperature(temperature);

				TimeUnit.MILLISECONDS.sleep(LittleJHandler.getInstance().getSettingsDTO().getProcessorDelayTemperature());
			} catch (Exception e) {
				log.error("Error sleeping", e);
			}

			// Remove comment for testing to run only once
			// doRun = false;
		}
	}

	private void updateTemperature(int temperature) {
		try {
			DBDevice dbDevice = new DBDevice(DBConnection.getConnection());
			DeviceDTO deviceDTO = dbDevice
					.getDeviceByDescription(LittleJHandler.getInstance().getSettingsDTO().getDeviceCode());
			if (deviceDTO != null) {
				deviceDTO.setTemperature(temperature);
				dbDevice.updateItem(deviceDTO);
			}
		} catch (SQLException sqlE) {
			log.error(sqlE);
		}
	}

	private int getTemperature() {
		int temperature = 0;
		if (LittleJHandler.getInstance().getSettingsDTO().isDebugMode()) {
			return 23;
		}

		try {
			temperature = (int) SystemInfo.getCpuTemperature();
		} catch (Exception e) {
			log.error(e);
		}

		return temperature;
	}
}
