package littleJ;


import littleJ.dto.SettingsDTO;
import littleJ.service.LittleJHandler;
import za.co.infinity.utils.properties.PropertiesFile;

public class Settings {
	private static SettingsDTO settingsDTO = null;
	
	public static void loadSettings(){		
		PropertiesFile propertiesFile = new PropertiesFile("littleJ.properties");
		propertiesFile.loadProperties();
		
		settingsDTO = new SettingsDTO();

		settingsDTO.setDbUrl(propertiesFile.getProperty("DB_URL"));
		settingsDTO.setDbUsername(propertiesFile.getProperty("DB_User"));
		settingsDTO.setDbPassword(propertiesFile.getProperty("DB_Password"));
		settingsDTO.setDeviceCode(propertiesFile.getProperty("DeviceCode"));
		
		String debugMode = propertiesFile.getProperty("DebugMode");
		if (debugMode.equalsIgnoreCase("true") || debugMode.equalsIgnoreCase("yes")){
			settingsDTO.setDebugMode(true);
		}
		
		String hasRasberryString = propertiesFile.getProperty("Has_Rasberry");
		if (hasRasberryString.equalsIgnoreCase("true") || hasRasberryString.equalsIgnoreCase("yes")){
			settingsDTO.setHasRasberry(true);
		}
		
		settingsDTO.setRaspberryMaxTemp(Integer.parseInt(propertiesFile.getProperty("Rasbperry_Max_Temp")));
		
		settingsDTO.setProcessorDelayPinStatus(Integer.parseInt(propertiesFile.getProperty("Processor_Delay_PinStatus")));
		settingsDTO.setProcessorDelaySchedular(Integer.parseInt(propertiesFile.getProperty("Processor_Delay_Schedular")));
		settingsDTO.setProcessorDelayStatusChange(Integer.parseInt(propertiesFile.getProperty("Processor_Delay_StatusChange")));
		settingsDTO.setProcessorDelayTemperature(Integer.parseInt(propertiesFile.getProperty("Processor_Delay_Temperature")));	
		
		settingsDTO.setWebConentLocation(propertiesFile.getProperty("WebContentLocation"));
		
		LittleJHandler.getInstance().setSettingsDTO(settingsDTO);
	}

	public static SettingsDTO getSettings(){
		return settingsDTO;
	}
}
