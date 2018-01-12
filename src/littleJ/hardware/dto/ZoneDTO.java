package littleJ.hardware.dto;

public class ZoneDTO {
	private int idZone;
	private String zoneName;
	private String zoneImage="";
	private String zoneImagePath = "";
	private int temperature;
	
	public int getIdZone() {
		return idZone;
	}
	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getZoneImage() {
		return zoneImage;
	}
	public void setZoneImage(String zoneImage) {
		this.zoneImage = zoneImage;
	}
	public String getZoneImagePath() {
		return zoneImagePath;
	}
	public void setZoneImagePath(String zoneImagePath) {
		this.zoneImagePath = zoneImagePath;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}	
	
}
