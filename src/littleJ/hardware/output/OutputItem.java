package littleJ.hardware.output;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import littleJ.database.DBConnection;
import littleJ.database.DBEspStatus;
import littleJ.database.DBRaspberryStatus;
import littleJ.database.DBStatusChange;
import littleJ.esp.dto.EspStatusDTO;
import littleJ.hardware.dto.ItemDTO;
import littleJ.hardware.dto.ItemStatus;
import littleJ.processors.statuschange.dto.StatusChangeDTO;
import littleJ.raspberry.service.GPIOHandler;
import littleJ.service.LittleJHandler;

public class OutputItem {
	ItemDTO itemDTO = null;
	int newStatus = 0;
	final static Logger log = Logger.getLogger(OutputItem.class);

	public void createItem(ItemDTO itemDTO, int newStatus) {
		this.itemDTO = itemDTO;
		this.newStatus = newStatus;
	}

	public void process() throws Exception {
		log.info("Processing fired for OutputLight");

		StatusChangeDTO statusChangeDTO = new StatusChangeDTO();
		statusChangeDTO.setAction(newStatus);
		statusChangeDTO.setChangeTimestamp(LocalDateTime.now());
		statusChangeDTO.setIdItem(itemDTO.getIdItem());
		statusChangeDTO.setIdschedule(-1);
		statusChangeDTO.setProcesstime(LocalDateTime.now());
		statusChangeDTO.setIdSourceItem(-1);

		new DBStatusChange(DBConnection.getConnection()).addItem(statusChangeDTO);
	}

	public void invokeAction() throws Exception {
		if (itemDTO.getDeviceDTO().getDeviceTypeDTO().getDescription().toLowerCase().contains("raspberry")) {
			if (newStatus == ItemStatus.ON.getStatusCode()) {
				fireOn();
			} else if (newStatus == ItemStatus.OFF.getStatusCode()) {
				fireOff();
			} else if (newStatus == ItemStatus.TOGGLE.getStatusCode()) {
				fireToggle();
			}

			performDebugFunctions();
		} else if (itemDTO.getDeviceDTO().getDeviceTypeDTO().getDescription().toLowerCase().startsWith("esp")) {
			if (newStatus == 0 || newStatus == 1) {
				new DBEspStatus(DBConnection.getConnection()).updateStatus(itemDTO.getIdDevice(), newStatus);
			} else if (newStatus == 2) {
				int lastStatus = 0;
				EspStatusDTO espStatusDTO = new DBEspStatus(DBConnection.getConnection())
						.getByDevideId(itemDTO.getIdDevice());
				if (espStatusDTO != null) {
					lastStatus = espStatusDTO.getStatus();
				}

				if (lastStatus == 0) {
					newStatus = 1;
				} else {
					newStatus = 0;
				}
				new DBEspStatus(DBConnection.getConnection()).updateStatus(itemDTO.getIdDevice(), newStatus);
			}
			log.info("Changing esp status to " + newStatus + " for " + itemDTO.getDescription());

		}
	}

	private void fireOn() throws Exception {
		log.info("Firing On for : " + itemDTO.getDescription());

		GPIOHandler gpioHandler = new GPIOHandler();
		gpioHandler.pinHigh(itemDTO.getPi4jPinDTO().getIdpi4jpin());
	}

	private void fireOff() throws Exception {
		log.info("Firing Off for : " + itemDTO.getDescription());

		GPIOHandler gpioHandler = new GPIOHandler();
		gpioHandler.pinLow(itemDTO.getPi4jPinDTO().getIdpi4jpin());
	}

	private void fireToggle() throws Exception {
		log.info("Firing Toogle for : " + itemDTO.getDescription());

		GPIOHandler gpioHandler = new GPIOHandler();
		gpioHandler.pinToggle(itemDTO.getPi4jPinDTO().getIdpi4jpin());
	}

	private void performDebugFunctions() throws SQLException {
		if (LittleJHandler.getInstance().getSettingsDTO().isDebugMode()) {
			if (newStatus == 2) {
				int currentStatus = new DBRaspberryStatus(DBConnection.getConnection()).getPinStatus(itemDTO.getIdDevice(), itemDTO.getIdPi4jPin());
				if (currentStatus == 0) {
					newStatus = 1;
				} else {
					newStatus = 0;
				}
			}

			new DBRaspberryStatus(DBConnection.getConnection()).updatePinStatus(itemDTO.getIdPi4jPin(),
					itemDTO.getIdDevice(), newStatus);
		}
	}

}
