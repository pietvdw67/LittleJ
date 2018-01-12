package littleJ.processors.statuschange.dto;

import java.time.LocalDateTime;

import littleJ.hardware.dto.ItemDTO;
import littleJ.views.dto.ScheduleDTO;

public class StatusChangeDTO {
	private int idstatuschange;
	private int idItem;
	private LocalDateTime changeTimestamp;
	private int idschedule;
	private LocalDateTime processtime;
	private int action;
	private int idSourceItem;
	
	private ItemDTO itemDTO;
	private ScheduleDTO scheduleDTO;
	
	public int getIdstatuschange() {
		return idstatuschange;
	}
	public void setIdstatuschange(int idstatuschange) {
		this.idstatuschange = idstatuschange;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public LocalDateTime getChangeTimestamp() {
		return changeTimestamp;
	}
	public void setChangeTimestamp(LocalDateTime changeTimestamp) {
		this.changeTimestamp = changeTimestamp;
	}
	public int getIdschedule() {
		return idschedule;
	}
	public void setIdschedule(int idschedule) {
		this.idschedule = idschedule;
	}
	public LocalDateTime getProcesstime() {
		return processtime;
	}
	public void setProcesstime(LocalDateTime processtime) {
		this.processtime = processtime;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
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
	public int getIdSourceItem() {
		return idSourceItem;
	}
	public void setIdSourceItem(int idSourceItem) {
		this.idSourceItem = idSourceItem;
	}
	
	
	

}
