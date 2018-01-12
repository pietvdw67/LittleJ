package littleJ.service;

import java.time.LocalDate;
import java.util.Map;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

import littleJ.dto.SettingsDTO;

public class LittleJHandler {
	private SettingsDTO settingsDTO;
	private static LittleJHandler littleJHandler = null;
	private LittleJHandler() {}
	private  Map<Integer, GpioPinDigitalOutput> provisioinedPins = null;
	private LocalDate resetScheduleDate = null;
	
	public static LittleJHandler getInstance(){
		if (littleJHandler == null){
			littleJHandler = new LittleJHandler();
		}
		
		return littleJHandler;
	}

	public SettingsDTO getSettingsDTO() {
		return settingsDTO;
	}

	public void setSettingsDTO(SettingsDTO settingsDTO) {
		this.settingsDTO = settingsDTO;
	}

	public Map<Integer, GpioPinDigitalOutput> getProvisioinedPins() {
		return provisioinedPins;
	}

	public void setProvisioinedPins(Map<Integer, GpioPinDigitalOutput> provisioinedPins) {
		this.provisioinedPins = provisioinedPins;
	}

	public LocalDate getResetScheduleDate() {
		return resetScheduleDate;
	}

	public void setResetScheduleDate(LocalDate resetScheduleDate) {
		this.resetScheduleDate = resetScheduleDate;
	}
	
}
