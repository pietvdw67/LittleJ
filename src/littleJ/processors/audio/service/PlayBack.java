package littleJ.processors.audio.service;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class PlayBack implements Runnable {
	private AdvancedPlayer player;

	public void run() {
		try {
			player.play();
		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.println("Failed to play the file.");
		}

	}

	/**
	 * Stops the audio, if repeat mode is on, it will restart
	 */
	public void stopPlay() {
		try {
			if (player != null) {
				if (player.getPlayBackListener() == null) {
					player.close();
				} else {
					player.stop();
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.println("Failed to play the file.");
		}
	}

	/**
	 * Stops the audio completely , if repeat mode is on, the audio will not
	 * continue
	 */
	public void stopComplete() {
		try {
			if (player != null) {
				player.close();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.println("Failed to play the file.");
		}
	}

	public AdvancedPlayer getPlayer() {
		return player;
	}

	public void setPlayer(AdvancedPlayer player) {
		this.player = player;
	}

}
