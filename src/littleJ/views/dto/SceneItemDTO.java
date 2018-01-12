package littleJ.views.dto;

import littleJ.LittleJUtils;
import littleJ.hardware.dto.ItemDTO;

public class SceneItemDTO {
	private int idSceneItem;
	private String imagepath="";
	private int idScene;
	private int idItem;
	private String delay;
	private int action;
	
	private SceneDTO sceneDTO;
	private ItemDTO itemDTO;
	
	public int getIdSceneItem() {
		return idSceneItem;
	}
	public void setIdSceneItem(int idSceneItem) {
		this.idSceneItem = idSceneItem;
	}
	public int getIdScene() {
		return idScene;
	}
	public void setIdScene(int idScene) {
		this.idScene = idScene;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public SceneDTO getSceneDTO() {
		return sceneDTO;
	}
	public void setSceneDTO(SceneDTO sceneDTO) {
		this.sceneDTO = sceneDTO;
	}
	public ItemDTO getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
	
	public String getActionText(){
		return LittleJUtils.getActionText(action);
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}	
}
