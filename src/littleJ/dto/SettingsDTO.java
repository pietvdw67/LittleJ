package littleJ.dto;

public class SettingsDTO {
	private String dbUrl;
	private String dbUsername;
	private String dbPassword;
	private String deviceCode;
	private boolean debugMode;
	private boolean hasRasberry;
	private int pageRefresh = 500;
	private int raspberryMaxTemp;
	
	private int processorDelaySchedular;
	private int processorDelayPinStatus;
	private int processorDelayStatusChange;
	private int processorDelayTemperature;
	
	private String webConentLocation;	
	
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public boolean isDebugMode() {
		return debugMode;
	}
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	public int getPageRefresh() {
		return pageRefresh;
	}
	public void setPageRefresh(int pageRefresh) {
		this.pageRefresh = pageRefresh;
	}
	public boolean isHasRasberry() {
		return hasRasberry;
	}
	public void setHasRasberry(boolean hasRasberry) {
		this.hasRasberry = hasRasberry;
	}
	public int getRaspberryMaxTemp() {
		return raspberryMaxTemp;
	}
	public void setRaspberryMaxTemp(int raspberryMaxTemp) {
		this.raspberryMaxTemp = raspberryMaxTemp;
	}
	public int getProcessorDelaySchedular() {
		return processorDelaySchedular;
	}
	public void setProcessorDelaySchedular(int processorDelaySchedular) {
		this.processorDelaySchedular = processorDelaySchedular;
	}
	public int getProcessorDelayPinStatus() {
		return processorDelayPinStatus;
	}
	public void setProcessorDelayPinStatus(int processorDelayPinStatus) {
		this.processorDelayPinStatus = processorDelayPinStatus;
	}
	public int getProcessorDelayStatusChange() {
		return processorDelayStatusChange;
	}
	public void setProcessorDelayStatusChange(int processorDelayStatusChange) {
		this.processorDelayStatusChange = processorDelayStatusChange;
	}
	public int getProcessorDelayTemperature() {
		return processorDelayTemperature;
	}
	public void setProcessorDelayTemperature(int processorDelayTemperature) {
		this.processorDelayTemperature = processorDelayTemperature;
	}
	public String getWebConentLocation() {
		return webConentLocation;
	}
	public void setWebConentLocation(String webConentLocation) {
		this.webConentLocation = webConentLocation;
	}
	
	
}
