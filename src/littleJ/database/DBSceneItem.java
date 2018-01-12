package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import littleJ.views.dto.SceneItemDTO;

	public class DBSceneItem extends DBBase<SceneItemDTO>{
		public DBSceneItem(Connection conn){
			this.setConnection(conn);
			this.setTable("sceneitem");
			this.setColumnPrimary("idsceneitem");
			this.setOrderByColiumn("iditem");
			this.columnsExceptPrimary("idscene,iditem,delay,action");
		}

		@Override
		protected SceneItemDTO mapFromResultSet(ResultSet rs) throws SQLException {
			SceneItemDTO sceneItemDTO = new  SceneItemDTO();
			sceneItemDTO.setIdSceneItem(rs.getInt("idsceneitem"));
			sceneItemDTO.setIdScene(rs.getInt("idscene"));
			sceneItemDTO.setIdItem(rs.getInt("iditem"));
			sceneItemDTO.setDelay(rs.getString("delay"));
			sceneItemDTO.setAction(rs.getInt("action"));

			
			return sceneItemDTO;
		}

		@Override
		protected void mapToResultSetEdit(SceneItemDTO sceneItemDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(sceneItemDTO,pstmt);
			pstmt.setInt(5, sceneItemDTO.getIdSceneItem());
		}

		@Override
		protected void mapToResultSetInsert(SceneItemDTO sceneItemDTO, PreparedStatement pstmt) throws SQLException {
			if (sceneItemDTO.getDelay().length() == 0){
				sceneItemDTO.setDelay("0s");
			}
			pstmt.setInt(1, sceneItemDTO.getIdScene());
			pstmt.setInt(2, sceneItemDTO.getIdItem());
			pstmt.setString(3, sceneItemDTO.getDelay());
			pstmt.setInt(4, sceneItemDTO.getAction());
	
		}

		@Override
		protected void populateSubItems(SceneItemDTO sceneItemDTO) throws SQLException {
			sceneItemDTO.setSceneDTO(new DBScene(getConnection()).getItem(sceneItemDTO.getIdScene()));
			sceneItemDTO.setItemDTO(new DBItem(getConnection()).getItem(sceneItemDTO.getIdItem()));
		}

		@Override
		protected void deleteRelations(SceneItemDTO sceneItemDTO) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
		public List<SceneItemDTO> getSceneItemsByScene(int idScene) throws SQLException {
			return getList("idScene", idScene);
		}

		@Override
		protected void postUpdate(SceneItemDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		
	}

