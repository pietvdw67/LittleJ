package littleJ.hardware.dto;

public class ItemTypeDTO {
	private int idItemType;
	private String itemTypeName;
	private String itemTypeImage;
	private String imagePathOn;
	private String imagePathOff;
	private boolean isOutput;
	private boolean isAudio;
	
	public int getIdItemType() {
		return idItemType;
	}
	public void setIdItemType(int idItemType) {
		this.idItemType = idItemType;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public String getItemTypeImage() {
		return itemTypeImage;
	}
	public void setItemTypeImage(String itemTypeImage) {
		this.itemTypeImage = itemTypeImage;
	}
	public boolean isOutput() {
		return isOutput;
	}
	public void setOutput(boolean isOutput) {
		this.isOutput = isOutput;
	}
	public String getIsOutputChecked() {
		if (isOutput){
			return "yes";
		} else {
			return "no";
		}
	}
	
	public String getIsAudioChecked() {
		if (isAudio){
			return "yes";
		} else {
			return "no";
		}
	}
	
	public boolean getIsOutput(){
		return isOutput;
	}
	public String getImagePathOn() {
		return imagePathOn;
	}
	public void setImagePathOn(String imagePathOn) {
		this.imagePathOn = imagePathOn;
	}
	public String getImagePathOff() {
		return imagePathOff;
	}
	public void setImagePathOff(String imagePathOff) {
		this.imagePathOff = imagePathOff;
	}
	public boolean getIsAudio() {
		return isAudio;
	}
	public void setIsAudio(boolean isAudio) {
		this.isAudio = isAudio;
	}
		
}
