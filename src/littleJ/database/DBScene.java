package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import littleJ.LittleJUtils;
import littleJ.views.dto.SceneDTO;

	public class DBScene extends DBBase<SceneDTO>{
		public DBScene(Connection conn){
			this.setConnection(conn);
			this.setTable("scene");
			this.setColumnPrimary("idscene");
			this.setOrderByColiumn("description");
			this.columnsExceptPrimary("description,isfavourite");
		}

		@Override
		protected SceneDTO mapFromResultSet(ResultSet rs) throws SQLException {
			SceneDTO sceneDTO = new SceneDTO();
			sceneDTO.setIdScene(rs.getInt("idscene"));
			sceneDTO.setDescription(rs.getString("description"));
			sceneDTO.setFavourite(LittleJUtils.intToBoolean(rs.getInt("isfavourite")));
			
			return sceneDTO;
		}

		@Override
		protected void mapToResultSetEdit(SceneDTO sceneDTO, PreparedStatement pstmt) throws SQLException {
			mapToResultSetInsert(sceneDTO,pstmt);
			pstmt.setInt(3, sceneDTO.getIdScene());
		}

		@Override
		protected void mapToResultSetInsert(SceneDTO sceneDTO, PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, sceneDTO.getDescription());
			pstmt.setInt(2,LittleJUtils.booleanToInt(sceneDTO.isFavourite()));	
		}

		@Override
		protected void populateSubItems(SceneDTO sceneDTO) throws SQLException {
			// no linked sub items
		}

		@Override
		protected void deleteRelations(SceneDTO sceneDTO) throws SQLException {
			
			String sql = "DELETE FROM sceneitem WHERE idscene = ?";
			try (PreparedStatement pstmt = getConnection().prepareStatement(sql);) {
				pstmt.setInt(1, sceneDTO.getIdScene());

				pstmt.executeUpdate();
			}
			
		}
		
		public List<SceneDTO> getScenesFavourites() throws SQLException {
			return getList("isfavourite", 1);
		}
		
		public SceneDTO getSceneByDescription(String description) throws SQLException {
			return getFirstFromList(getList("description",description));			
		}

		@Override
		protected void postUpdate(SceneDTO t) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		
	}

