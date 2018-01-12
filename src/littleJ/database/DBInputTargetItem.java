package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import littleJ.views.dto.InputTargetItemDTO;

public class DBInputTargetItem extends DBBase<InputTargetItemDTO>{
	public DBInputTargetItem(Connection conn){
		this.setConnection(conn);
		this.setTable("inputtargetitem");
		this.setColumnPrimary("idinputtargetitem");
		this.setOrderByColiumn("idtargetitem");
		this.columnsExceptPrimary("idsourceitem,idtargetitem,action,delay");
	}
	
	public List<InputTargetItemDTO> getItemsBySourceItem(int iditem) throws SQLException {
		return getList("idsourceitem", iditem);
	}

	@Override
	protected InputTargetItemDTO mapFromResultSet(ResultSet rs) throws SQLException {
		InputTargetItemDTO inputTargetItemDTO = new InputTargetItemDTO();
		inputTargetItemDTO.setIdInputTargetItem(rs.getInt("idinputtargetitem"));
		inputTargetItemDTO.setIdSourceItem(rs.getInt("idsourceitem"));
		inputTargetItemDTO.setIdTargetItem(rs.getInt("idtargetitem"));
		inputTargetItemDTO.setAction(rs.getInt("action"));
		inputTargetItemDTO.setDelay(rs.getString("delay"));
		
		return inputTargetItemDTO;
	}

	@Override
	protected void mapToResultSetEdit(InputTargetItemDTO inputTargetItemDTO, PreparedStatement pstmt) throws SQLException {
		mapToResultSetInsert(inputTargetItemDTO,pstmt);
		pstmt.setInt(5, inputTargetItemDTO.getIdInputTargetItem());
	}

	@Override
	protected void mapToResultSetInsert(InputTargetItemDTO inputTargetItemDTO, PreparedStatement pstmt) throws SQLException {
		if (inputTargetItemDTO.getDelay().length() == 0){
			inputTargetItemDTO.setDelay("0s");
		}
		pstmt.setInt(1, inputTargetItemDTO.getIdSourceItem());
		pstmt.setInt(2, inputTargetItemDTO.getIdTargetItem());
		pstmt.setInt(3, inputTargetItemDTO.getAction());
		pstmt.setString(4, inputTargetItemDTO.getDelay());		
	}

	@Override
	protected void populateSubItems(InputTargetItemDTO inputTargetItemDTO) throws SQLException {
		inputTargetItemDTO.setSourceItem(new DBItem(getConnection()).getItem(inputTargetItemDTO.getIdSourceItem()));
		inputTargetItemDTO.setTargetItem(new DBItem(getConnection()).getItem(inputTargetItemDTO.getIdTargetItem()));	
	}

	@Override
	protected void deleteRelations(InputTargetItemDTO t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void postUpdate(InputTargetItemDTO t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
}

