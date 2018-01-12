package littleJ.processors.statuschange.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import littleJ.ItemActions;
import littleJ.database.DBConnection;
import littleJ.database.DBItem;
import littleJ.database.DBStatusChange;
import littleJ.hardware.dto.ItemDTO;
import littleJ.hardware.input.InputItem;
import littleJ.hardware.output.OutputItem;
import littleJ.processors.audio.service.AudioPlayerHandler;
import littleJ.processors.statuschange.dto.StatusChangeDTO;
import littleJ.service.LittleJHandler;

public class StatusChangeHandler implements Runnable {
	final static Logger log = Logger.getLogger(StatusChangeHandler.class);

	@Override
	public void run() {
		log.info("Starting up process: StatusChangeHandler");

		boolean doRun = true;
		while (doRun) {
			try {
				updateStatusses();

				TimeUnit.MILLISECONDS.sleep(LittleJHandler.getInstance().getSettingsDTO().getProcessorDelayStatusChange());
			} catch (Exception e) {
				log.error("Error sleeping", e);
			}

			// Remove comment for testing to run only once
			// doRun = false;
		}
	}

	public static synchronized void updateStatusses() throws Exception {
		List<StatusChangeDTO> statusChangeDTOList = new DBStatusChange(DBConnection.getConnection()).getItemsToAction();

		for (StatusChangeDTO statusChangeDTO : statusChangeDTOList) {
			processItem(statusChangeDTO);
		}

	}

	private static void processItem(StatusChangeDTO statusChangeDTO) throws Exception {
		int idItem = statusChangeDTO.getIdItem();
		
		ItemDTO itemDTO = new DBItem(DBConnection.getConnection()).getItem(idItem);
		if (statusChangeDTO.getAction() == ItemActions.STOP_AUDIO.getValue()){
			AudioPlayerHandler.getInstance().stopComplete();			
		}else if (itemDTO.getItemTypeDTO().getIsAudio()){
			if (statusChangeDTO.getAction() == ItemActions.PLAY_ONCE.getValue()){
				AudioPlayerHandler.getInstance().play(itemDTO.getAudioFile());
			} else {
				AudioPlayerHandler.getInstance().playRepeat(itemDTO.getAudioFile());
			}
		} else if (itemDTO.getItemTypeDTO().isOutput()){		
			OutputItem outputItem = new OutputItem();
			outputItem.createItem(statusChangeDTO.getItemDTO(), statusChangeDTO.getAction());
			outputItem.invokeAction();
		} else {
			InputItem inputItem = new InputItem();
			inputItem.createItem(statusChangeDTO.getItemDTO(), statusChangeDTO.getAction());
			inputItem.invokeAction();
		}

		new DBStatusChange(DBConnection.getConnection()).deleteItem(statusChangeDTO.getIdstatuschange());

	}

}
