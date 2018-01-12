package littleJ.views.dto;

public class SceneDTO {
	private int idScene;
	private String description;
	private String imagePath;
	private boolean isFavourite;
	
	public int getIdScene() {
		return idScene;
	}
	public void setIdScene(int idScene) {
		this.idScene = idScene;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public boolean isFavourite() {
		return isFavourite;
	}
	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
}
