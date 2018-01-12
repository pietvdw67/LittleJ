package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import littleJ.LittleJUtils;
import littleJ.esp.dto.EspStatusDTO;
import littleJ.hardware.dto.ItemDTO;

	public class DBItem extends DBBase<ItemDTO>{
		public DBItem(Connection conn){
			this.setConnection(conn);
			this.setTable("item");
			this.setColumnPrimary("iditem");
			this.setOrderByColiumn("description");
			this.columnsExceptPrimary("description,iditemtype,iddevice,idpi4jpin,idzone,isActive,isFavourite,audiofile");
		}

		@Override
		protected ItemDTO mapFromResultSet(ResultSet rs) throws SQLException {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setIdItem(rs.getInt("iditem"));
			itemDTO.setDescription(rs.getString("description"));
			itemDTO.setIdItemType(rs.getInt("iditemtype"));
			itemDTO.setIdDevice(rs.getInt("iddevice"));
			itemDTO.setIdPi4jPin(rs.getInt("idpi4jpin"));
			itemDTO.setIdZone(rs.getInt("idzone"));
			itemDTO.setAtive(LittleJUtils.intToBoolean(rs.getInt("isActive")));
			itemDTO.setFavourite(LittleJUtils.intToBoolean(rs.getInt("isFavourite")));
			itemDTO.setAudioFile(rs.getString("audiofile"));
			
			return itemDTO;
		}

		@Override
		protected void mapToResultSetEdit(ItemDTO itemDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(itemDTO,pstmt);
			pstmt.setInt(9, itemDTO.getIdItem());
		}

		@Override
		protected void mapToResultSetInsert(ItemDTO itemDTO, PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, itemDTO.getDescription());
			pstmt.setInt(2, itemDTO.getIdItemType());
			pstmt.setInt(3, itemDTO.getIdDevice());
			pstmt.setInt(4, itemDTO.getIdPi4jPin());
			pstmt.setInt(5, itemDTO.getIdZone());
			pstmt.setInt(6, LittleJUtils.booleanToInt(itemDTO.isAtive()));
			pstmt.setInt(7, LittleJUtils.booleanToInt(itemDTO.isFavourite()));	
			pstmt.setString(8, itemDTO.getAudioFile());
		}

		@Override
		protected void populateSubItems(ItemDTO itemDTO) throws SQLException {
			itemDTO.setItemTypeDTO(new DBItemType(getConnection()).getItem(itemDTO.getIdItemType()));
			itemDTO.setDeviceDTO(new DBDevice(getConnection()).getItem(itemDTO.getIdDevice()));
			itemDTO.setPi4jPinDTO(new DBPi4jPin(getConnection()).getItem(itemDTO.getIdPi4jPin()));
			itemDTO.setZoneDTO(new DBZone(getConnection()).getItem(itemDTO.getIdZone()));

			if (itemDTO.getItemTypeDTO().isOutput() && !itemDTO.getItemTypeDTO().getIsAudio()){
				if (itemDTO.getDeviceDTO() != null && itemDTO.getDeviceDTO().getDeviceTypeDTO().getDescription().toLowerCase().startsWith("raspberry")){
					itemDTO.setStatus(new DBRaspberryStatus(getConnection()).getPinStatus(itemDTO.getIdDevice(), itemDTO.getIdPi4jPin()));
				} else if (itemDTO.getDeviceDTO() != null &&  itemDTO.getDeviceDTO().getDeviceTypeDTO().getDescription().toLowerCase().startsWith("esp")){
					EspStatusDTO espStatusDTO = new DBEspStatus(getConnection()).getByDevideId(itemDTO.getIdDevice());
					if (espStatusDTO == null){
						itemDTO.setStatus(0);
					} else {
						itemDTO.setStatus(espStatusDTO.getStatus());
					}
				}
			} else {
				// Input item or audio
				itemDTO.setStatus(LittleJUtils.booleanToInt(itemDTO.isAtive()));
			}
		}

		@Override
		protected void deleteRelations(ItemDTO itemDTO) throws SQLException {
			String sql = "DELETE FROM inputtargetitem where idsourceitem = ?";
			try (PreparedStatement pstmt = getConnection().prepareStatement(sql);) {
				pstmt.setInt(1, itemDTO.getIdItem());

				pstmt.executeUpdate();
			}
			
		}
		
		public List<ItemDTO> getOutputItemsFavourites() throws SQLException {
			return getOutputItemsFiltered("isfavourite", 1);
		}

		public List<ItemDTO> getOutputItemsByItemType(int iditemtype) throws SQLException {
			return getOutputItemsFiltered("iditemtype", iditemtype);
		}
		
		public List<ItemDTO> getItemsByItemType(int iditemtype) throws SQLException {
			return getList("iditemtype", iditemtype);
		}

		public List<ItemDTO> getOutputItemsByZone(int idZone) throws SQLException {
			return getOutputItemsFiltered("idzone", idZone);
		}
		
		public List<ItemDTO> getItemsByDevice(int idDevice) throws SQLException {
			return getList("iddevice", idDevice);
		}
		
		public List<ItemDTO> getInputItems() throws SQLException {
			List<ItemDTO> itemDTOlist = getAllItems();
			List<ItemDTO> returnList = new ArrayList<>();
			for (ItemDTO itemDTO : itemDTOlist){
				if (!itemDTO.getItemTypeDTO().isOutput()){
					returnList.add(itemDTO);
				}
			}
			
			return returnList;
		}
		
		public List<ItemDTO> getOutputItems() throws SQLException {
			List<ItemDTO> itemDTOlist = getAllItems();
			List<ItemDTO> returnList = new ArrayList<>();
			for (ItemDTO itemDTO : itemDTOlist){
				if (itemDTO.getItemTypeDTO().isOutput()){
					returnList.add(itemDTO);
				}
			}
			
			return returnList;
		}
		
		public List<ItemDTO> getOutputItemsFiltered(String column,Object value) throws SQLException {
			List<ItemDTO> itemDTOlist = getList(column, value);
			List<ItemDTO> returnList = new ArrayList<>();
			for (ItemDTO itemDTO : itemDTOlist){
				if (itemDTO.getItemTypeDTO().isOutput()){
					returnList.add(itemDTO);
				}
			}
			
			return returnList;
		}

		@Override
		protected void postUpdate(ItemDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		
	}


