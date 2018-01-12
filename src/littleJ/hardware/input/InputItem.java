package littleJ.hardware.input;

import org.apache.log4j.Logger;

import littleJ.ItemActions;
import littleJ.database.DBConnection;
import littleJ.database.DBItem;
import littleJ.hardware.dto.ItemDTO;
import littleJ.hardware.output.OutputItem;

public class InputItem {
	ItemDTO itemDTO = null;
	int newStatus = 0;
	final static Logger log = Logger.getLogger(OutputItem.class);
	
	public void createItem(ItemDTO itemDTO, int newStatus) {
		this.itemDTO = itemDTO;
		this.newStatus = newStatus;
	}
	
	public void invokeAction() throws Exception {
		if (newStatus == ItemActions.DISABLE.getValue()){
			itemDTO.setAtive(false);
			log.info("Disabled input device " + itemDTO.getDescription() );
		}
		if (newStatus == ItemActions.ENABLE.getValue()){
			itemDTO.setAtive(true);	
			log.info("Enabled input device " + itemDTO.getDescription() );
		}
		
		new DBItem(DBConnection.getConnection()).updateItem(itemDTO);
	}

}
