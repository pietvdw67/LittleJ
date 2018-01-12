package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import littleJ.LittleJUtils;
import littleJ.hardware.dto.ItemTypeDTO;

	public class DBItemType extends DBBase<ItemTypeDTO>{
		public DBItemType(Connection conn){
			this.setConnection(conn);
			this.setTable("itemtype");
			this.setColumnPrimary("iditemtype");
			this.setOrderByColiumn("itemtypename");
			this.columnsExceptPrimary("itemtypename,itemtypeimage,isoutput,isaudio");
		}

		@Override
		protected ItemTypeDTO mapFromResultSet(ResultSet rs) throws SQLException {
			ItemTypeDTO itemTypeDTO = new ItemTypeDTO();
			itemTypeDTO.setIdItemType(rs.getInt("iditemtype"));
			itemTypeDTO.setItemTypeName(rs.getString("itemtypename"));
			itemTypeDTO.setItemTypeImage(rs.getString("itemtypeimage"));
			itemTypeDTO.setOutput(LittleJUtils.intToBoolean(rs.getInt("isoutput")));
			itemTypeDTO.setIsAudio(LittleJUtils.intToBoolean(rs.getInt("isaudio")));
			
			return itemTypeDTO;
		}

		@Override
		protected void mapToResultSetEdit(ItemTypeDTO itemTypeDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(itemTypeDTO,pstmt);
			pstmt.setInt(5, itemTypeDTO.getIdItemType());
		}

		@Override
		protected void mapToResultSetInsert(ItemTypeDTO itemTypeDTO, PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, itemTypeDTO.getItemTypeName());
			pstmt.setString(2, itemTypeDTO.getItemTypeImage());
			pstmt.setInt(3, LittleJUtils.booleanToInt(itemTypeDTO.getIsOutput()));
			pstmt.setInt(4, LittleJUtils.booleanToInt(itemTypeDTO.getIsAudio()));
	
		}

		@Override
		protected void populateSubItems(ItemTypeDTO itemTypeDTO) throws SQLException {
			String imagesPath = "images/itemtype/";
			itemTypeDTO.setImagePathOn(imagesPath + LittleJUtils.getImageOnName(itemTypeDTO.getItemTypeImage()));
			itemTypeDTO.setImagePathOff(imagesPath + LittleJUtils.getImageOffName(itemTypeDTO.getItemTypeImage()));		
		}

		@Override
		protected void deleteRelations(ItemTypeDTO itemTypeDTO) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void postUpdate(ItemTypeDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		
	}

