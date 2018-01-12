package littleJ.views.dto;

public class ScheduleDTO {
	int idSchedule;
	String description;
	boolean isActive;
	boolean isPopulated;
	private String imagePathOn;
	private String imagePathOff;
	
	public int getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(int idSchedule) {
		this.idSchedule = idSchedule;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean getActive(){
		return isActive;
	}
	public boolean isPopulated() {
		return isPopulated;
	}
	public void setPopulated(boolean isPopulated) {
		this.isPopulated = isPopulated;
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
}
