package littleJ;

import java.time.LocalTime;

import littleJ.views.dto.ScheduleItemDTO;

public class LittleJUtils {
	public static String localTimeToString(LocalTime lt) {
		return lt.getHour() + ":" + lt.getMinute();
	}

	public static LocalTime StringToLocalTime(String time) {
		int hour = Integer.parseInt(time.substring(0, time.indexOf(":")));
		int minute = Integer.parseInt(time.substring(time.indexOf(":") + 1));
		return LocalTime.of(hour, minute);
	}

	public static int booleanToInt(boolean value) {
		if (value) {
			return 1;
		} else {
			return 0;
		}
	}

	public static boolean intToBoolean(int value) {
		if (value == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void setDaysOfWeekFromString(String daysOfWeekString, ScheduleItemDTO scheduleItemDTO) {
		if (daysOfWeekString.substring(0, 1).equals("1")) {
			scheduleItemDTO.setMonday(true);
		} else {
			scheduleItemDTO.setMonday(false);
		}
		if (daysOfWeekString.substring(1, 2).equals("1")) {
			scheduleItemDTO.setTuesday(true);
		} else {
			scheduleItemDTO.setTuesday(false);
		}
		if (daysOfWeekString.substring(2, 3).equals("1")) {
			scheduleItemDTO.setWednesday(true);
		} else {
			scheduleItemDTO.setWednesday(false);
		}
		if (daysOfWeekString.substring(3, 4).equals("1")) {
			scheduleItemDTO.setThursday(true);
		} else {
			scheduleItemDTO.setThursday(false);
		}
		if (daysOfWeekString.substring(4, 5).equals("1")) {
			scheduleItemDTO.setFriday(true);
		} else {
			scheduleItemDTO.setFriday(false);
		}
		if (daysOfWeekString.substring(5, 6).equals("1")) {
			scheduleItemDTO.setSaturday(true);
		} else {
			scheduleItemDTO.setSaturday(false);
		}
		if (daysOfWeekString.substring(6, 7).equals("1")) {
			scheduleItemDTO.setSunday(true);
		} else {
			scheduleItemDTO.setSunday(false);
		}
	}

	public static String getDaysOfWeekString(ScheduleItemDTO scheduleItemDTO) {
		StringBuilder daysOfWeekString = new StringBuilder();
		if (scheduleItemDTO.isMonday()) {
			daysOfWeekString.append("1");
		} else {
			daysOfWeekString.append("0");
		}
		if (scheduleItemDTO.isTuesday()) {
			daysOfWeekString.append("1");
		} else {
			daysOfWeekString.append("0");
		}
		if (scheduleItemDTO.isWednesday()) {
			daysOfWeekString.append("1");
		} else {
			daysOfWeekString.append("0");
		}
		if (scheduleItemDTO.isThursday()) {
			daysOfWeekString.append("1");
		} else {
			daysOfWeekString.append("0");
		}
		if (scheduleItemDTO.isFriday()) {
			daysOfWeekString.append("1");
		} else {
			daysOfWeekString.append("0");
		}
		if (scheduleItemDTO.isSaturday()) {
			daysOfWeekString.append("1");
		} else {
			daysOfWeekString.append("0");
		}
		if (scheduleItemDTO.isSunday()) {
			daysOfWeekString.append("1");
		} else {
			daysOfWeekString.append("0");
		}

		return daysOfWeekString.toString();
	}

	public static String getActionText(int action) {
		// Refer to ItemActions for enum
		switch (action) {
		case 0:
			return "Off";
		case 1:
			return "On";
		case 3:
			return "Enable";
		case 4:
			return "Disable";
		case 5:
			return "Play Once";
		case 6:
			return "Repeat";
		default:
			return "Not Set In LittjeJUtils.getActionText";
		}

	}

	public static String getImageOnName(String imageName) {
		int periodPos = imageName.lastIndexOf(".");
		return imageName.substring(0, periodPos) + "_on" + imageName.substring(periodPos);
	}

	public static String getImageOffName(String imageName) {
		int periodPos = imageName.lastIndexOf(".");
		return imageName.substring(0, periodPos) + "_off" + imageName.substring(periodPos);
	}

	public static String getImageBaseName(String imageName) {
		int periodPos = imageName.lastIndexOf(".");
		int underscorePos = imageName.lastIndexOf("_");
		return imageName.substring(0, underscorePos) + imageName.substring(periodPos);
	}
	
	public static String getFileBaseName(String filename) {
		int periodPos = filename.lastIndexOf(".");
		
		return filename.substring(0,periodPos);
	}

}
