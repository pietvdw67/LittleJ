package littleJ;

import littleJ.database.DBConnection;
import littleJ.processors.audio.service.AudioPlayerHandler;

public class Preloader {
	
	public void preload(){
		Settings.loadSettings();
		
		loadDatabase();
		
		// initializes audio handler
		AudioPlayerHandler.getInstance();
	}
	
	private void loadDatabase() {
		DBConnection.setConnection(Settings.getSettings().getDbUrl(),Settings.getSettings().getDbUsername(),Settings.getSettings().getDbPassword());
	}

}
