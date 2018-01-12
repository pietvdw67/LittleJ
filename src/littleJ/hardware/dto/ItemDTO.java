package littleJ.hardware.dto;

public class ItemDTO {
	private int idItem;
	private String description;
	private int idItemType;
	private int idDevice;
	private int idPi4jPin;
	private int idZone;
	private boolean isAtive;
	private boolean isFavourite;
	private String audioFile;
	
	// Status determined and set in DBItem
	private int status;
	
	private ItemTypeDTO itemTypeDTO;
	private DeviceDTO deviceDTO;
	private Pi4jPinDTO pi4jPinDTO;
	private ZoneDTO zoneDTO;
	
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIdItemType() {
		return idItemType;
	}
	public void setIdItemType(int idItemType) {
		this.idItemType = idItemType;
	}
	public int getIdDevice() {
		return idDevice;
	}
	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}
	public int getIdPi4jPin() {
		return idPi4jPin;
	}
	public void setIdPi4jPin(int idPi4jPin) {
		this.idPi4jPin = idPi4jPin;
	}
	public int getIdZone() {
		return idZone;
	}
	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}
	public boolean isAtive() {
		return isAtive;
	}
	public void setAtive(boolean isAtive) {
		this.isAtive = isAtive;
	}
	public boolean isFavourite() {
		return isFavourite;
	}
	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	public ItemTypeDTO getItemTypeDTO() {
		return itemTypeDTO;
	}
	public void setItemTypeDTO(ItemTypeDTO itemTypeDTO) {
		this.itemTypeDTO = itemTypeDTO;
	}
	public DeviceDTO getDeviceDTO() {
		return deviceDTO;
	}
	public void setDeviceDTO(DeviceDTO deviceDTO) {
		this.deviceDTO = deviceDTO;
	}
	public Pi4jPinDTO getPi4jPinDTO() {
		return pi4jPinDTO;
	}
	public void setPi4jPinDTO(Pi4jPinDTO pi4jPinDTO) {
		this.pi4jPinDTO = pi4jPinDTO;
	}
	public ZoneDTO getZoneDTO() {
		return zoneDTO;
	}
	public void setZoneDTO(ZoneDTO zoneDTO) {
		this.zoneDTO = zoneDTO;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAudioFile() {
		return audioFile;
	}
	public void setAudioFile(String audioFile) {
		this.audioFile = audioFile;
	}
	
	
}
