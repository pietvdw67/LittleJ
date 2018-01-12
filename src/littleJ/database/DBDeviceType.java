package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import littleJ.hardware.dto.DeviceTypeDTO;

public class DBDeviceType extends DBBase<DeviceTypeDTO> {
	public DBDeviceType(Connection conn){
				this.setConnection(conn);
				this.setTable("devicetype");
				this.setColumnPrimary("iddevicetype");
				this.setOrderByColiumn("description");
				this.columnsExceptPrimary("description");
			}

	@Override
	protected DeviceTypeDTO mapFromResultSet(ResultSet rs) throws SQLException {
		DeviceTypeDTO deviceTypeDTO = new DeviceTypeDTO();
		deviceTypeDTO.setIdDeviceType(rs.getInt("iddevicetype"));
		deviceTypeDTO.setDescription(rs.getString("description"));

		return deviceTypeDTO;
	}

	@Override
	protected void mapToResultSetEdit(DeviceTypeDTO deviceTypeDTO, PreparedStatement pstmt) throws SQLException {
		mapToResultSetInsert(deviceTypeDTO, pstmt);
		pstmt.setInt(2, deviceTypeDTO.getIdDeviceType());
	}

	@Override
	protected void mapToResultSetInsert(DeviceTypeDTO deviceTypeDTO, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, deviceTypeDTO.getDescription());

	}

	@Override
	protected void populateSubItems(DeviceTypeDTO deviceTypeDTO) throws SQLException {
		// no linked sub items
	}

	@Override
	protected void deleteRelations(DeviceTypeDTO deviceTypeDTO) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void postUpdate(DeviceTypeDTO t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
