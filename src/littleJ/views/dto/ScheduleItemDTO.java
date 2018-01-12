package littleJ.views.dto;

import java.time.LocalTime;

import littleJ.LittleJUtils;
import littleJ.hardware.dto.ItemDTO;

public class ScheduleItemDTO {
	private int idScheduleItem;
	private int idSchedule;
	private int idItem;
	private int action;
	private LocalTime scheduleTime;
	private boolean isMonday;
	private boolean isTuesday;
	private boolean isWednesday;
	private boolean isThursday;
	private boolean isFriday;
	private boolean isSaturday;
	private boolean isSunday;
	
	private ScheduleDTO scheduleDTO;
	private ItemDTO itemDTO;
	
	public int getIdScheduleItem() {
		return idScheduleItem;
	}
	public void setIdScheduleItem(int idScheduleItem) {
		this.idScheduleItem = idScheduleItem;
	}
	public int getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(int idSchedule) {
		this.idSchedule = idSchedule;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public LocalTime getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(LocalTime scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	public boolean isMonday() {
		return isMonday;
	}
	public void setMonday(boolean isMonday) {
		this.isMonday = isMonday;
	}
	public boolean isTuesday() {
		return isTuesday;
	}
	public void setTuesday(boolean isTuesday) {
		this.isTuesday = isTuesday;
	}
	public boolean isWednesday() {
		return isWednesday;
	}
	public void setWednesday(boolean isWednesday) {
		this.isWednesday = isWednesday;
	}
	public boolean isThursday() {
		return isThursday;
	}
	public void setThursday(boolean isThursday) {
		this.isThursday = isThursday;
	}
	public boolean isFriday() {
		return isFriday;
	}
	public void setFriday(boolean isFriday) {
		this.isFriday = isFriday;
	}
	public boolean isSaturday() {
		return isSaturday;
	}
	public void setSaturday(boolean isSaturday) {
		this.isSaturday = isSaturday;
	}
	public boolean isSunday() {
		return isSunday;
	}
	public void setSunday(boolean isSunday) {
		this.isSunday = isSunday;
	}
	public ItemDTO getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
	public ScheduleDTO getScheduleDTO() {
		return scheduleDTO;
	}
	public void setScheduleDTO(ScheduleDTO scheduleDTO) {
		this.scheduleDTO = scheduleDTO;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}	
	public String getActionText(){
		return LittleJUtils.getActionText(action);
	}
	
	
}
