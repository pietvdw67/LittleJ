package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import littleJ.esp.dto.EspStatusDTO;

	public class DBEspStatus extends DBBase<EspStatusDTO>{
		public DBEspStatus(Connection conn){
			this.setConnection(conn);
			this.setTable("espstatus");
			this.setColumnPrimary("idespstatus");
			this.setOrderByColiumn("iddevice");
			this.columnsExceptPrimary("iddevice,status");
		}

		@Override
		protected EspStatusDTO mapFromResultSet(ResultSet rs) throws SQLException {
			EspStatusDTO espStatusDTO = new EspStatusDTO();
			espStatusDTO.setIdEspStatus(rs.getInt("idespstatus"));
			espStatusDTO.setIdDevice(rs.getInt("iddevice"));
			espStatusDTO.setStatus(rs.getInt("status"));
			
			return espStatusDTO;
		}

		@Override
		protected void mapToResultSetEdit(EspStatusDTO espStatusDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(espStatusDTO,pstmt);
			pstmt.setInt(3, espStatusDTO.getIdEspStatus());
		}

		@Override
		protected void mapToResultSetInsert(EspStatusDTO espStatusDTO, PreparedStatement pstmt) throws SQLException {
			pstmt.setInt(1, espStatusDTO.getIdDevice());
			pstmt.setInt(2, espStatusDTO.getStatus());	
		}

		@Override
		protected void populateSubItems(EspStatusDTO espStatusDTO) throws SQLException {
			// no linked sub items
		}

		@Override
		protected void deleteRelations(EspStatusDTO espStatusDTO) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
		public EspStatusDTO getByDevideId(int deviceId) throws SQLException{
			return getFirstFromList(getList("iddevice", deviceId));			
		}
		
		public void updateStatus(int idDevice,int status) throws SQLException {
			DBEspStatus dbEspStatus= new DBEspStatus(DBConnection.getConnection());
			EspStatusDTO epStatusDTOCheck = dbEspStatus.getByDevideId(idDevice);
			if (epStatusDTOCheck == null){
				EspStatusDTO espStatusDTOInsert = new EspStatusDTO();
				espStatusDTOInsert.setIdDevice(idDevice);
				dbEspStatus.addItem(espStatusDTOInsert);				
				epStatusDTOCheck = dbEspStatus.getByDevideId(idDevice);
			}
			
			epStatusDTOCheck.setStatus(status);			
			super.updateItem(epStatusDTOCheck);
		}

		@Override
		protected void postUpdate(EspStatusDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
	}

