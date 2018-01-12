package littleJ.raspberry.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import littleJ.database.DBConnection;
import littleJ.database.DBPi4jPin;
import littleJ.hardware.dto.Pi4jPinDTO;
import littleJ.service.LittleJHandler;

/**
 * Before a raspberry bin can be used, it must be privsioned
 * The handler keeps a list of provisioned pines, before a pin can be used, it first check if the pin is provisioned
 * before it can be used
 * @author F3953289
 *
 */
public class GPIOHandler {
	final static Logger log = Logger.getLogger(GPIOHandler.class);
	
	public void pinHigh(int idPi4jPin) throws SQLException {
		if (LittleJHandler.getInstance().getSettingsDTO().isDebugMode()) {
			return;
		}
		
		GpioPinDigitalOutput outputPin = getProvisionedPin(idPi4jPin);
		outputPin.high();
	}

	public void pinLow(int idPi4jPin) throws SQLException {
		if (LittleJHandler.getInstance().getSettingsDTO().isDebugMode()) {
			return;
		}
		
		GpioPinDigitalOutput outputPin = getProvisionedPin(idPi4jPin);
		outputPin.low();

	}

	public void pinToggle(int idPi4jPin) throws SQLException {
		if (LittleJHandler.getInstance().getSettingsDTO().isDebugMode()) {
			return;
		}
		
		GpioPinDigitalOutput outputPin = getProvisionedPin(idPi4jPin);
		outputPin.toggle();
	}

	public int getPinStatus(int pinNumber) throws SQLException {
		if (LittleJHandler.getInstance().getSettingsDTO().isDebugMode()) {
			return 0;
		}
		
		Pi4jPinDTO pi4jPinDTO = new DBPi4jPin(DBConnection.getConnection()).getPi4jPinByPinNumber(pinNumber);
		
		
		int idPi4jPin = pi4jPinDTO.getIdpi4jpin();
		GpioPinDigitalOutput outputPin = getProvisionedPin(idPi4jPin);
		if (outputPin == null){
			return 0;
		}
		
		if (outputPin.getState().equals(PinState.LOW)){
			return 0;
		}
		
		if (outputPin.getState().equals(PinState.HIGH)){
			return 1;
		}
		
		return 0;
	}
	
	public GpioPinDigitalOutput getProvisionedPin(int idPi4jPin) throws SQLException{
		if (LittleJHandler.getInstance().getSettingsDTO().isDebugMode()) {
			return null;
		}
		
		if (LittleJHandler.getInstance().getProvisioinedPins() == null){
			LittleJHandler.getInstance().setProvisioinedPins(new HashMap<>());
		}
		
		if (!LittleJHandler.getInstance().getProvisioinedPins().containsKey(idPi4jPin)){
			provisionPin(idPi4jPin);
		}		
		
		return LittleJHandler.getInstance().getProvisioinedPins().get(idPi4jPin);
	
	}
	

	public void provisionPin(int idPi4jPin) throws SQLException {
		if (LittleJHandler.getInstance().getSettingsDTO().isDebugMode()) {
			return;
		}

		int pinNumber = getPinNumberFromIdPi4jPin(idPi4jPin);
		log.info("provisioning pin number: " + pinNumber);
		Pin gpioPin = getPinFromNumber(pinNumber);
		System.out.println(gpioPin);
		GpioPinDigitalOutput gpioPinDigitalOutput = GpioFactory.getInstance().provisionDigitalOutputPin(gpioPin, String.valueOf(idPi4jPin), PinState.LOW);
		
		LittleJHandler.getInstance().getProvisioinedPins().put(idPi4jPin, gpioPinDigitalOutput);
	}

	public int getPinNumberFromIdPi4jPin(int pi4jpin) throws SQLException {
		Pi4jPinDTO pi4jPinDTO = new DBPi4jPin(DBConnection.getConnection()).getItem(pi4jpin);
		return pi4jPinDTO.getPinNumber();
	}
	
	public static String getColumnNameFromPinNumber(int pinNumber){
		return "pin" + String.format("%02d", pinNumber);
		
	}

	public static Pin getPinFromNumber(int number) {
		switch (number) {
		case 0:
			return RaspiPin.GPIO_00;
		case 1:
			return RaspiPin.GPIO_01;
		case 2:
			return RaspiPin.GPIO_02;
		case 3:
			return RaspiPin.GPIO_03;
		case 4:
			return RaspiPin.GPIO_04;
		case 5:
			return RaspiPin.GPIO_05;
		case 6:
			return RaspiPin.GPIO_06;
		case 7:
			return RaspiPin.GPIO_07;
		case 8:
			return RaspiPin.GPIO_08;
		case 9:
			return RaspiPin.GPIO_09;
		case 10:
			return RaspiPin.GPIO_10;
		case 11:
			return RaspiPin.GPIO_11;
		case 12:
			return RaspiPin.GPIO_12;
		case 13:
			return RaspiPin.GPIO_13;
		case 14:
			return RaspiPin.GPIO_14;
		case 15:
			return RaspiPin.GPIO_15;
		case 16:
			return RaspiPin.GPIO_16;
		case 17:
			return RaspiPin.GPIO_17;
		case 18:
			return RaspiPin.GPIO_18;
		case 19:
			return RaspiPin.GPIO_19;
		case 20:
			return RaspiPin.GPIO_20;
		case 21:
			return RaspiPin.GPIO_21;
		case 22:
			return RaspiPin.GPIO_22;
		case 23:
			return RaspiPin.GPIO_23;
		case 24:
			return RaspiPin.GPIO_24;
		case 25:
			return RaspiPin.GPIO_25;
		case 26:
			return RaspiPin.GPIO_26;
		case 27:
			return RaspiPin.GPIO_27;
		case 28:
			return RaspiPin.GPIO_28;
		case 29:
			return RaspiPin.GPIO_29;

		}

		return RaspiPin.GPIO_00;
	}

}
