package littleJ.processors.audio.service;

import java.io.FileInputStream;

import org.apache.log4j.Logger;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import littleJ.service.LittleJHandler;

public class AudioPlayerHandler {
	final static Logger log = Logger.getLogger(AudioPlayerHandler.class);
	private static AudioPlayerHandler audioPlayerHandler = null; 
	private AdvancedPlayer player;
	private PlayBack playBack = null;
	private AudioPlayerHandler() {
		// singleton
	}
	
	public static AudioPlayerHandler getInstance(){
		if (audioPlayerHandler == null ){
			audioPlayerHandler = new AudioPlayerHandler();
			audioPlayerHandler.initialize();			
		}
		return audioPlayerHandler;
	}
	
	private void initialize(){
		
	}
	
	public void playRepeat(String filename){
		String fullFilename = LittleJHandler.getInstance().getSettingsDTO().getWebConentLocation() + "audiofiles/" + filename;
		log.info("Playing audio repeat mode: " + filename);
		
		try {		
			if (player != null){
				player.close();
			}
			
			FileInputStream fis = new FileInputStream(fullFilename);
			player = new AdvancedPlayer(fis);
			
			player.setPlayBackListener(new PlaybackListener() {
			    @Override
			    public void playbackFinished(PlaybackEvent event) {
			    	log.info("Repeating audio");
			    	AudioPlayerHandler.getInstance().playRepeat(filename);
			    }
			});			
			
			playBack = new PlayBack();
			playBack.setPlayer(player);
			new Thread(playBack).start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(String filename){
		String fullFilename = LittleJHandler.getInstance().getSettingsDTO().getWebConentLocation() + "audiofiles/" + filename;
		log.info("Playing audio: " + filename);
				
		try {		
			if (player != null){
				player.close();
			}
			
			FileInputStream fis = new FileInputStream(fullFilename);
			player = new AdvancedPlayer(fis);
			playBack = new PlayBack();
			playBack.setPlayer(player);
			new Thread(playBack).start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void stop(){
		log.info("Stopping audio, will repeat if set");
		playBack.stopPlay();
	}
	
	public void stopComplete(){
		log.info("Stopping audio compeletely");
		playBack.stopComplete();
		
	}

}
