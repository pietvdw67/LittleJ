package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import littleJ.hardware.dto.Pi4jPinDTO;
import littleJ.raspberry.dto.RaspberryStatusDTO;
import littleJ.raspberry.service.GPIOHandler;

	public class DBRaspberryStatus extends DBBase<RaspberryStatusDTO>{
		public DBRaspberryStatus(Connection conn){
			this.setConnection(conn);
			this.setTable("raspberrystatus");
			this.setColumnPrimary("idraspberrystatus");
			this.setOrderByColiumn("iddevice");
			this.columnsExceptPrimary("iddevice,pin00,pin01,pin02,pin03,pin04,pin05,pin06,pin07,pin08,pin09,pin10,pin11,pin12,pin13,pin14,pin15,pin16");
		}

		@Override
		protected RaspberryStatusDTO mapFromResultSet(ResultSet rs) throws SQLException {
			RaspberryStatusDTO raspberryStatusDTO = new RaspberryStatusDTO();
			raspberryStatusDTO.setIdRaspberryStatus(rs.getInt("idraspberrystatus"));
			raspberryStatusDTO.setIdDevice(rs.getInt("iddevice"));
			raspberryStatusDTO.setPin00(rs.getInt("pin00"));
			raspberryStatusDTO.setPin01(rs.getInt("pin01"));
			raspberryStatusDTO.setPin02(rs.getInt("pin02"));
			raspberryStatusDTO.setPin03(rs.getInt("pin03"));
			raspberryStatusDTO.setPin04(rs.getInt("pin04"));
			raspberryStatusDTO.setPin05(rs.getInt("pin05"));
			raspberryStatusDTO.setPin06(rs.getInt("pin06"));
			raspberryStatusDTO.setPin07(rs.getInt("pin07"));
			raspberryStatusDTO.setPin08(rs.getInt("pin08"));
			raspberryStatusDTO.setPin09(rs.getInt("pin09"));
			raspberryStatusDTO.setPin10(rs.getInt("pin10"));
			raspberryStatusDTO.setPin11(rs.getInt("pin11"));
			raspberryStatusDTO.setPin12(rs.getInt("pin12"));
			raspberryStatusDTO.setPin13(rs.getInt("pin13"));
			raspberryStatusDTO.setPin14(rs.getInt("pin14"));
			raspberryStatusDTO.setPin15(rs.getInt("pin15"));
			raspberryStatusDTO.setPin16(rs.getInt("pin16"));			
			
			return raspberryStatusDTO;
		}

		@Override
		protected void mapToResultSetEdit(RaspberryStatusDTO paspberryStatusDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(paspberryStatusDTO,pstmt);
			pstmt.setInt(19, paspberryStatusDTO.getIdRaspberryStatus());
		}

		@Override
		protected void mapToResultSetInsert(RaspberryStatusDTO raspberryStatusDTO, PreparedStatement pstmt) throws SQLException {
			pstmt.setInt(1, raspberryStatusDTO.getIdDevice());
			pstmt.setInt(2, raspberryStatusDTO.getPin00());
			pstmt.setInt(3, raspberryStatusDTO.getPin01());
			pstmt.setInt(4, raspberryStatusDTO.getPin02());
			pstmt.setInt(5, raspberryStatusDTO.getPin03());
			pstmt.setInt(6, raspberryStatusDTO.getPin04());
			pstmt.setInt(7, raspberryStatusDTO.getPin05());
			pstmt.setInt(8, raspberryStatusDTO.getPin06());
			pstmt.setInt(9, raspberryStatusDTO.getPin07());
			pstmt.setInt(10, raspberryStatusDTO.getPin08());
			pstmt.setInt(11, raspberryStatusDTO.getPin09());
			pstmt.setInt(12, raspberryStatusDTO.getPin10());
			pstmt.setInt(13, raspberryStatusDTO.getPin11());
			pstmt.setInt(14, raspberryStatusDTO.getPin12());
			pstmt.setInt(15, raspberryStatusDTO.getPin13());
			pstmt.setInt(16, raspberryStatusDTO.getPin14());
			pstmt.setInt(17, raspberryStatusDTO.getPin15());
			pstmt.setInt(18, raspberryStatusDTO.getPin16());
	
		}
		
		public int getPinStatus(int idDevice,int idpi4jpin) throws SQLException{
			RaspberryStatusDTO raspberryStatusDTO = getFirstFromList(getList("idDevice", idDevice));
			if (raspberryStatusDTO == null){
				return 0;
			}
			
			Pi4jPinDTO pi4jPinDTO = new DBPi4jPin(getConnection()).getItem(idpi4jpin);
			
			switch (pi4jPinDTO.getPinNumber()){
				case 0: return raspberryStatusDTO.getPin00();
				case 1: return raspberryStatusDTO.getPin01();
				case 2: return raspberryStatusDTO.getPin02();
				case 3: return raspberryStatusDTO.getPin03();
				case 4: return raspberryStatusDTO.getPin04();
				case 5: return raspberryStatusDTO.getPin05();
				case 6: return raspberryStatusDTO.getPin06();
				case 7: return raspberryStatusDTO.getPin07();
				case 8: return raspberryStatusDTO.getPin08();
				case 9: return raspberryStatusDTO.getPin09();
				case 10: return raspberryStatusDTO.getPin10();
				case 11: return raspberryStatusDTO.getPin11();
				case 12: return raspberryStatusDTO.getPin12();
				case 13: return raspberryStatusDTO.getPin13();
				case 14: return raspberryStatusDTO.getPin14();
				case 15: return raspberryStatusDTO.getPin15();
				case 16: return raspberryStatusDTO.getPin16();
			}
			
			return 0;
		}
		
		public void updatePinStatus(int idPi4jPin,int idDevice,int status) throws SQLException{
			DBRaspberryStatus dbRaspberryStatus = new DBRaspberryStatus(DBConnection.getConnection());
			RaspberryStatusDTO raspberryStatusDTOCheck = dbRaspberryStatus.getByDevideId(idDevice);
			if (raspberryStatusDTOCheck == null) {
				RaspberryStatusDTO raspberryStatusDTOInsert = new RaspberryStatusDTO();
				raspberryStatusDTOInsert.setIdDevice(idDevice);
				dbRaspberryStatus.addItem(raspberryStatusDTOInsert);
			}
			
			Pi4jPinDTO pi4jPinDTO = new DBPi4jPin(DBConnection.getConnection()).getItem(idPi4jPin);
			String sql = "UPDATE raspberrystatus SET " + GPIOHandler.getColumnNameFromPinNumber(pi4jPinDTO.getPinNumber() )+ " = ? where idDevice = ?";
			List<Object> parmlist = new ArrayList<>();
			parmlist.add(status);
			parmlist.add(idDevice);
			updateCustom(-1,sql, parmlist);
		}
		
		public RaspberryStatusDTO getByDevideId(int deviceId) throws SQLException{
			return getFirstFromList(getList("iddevice", deviceId));			
		}

		@Override
		protected void populateSubItems(RaspberryStatusDTO raspberryStatusDTO) throws SQLException {
			// no linked sub items
		}

		@Override
		protected void deleteRelations(RaspberryStatusDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void postUpdate(RaspberryStatusDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
	}


