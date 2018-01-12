package littleJ.processors.schedule.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import littleJ.database.DBConnection;
import littleJ.database.DBSchedule;
import littleJ.database.DBScheduleItem;
import littleJ.database.DBStatusChange;
import littleJ.processors.statuschange.dto.StatusChangeDTO;
import littleJ.processors.statuschange.service.StatusChangeHandler;
import littleJ.service.LittleJHandler;
import littleJ.views.dto.ScheduleDTO;
import littleJ.views.dto.ScheduleItemDTO;

public class ScheduleHandler implements Runnable {
	final static Logger log = Logger.getLogger(StatusChangeHandler.class);

	@Override
	public void run() {
		log.info("Starting up process: ScheduleHandler");
		try {
			new DBSchedule(DBConnection.getConnection()).resetPopulatedSchedules();
		} catch (Exception e) {
			log.error("Error resetting schedules", e);
		}

		boolean doRun = true;
		while (doRun) {
			try {
				rescheduleDaysSchedules();
				processSchedules();

				TimeUnit.MILLISECONDS.sleep(LittleJHandler.getInstance().getSettingsDTO().getProcessorDelaySchedular());
			} catch (Exception e) {
				log.error("Error sleeping", e);
			}

			// Remove comment for testing to run only once
			// doRun = false;
		}
	}
	
	/**
	 * Reset schedules once in the day
	 * @throws Exception
	 */
	private void rescheduleDaysSchedules() throws Exception{

		if (LittleJHandler.getInstance().getResetScheduleDate() == null || LittleJHandler.getInstance().getResetScheduleDate().isBefore(LocalDate.now())){
			new DBSchedule(DBConnection.getConnection()).resetPopulatedSchedules();
			LittleJHandler.getInstance().setResetScheduleDate(LocalDate.now());
		}
	}

	private void processSchedules() throws Exception {
		DBSchedule dbSchedule = new DBSchedule(DBConnection.getConnection());
		dbSchedule.deleteInactiveSchedules();


		List<ScheduleDTO> scheduleDTOToProcesslist = dbSchedule.getUnprocessedActiveSchedules();
		for (ScheduleDTO scheduleDTO : scheduleDTOToProcesslist) {
			createStatusChangeForScheduleItems(scheduleDTO);
			dbSchedule.updateScheduleToPopulated(scheduleDTO.getIdSchedule());
		}
	}

	private void createStatusChangeForScheduleItems(ScheduleDTO scheduleDTO) throws Exception {
		List<ScheduleItemDTO> scheduleItemDTOList = new DBScheduleItem(DBConnection.getConnection())
				.getScheduleItemsBySchedule(scheduleDTO.getIdSchedule());

		for (ScheduleItemDTO scheduleItemDTO : scheduleItemDTOList) {

			// don't process items past current time
			if (scheduleItemDTO.getScheduleTime().isBefore(LocalTime.now())) {
				continue;
			}

			// don't process item if it is not ment for current day
			if ((LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY) && !scheduleItemDTO.isMonday()) {
				continue;
			}
			if ((LocalDate.now().getDayOfWeek() == DayOfWeek.TUESDAY) && !scheduleItemDTO.isTuesday()) {
				continue;
			}
			if ((LocalDate.now().getDayOfWeek() == DayOfWeek.WEDNESDAY) && !scheduleItemDTO.isWednesday()) {
				continue;
			}
			if ((LocalDate.now().getDayOfWeek() == DayOfWeek.THURSDAY) && !scheduleItemDTO.isThursday()) {
				continue;
			}
			if ((LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY) && !scheduleItemDTO.isFriday()) {
				continue;
			}
			if ((LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) && !scheduleItemDTO.isSaturday()) {
				continue;
			}
			if ((LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) && !scheduleItemDTO.isSunday()) {
				continue;
			}

			// create schedule
			StatusChangeDTO statusChangeDTO = new StatusChangeDTO();
			statusChangeDTO.setAction(scheduleItemDTO.getAction());
			statusChangeDTO.setChangeTimestamp(LocalDateTime.now());
			statusChangeDTO.setIdItem(scheduleItemDTO.getIdItem());
			statusChangeDTO.setIdschedule(scheduleDTO.getIdSchedule());
			LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), scheduleItemDTO.getScheduleTime());
			statusChangeDTO.setProcesstime(ldt);
			statusChangeDTO.setIdSourceItem(-1);

			new DBStatusChange(DBConnection.getConnection()).addItem(statusChangeDTO);
		}

	}

}
