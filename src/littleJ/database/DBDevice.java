package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import littleJ.esp.dto.EspStatusDTO;
import littleJ.hardware.dto.DeviceDTO;
import littleJ.raspberry.dto.RaspberryStatusDTO;

public class DBDevice extends DBBase<DeviceDTO> {
	public DBDevice(Connection conn){
				this.setConnection(conn);
				this.setTable("device");
				this.setColumnPrimary("iddevice");
				this.setOrderByColiumn("description");
				this.columnsExceptPrimary("description,temperature,ip,iddevicetype");
			}

	@Override
	protected DeviceDTO mapFromResultSet(ResultSet rs) throws SQLException {
		DeviceDTO deviceDTO = new DeviceDTO();
		deviceDTO.setIdDevice(rs.getInt("iddevice"));
		deviceDTO.setDescription(rs.getString("description"));
		deviceDTO.setTemperature(rs.getInt("temperature"));
		deviceDTO.setIp(rs.getString("ip"));
		deviceDTO.setIdDeviceType(rs.getInt("iddevicetype"));
		
		return deviceDTO;
	}

	@Override
	protected void mapToResultSetEdit(DeviceDTO deviceDTO, PreparedStatement pstmt) throws SQLException {
		mapToResultSetInsert(deviceDTO, pstmt);
		pstmt.setInt(5, deviceDTO.getIdDevice());
	}

	@Override
	protected void mapToResultSetInsert(DeviceDTO deviceDTO, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, deviceDTO.getDescription());
		pstmt.setInt(2, deviceDTO.getTemperature());
		pstmt.setString(3, deviceDTO.getIp());
		pstmt.setInt(4, deviceDTO.getIdDeviceType());
	}
	
	public DeviceDTO getDeviceByDescription(String description) throws SQLException {
		return getFirstFromList(getList("description", description));
	}
	
	public ArrayList<DeviceDTO> getRaspberryDevices() throws SQLException {		
		ArrayList<DeviceDTO> returnList = (ArrayList<DeviceDTO>)getCustomList("SELECT " + getSelectColumns(true) + " FROM " + getTable() + " WHERE description like 'Raspberry%'" , new ArrayList<>());
		return returnList;		
	}

	@Override
	protected void populateSubItems(DeviceDTO deviceDTO) throws SQLException {
		deviceDTO.setDeviceTypeDTO(new DBDeviceType(getConnection()).getItem(deviceDTO.getIdDeviceType()));
	}

	@Override
	protected void deleteRelations(DeviceDTO deviceDTO) throws SQLException {
		if (deviceDTO.getDeviceTypeDTO().getDescription().toLowerCase().startsWith("esp")){
			DBEspStatus dbEspStatus = new DBEspStatus(getConnection());
			EspStatusDTO espStatusDTO = dbEspStatus.getByDevideId(deviceDTO.getIdDevice());
			if (espStatusDTO != null){
				dbEspStatus.deleteItem(espStatusDTO.getIdEspStatus());
			}
		}
		if (deviceDTO.getDeviceTypeDTO().getDescription().toLowerCase().startsWith("raspberry")){
			DBRaspberryStatus dbRaspberryStatus = new DBRaspberryStatus(getConnection());
			RaspberryStatusDTO raspberryStatusDTO = dbRaspberryStatus.getByDevideId(deviceDTO.getIdDevice());
			if (raspberryStatusDTO != null){
				dbRaspberryStatus.deleteItem(raspberryStatusDTO.getIdRaspberryStatus());
			}
		}		
	}

	@Override
	protected void postUpdate(DeviceDTO t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
