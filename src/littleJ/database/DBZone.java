package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import littleJ.hardware.dto.ZoneDTO;

	public class DBZone extends DBBase<ZoneDTO>{
		public DBZone(Connection conn){
			this.setConnection(conn);
			this.setTable("zone");
			this.setColumnPrimary("idzone");
			this.setOrderByColiumn("zonename");
			this.columnsExceptPrimary("zonename,zoneimage,temperature");
		}

		@Override
		protected ZoneDTO mapFromResultSet(ResultSet rs) throws SQLException {
			ZoneDTO zoneDTO = new ZoneDTO();
			zoneDTO.setIdZone(rs.getInt("idzone"));
			zoneDTO.setZoneName(rs.getString("zonename"));
			zoneDTO.setZoneImage(rs.getString("zoneimage"));
			zoneDTO.setTemperature(rs.getInt("temperature"));
			
			return zoneDTO;
		}

		@Override
		protected void mapToResultSetEdit(ZoneDTO zoneDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(zoneDTO,pstmt);
			pstmt.setInt(4, zoneDTO.getIdZone());
		}

		@Override
		protected void mapToResultSetInsert(ZoneDTO zoneDTO, PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, zoneDTO.getZoneName());
			pstmt.setString(2, zoneDTO.getZoneImage());
			pstmt.setInt(3, zoneDTO.getTemperature());
		}
		
		public void updateTemperature(int idZone,int temperature) throws SQLException {
			ZoneDTO zoneDTO = getItem(idZone);
			zoneDTO.setTemperature(temperature);
			updateItem(zoneDTO);
		}

		@Override
		protected void populateSubItems(ZoneDTO zoneDTO) throws SQLException {
			// no linked sub items
		}

		@Override
		protected void deleteRelations(ZoneDTO zoneDTO) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void postUpdate(ZoneDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		
	}

