package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import littleJ.LittleJUtils;
import littleJ.views.dto.ScheduleItemDTO;

	public class DBScheduleItem extends DBBase<ScheduleItemDTO>{
		public DBScheduleItem(Connection conn){
			this.setConnection(conn);
			this.setTable("scheduleitem");
			this.setColumnPrimary("idscheduleitem");
			this.setOrderByColiumn("iditem,scheduletime");
			this.columnsExceptPrimary("idschedule,iditem,scheduletime,daysofweek,action");
		}

		@Override
		protected ScheduleItemDTO mapFromResultSet(ResultSet rs) throws SQLException {
			ScheduleItemDTO scheduleItemDTO = new ScheduleItemDTO();
			scheduleItemDTO.setIdScheduleItem(rs.getInt("idscheduleitem"));
			scheduleItemDTO.setIdSchedule(rs.getInt("idschedule"));
			scheduleItemDTO.setIdItem(rs.getInt("iditem"));
			scheduleItemDTO.setScheduleTime(LittleJUtils.StringToLocalTime(rs.getString("scheduletime")));
			LittleJUtils.setDaysOfWeekFromString(rs.getString("daysofweek"), scheduleItemDTO);
			scheduleItemDTO.setAction(rs.getInt("action"));
			
			return scheduleItemDTO;
		}


		@Override
		protected void mapToResultSetEdit(ScheduleItemDTO scheduleItemDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(scheduleItemDTO,pstmt);
			pstmt.setInt(6, scheduleItemDTO.getIdScheduleItem());
		}

		@Override
		protected void mapToResultSetInsert(ScheduleItemDTO scheduleItemDTO, PreparedStatement pstmt) throws SQLException {
			pstmt.setInt(1, scheduleItemDTO.getIdSchedule());
			pstmt.setInt(2, scheduleItemDTO.getIdItem());
			pstmt.setString(3, LittleJUtils.localTimeToString(scheduleItemDTO.getScheduleTime()));
			pstmt.setString(4, LittleJUtils.getDaysOfWeekString(scheduleItemDTO));	
			pstmt.setInt(5, scheduleItemDTO.getAction());
	
		}

		@Override
		protected void populateSubItems(ScheduleItemDTO scheduleItemDTO) throws SQLException {
			scheduleItemDTO.setScheduleDTO(new DBSchedule(getConnection()).getItem(scheduleItemDTO.getIdSchedule()));		
			scheduleItemDTO.setItemDTO(new DBItem(getConnection()).getItem(scheduleItemDTO.getIdItem()));
		}

		@Override
		protected void deleteRelations(ScheduleItemDTO scheduleItemDTO) throws SQLException {
			new DBSchedule(getConnection()).updateScheduleToUnPopulated(scheduleItemDTO.getIdSchedule());
			
		}
		
		public List<ScheduleItemDTO> getScheduleItemsBySchedule(int idSchedule) throws SQLException {
			return getList("idschedule", idSchedule);
		}

		@Override
		protected void postUpdate(ScheduleItemDTO scheduleItemDTO) throws SQLException {
			new DBSchedule(getConnection()).updateScheduleToUnPopulated(scheduleItemDTO.getIdSchedule());		
		}

		
	}

