package littleJ.hardware.dto;

public class DeviceDTO {
	private int idDevice;
	private String description;
	private int temperature;
	private String ip;
	private int idDeviceType;
	 
	private DeviceTypeDTO deviceTypeDTO;	
	
	public int getIdDevice() {
		return idDevice;
	}
	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getIdDeviceType() {
		return idDeviceType;
	}
	public void setIdDeviceType(int idDeviceType) {
		this.idDeviceType = idDeviceType;
	}
	public DeviceTypeDTO getDeviceTypeDTO() {
		return deviceTypeDTO;
	}
	public void setDeviceTypeDTO(DeviceTypeDTO deviceTypeDTO) {
		this.deviceTypeDTO = deviceTypeDTO;
	}	
}
