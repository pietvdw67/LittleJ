package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import littleJ.hardware.dto.Pi4jPinDTO;

	public class DBPi4jPin extends DBBase<Pi4jPinDTO>{
		public DBPi4jPin(Connection conn){
			this.setConnection(conn);
			this.setTable("pi4jpin");
			this.setColumnPrimary("idpi4jpin");
			this.setOrderByColiumn("pinnumber");
			this.columnsExceptPrimary("pinnumber");
		}

		@Override
		protected Pi4jPinDTO mapFromResultSet(ResultSet rs) throws SQLException {
			Pi4jPinDTO pi4jPinDTO = new Pi4jPinDTO();
			pi4jPinDTO.setIdpi4jpin(rs.getInt("idpi4jpin"));
			pi4jPinDTO.setPinNumber(rs.getInt("pinnumber"));
			
			return pi4jPinDTO;
		}
		
		public Pi4jPinDTO getPi4jPinByPinNumber(int number) throws SQLException {
			return getFirstFromList(getList("pinnumber", number));
		}

		@Override
		protected void mapToResultSetEdit(Pi4jPinDTO pi4jPinDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(pi4jPinDTO,pstmt);
			pstmt.setInt(2, pi4jPinDTO.getIdpi4jpin());
		}

		@Override
		protected void mapToResultSetInsert(Pi4jPinDTO pi4jPinDTO, PreparedStatement pstmt) throws SQLException {
			pstmt.setInt(1, pi4jPinDTO.getPinNumber());
	
		}

		@Override
		protected void populateSubItems(Pi4jPinDTO pi4jPinDTO) throws SQLException {
			// no linked sub items
		}

		@Override
		protected void deleteRelations(Pi4jPinDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void postUpdate(Pi4jPinDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		
	}

