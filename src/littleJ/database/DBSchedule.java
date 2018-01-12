package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import littleJ.LittleJUtils;
import littleJ.views.dto.ScheduleDTO;

	public class DBSchedule extends DBBase<ScheduleDTO>{
		public DBSchedule(Connection conn){
			this.setConnection(conn);
			this.setTable("schedule");
			this.setColumnPrimary("idschedule");
			this.setOrderByColiumn("description");
			this.columnsExceptPrimary("description,isactive,populated");
		}

		@Override
		protected ScheduleDTO mapFromResultSet(ResultSet rs) throws SQLException {
			ScheduleDTO scheduleDTO = new ScheduleDTO();
			scheduleDTO.setIdSchedule(rs.getInt("idschedule"));
			scheduleDTO.setDescription(rs.getString("description"));
			scheduleDTO.setActive(LittleJUtils.intToBoolean(rs.getInt("isactive")));
			scheduleDTO.setPopulated(LittleJUtils.intToBoolean(rs.getInt("populated")));
			
			return scheduleDTO;
		}

		@Override
		protected void mapToResultSetEdit(ScheduleDTO scheduleDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(scheduleDTO,pstmt);
			pstmt.setInt(4, scheduleDTO.getIdSchedule());
		}

		@Override
		protected void mapToResultSetInsert(ScheduleDTO scheduleDTO, PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, scheduleDTO.getDescription());
			pstmt.setInt(2, LittleJUtils.booleanToInt(scheduleDTO.isActive()));
			pstmt.setInt(3, LittleJUtils.booleanToInt(scheduleDTO.isPopulated()));
	
		}

		@Override
		protected void populateSubItems(ScheduleDTO scheduleDTO) throws SQLException {
			String sceneImagesPath = "images/base/";
			scheduleDTO.setImagePathOn(sceneImagesPath + "schedule_on.png");
			scheduleDTO.setImagePathOff(sceneImagesPath + "schedule_off.png");
		}

		@Override
		protected void deleteRelations(ScheduleDTO scheduleDTO) throws SQLException {
			String sql = "DELETE FROM scheduleitem WHERE idschedule = ?";
			try (PreparedStatement pstmt = getConnection().prepareStatement(sql);) {
				pstmt.setInt(1, scheduleDTO.getIdSchedule());
				pstmt.executeUpdate();
			}
			
		}
		
		public ScheduleDTO getScheduleByDescription(String description) throws SQLException {
			return getFirstFromList(getList("description", description));	
		}

		@Override
		protected void postUpdate(ScheduleDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
		public void deleteInactiveSchedules() throws SQLException {
			List<ScheduleDTO> scheduleDTOlist = getList("isactive", 0);
			for (ScheduleDTO scheduleDTO : scheduleDTOlist){
				new DBStatusChange(getConnection()).deleteByScheduleId(scheduleDTO.getIdSchedule());
			}
			
			List<ScheduleDTO> scheduleDTOlistUnpopulated = getList("populated", 0);
			for (ScheduleDTO scheduleDTO : scheduleDTOlistUnpopulated){
				new DBStatusChange(getConnection()).deleteByScheduleId(scheduleDTO.getIdSchedule());
			}
		}
		
		public List<ScheduleDTO> getUnprocessedActiveSchedules() throws SQLException {
			String sql = "SELECT " + getSelectColumns(true) + " FROM " + getTable() + " WHERE isactive=1 AND populated=0";
			List<ScheduleDTO> scheduleDTOlist = getCustomList(sql, new ArrayList<>());
			
			return scheduleDTOlist;
		}
		
		public void updateScheduleToPopulated(int idSchedule) throws SQLException {
			String sql = "UPDATE " + getTable() + " SET populated=1 WHERE idSchedule = ?";
			try (PreparedStatement pstmt = getConnection().prepareStatement(sql);){
				pstmt.setInt(1, idSchedule);
				
				pstmt.executeUpdate();
			}
		}
		
		public void updateScheduleToUnPopulated(int idSchedule) throws SQLException {
			String sql = "UPDATE " + getTable() + " SET populated=0 WHERE idSchedule = ?";
			try (PreparedStatement pstmt = getConnection().prepareStatement(sql);){
				pstmt.setInt(1, idSchedule);
				
				pstmt.executeUpdate();
			}
		}
		
		public void resetPopulatedSchedules() throws SQLException {
			String sql = "UPDATE " + getTable() + " SET populated=0";
			try (PreparedStatement pstmt = getConnection().prepareStatement(sql);){
							
				pstmt.executeUpdate();
			}
		}
	}

