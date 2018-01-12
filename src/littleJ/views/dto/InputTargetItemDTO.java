package littleJ.views.dto;

import littleJ.LittleJUtils;
import littleJ.hardware.dto.ItemDTO;

public class InputTargetItemDTO {
	private int idInputTargetItem;
	private int idSourceItem;
	private int idTargetItem;
	private int action;
	private String delay;
	
	private ItemDTO sourceItem;
	private ItemDTO targetItem;
	public int getIdInputTargetItem() {
		return idInputTargetItem;
	}
	public void setIdInputTargetItem(int idInputTargetItem) {
		this.idInputTargetItem = idInputTargetItem;
	}
	public int getIdSourceItem() {
		return idSourceItem;
	}
	public void setIdSourceItem(int idSourceItem) {
		this.idSourceItem = idSourceItem;
	}
	public int getIdTargetItem() {
		return idTargetItem;
	}
	public void setIdTargetItem(int idTargetItem) {
		this.idTargetItem = idTargetItem;
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

	public ItemDTO getSourceItem() {
		return sourceItem;
	}
	public void setSourceItem(ItemDTO sourceItem) {
		this.sourceItem = sourceItem;
	}
	public ItemDTO getTargetItem() {
		return targetItem;
	}
	public void setTargetItem(ItemDTO targetItem) {
		this.targetItem = targetItem;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	
	
}
