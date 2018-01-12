package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import littleJ.processors.statuschange.dto.StatusChangeDTO;

	public class DBStatusChange extends DBBase<StatusChangeDTO>{
		public DBStatusChange(Connection conn){
			this.setConnection(conn);
			this.setTable("statuschange");
			this.setColumnPrimary("idstatuschange");
			this.setOrderByColiumn("processtime");
			this.columnsExceptPrimary("idItem,changeTimestamp,idschedule,processtime,action,idSourceItem");
		}

		@Override
		protected StatusChangeDTO mapFromResultSet(ResultSet rs) throws SQLException {
			StatusChangeDTO statusChangeDTO = new StatusChangeDTO();
			statusChangeDTO.setIdstatuschange(rs.getInt("idstatuschange"));
			statusChangeDTO.setIdItem(rs.getInt("idItem"));
			java.sql.Timestamp changeTimestamp = rs.getTimestamp("changeTimestamp");
			statusChangeDTO.setChangeTimestamp(changeTimestamp.toLocalDateTime());			
			statusChangeDTO.setIdschedule(rs.getInt("idschedule"));
			java.sql.Timestamp processTimestamp = rs.getTimestamp("processtime");
			statusChangeDTO.setProcesstime(processTimestamp.toLocalDateTime());	
			statusChangeDTO.setAction(rs.getInt("action"));	
			statusChangeDTO.setIdSourceItem(rs.getInt("idSourceItem"));
			
			return statusChangeDTO;
		}

		@Override
		protected void mapToResultSetEdit(StatusChangeDTO statusChangeDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(statusChangeDTO,pstmt);
			pstmt.setInt(7, statusChangeDTO.getIdstatuschange());
		}

		@Override
		protected void mapToResultSetInsert(StatusChangeDTO statusChangeDTO, PreparedStatement pstmt) throws SQLException {
			pstmt.setInt(1, statusChangeDTO.getIdItem());
			Timestamp changeTimestamp = Timestamp.valueOf(statusChangeDTO.getChangeTimestamp());
			pstmt.setTimestamp(2,changeTimestamp);
			pstmt.setInt(3, statusChangeDTO.getIdschedule());
			Timestamp processTimestamp = Timestamp.valueOf(statusChangeDTO.getProcesstime());
			pstmt.setTimestamp(4,processTimestamp);
			pstmt.setInt(5, statusChangeDTO.getAction());
			pstmt.setInt(6, statusChangeDTO.getIdSourceItem());	
		}

		@Override
		protected void populateSubItems(StatusChangeDTO statusChangeDTO) throws SQLException {
			statusChangeDTO.setItemDTO(new DBItem(getConnection()).getItem(statusChangeDTO.getIdItem()));
			statusChangeDTO.setScheduleDTO(new DBSchedule(getConnection()).getItem(statusChangeDTO.getIdschedule()));
		}

		@Override
		protected void deleteRelations(StatusChangeDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
		public List<StatusChangeDTO> getItemsToAction() throws SQLException {
			String sql = "SELECT " + getSelectColumns(true) + " FROM " + getTable() + " WHERE processtime IS NULL OR processtime <= NOW()"; 
			return getCustomList(sql,null);
		}

		@Override
		protected void postUpdate(StatusChangeDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
		public void deleteByScheduleId(int idSchedule) throws SQLException {
			List<StatusChangeDTO> statusChangeDTOlist = getList("idschedule", idSchedule);
			for (StatusChangeDTO statusChangeDTO : statusChangeDTOlist){
				deleteItem(statusChangeDTO.getIdstatuschange());
			}
		}
		
		public void removeByIdSourceItem(int idSourceItem) throws SQLException {
			try (PreparedStatement pstmt = getConnection().prepareStatement("DELETE FROM statuschange WHERE idSourceItem = ?");){
				pstmt.setInt(1, idSourceItem);
				pstmt.executeUpdate();
			}
			
		}

		
	}


