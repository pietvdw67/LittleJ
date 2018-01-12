package littleJ.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DBBase<T> {
	private String table;
	private String columnsExceptPrimary;
	private String columnPrimary;
	private String orderByColumn;
	private Connection conn;

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	protected Connection getConnection() {
		return conn;
	}

	public void setTable(String table) {
		this.table = " " + table + " ";
	}

	public void setColumnPrimary(String columnPrimary) {
		this.columnPrimary = columnPrimary;
	}

	public void columnsExceptPrimary(String columnsExceptPrimary) {
		this.columnsExceptPrimary = columnsExceptPrimary;
	}

	public void setOrderByColiumn(String orderByColumn) {
		this.orderByColumn = " ORDER BY " + orderByColumn + " ";
	}

	protected String getSelectColumns(boolean includePrimary) {
		String returnString = columnsExceptPrimary;
		if (includePrimary) {
			returnString = columnPrimary + "," + returnString;
		}

		return " " + returnString + " ";
	}

	protected T getFirstFromList(List<T> list) {
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public void deleteItem(int idPrimaryValue) throws SQLException {

		T t = getItem(idPrimaryValue);
		deleteRelations(t);

		String sql = "DELETE FROM" + table + "WHERE " + columnPrimary + " = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, idPrimaryValue);

			pstmt.executeUpdate();
		}
		
	}

	public void updateItem(T t) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE" + table + "SET ");

		for (String column : getSelectColumns(false).split(",")) {
			sb.append(column + "=?,");
		}

		sb.setLength(sb.length() - 1);
		sb.append(" WHERE " + columnPrimary + " = ?");

		try (PreparedStatement pstmt = conn.prepareStatement(sb.toString());) {
			mapToResultSetEdit(t, pstmt);
			pstmt.executeUpdate();
		}
		
		postUpdate(t);
	}
	
	protected void updateCustom(int primaryId,String sql,List<Object> parmlist) throws SQLException {
		int counter = 0;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			for (Object parameter : parmlist){
				counter++;
				pstmt.setObject(counter, parameter);
			}
			pstmt.executeUpdate();
		}
		
		if (primaryId >=0 ){		
			T t = getItem(primaryId);
			postUpdate(t);
		}
	}

	public void addItem(T t) throws SQLException {
		int last_inserted_id = -1;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO" + table + "(" + getSelectColumns(false) + ") VALUES (");
		for (@SuppressWarnings("unused")
		String column : getSelectColumns(false).split(",")) {
			sb.append("?,");
		}

		sb.setLength(sb.length() - 1);
		sb.append(")");

		try (PreparedStatement pstmt = conn.prepareStatement(sb.toString(),Statement.RETURN_GENERATED_KEYS);) {
			mapToResultSetInsert(t, pstmt);

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next())
            {
                last_inserted_id = rs.getInt(1);
            }
		}
		
		if (last_inserted_id > -1){
			T tInserted = getItem(last_inserted_id);
			postUpdate(tInserted);
		}
	}

	protected abstract T mapFromResultSet(ResultSet rs) throws SQLException;

	/**
	 * Include primary column as last column
	 * 
	 * @param pstmt
	 */
	protected abstract void mapToResultSetEdit(T t, PreparedStatement pstmt) throws SQLException;

	/**
	 * Dont incude primary column
	 * 
	 * @param pstmt
	 */
	protected abstract void mapToResultSetInsert(T t, PreparedStatement pstmt) throws SQLException;

	/**
	 * Populates sub items of an item
	 * 
	 * @param t
	 * @throws SQLException
	 */
	protected abstract void populateSubItems(T t) throws SQLException;

	/**
	 * Removes relational table items upon deletion of an entry
	 * 
	 * @param t
	 * @throws SQLException
	 */
	protected abstract void deleteRelations(T t) throws SQLException;
	
	/**
	 * Executes after an update has occurred
	 * @param t
	 * @throws SQLException
	 */
	protected abstract void postUpdate(T t) throws SQLException;

	public List<T> getAllItems() throws SQLException {
		return getList("", -1);
	}

	public T getItem(int primaryValue) throws SQLException {
		List<T> list = getList(columnPrimary, primaryValue);

		return getFirstFromList(list);
	}

	protected List<T> getCustomList(String sql, List<Object> parameterList) throws SQLException {
		T dtoInstance = null;
		List<T> list = new ArrayList<>();

		int counter = 0;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			if (parameterList != null && parameterList.size() > 0) {
				for (Object parameter : parameterList) {
					counter++;
					pstmt.setObject(counter, parameter);
				}
			}

			try (ResultSet rs = pstmt.executeQuery();) {

				while (rs.next()) {
					dtoInstance = mapFromResultSet(rs);

					populateSubItems(dtoInstance);

					list.add(dtoInstance);
				}
			}
		}

		return list;
	}

	protected List<T> getList(String column, Object value) throws SQLException {
		T dtoInstance = null;
		List<T> list = new ArrayList<>();
		String sql = "SELECT" + getSelectColumns(true) + "FROM" + table;

		if (column.length() > 0) {
			sql += "WHERE " + column + " = ?";
		}
		
		sql+= orderByColumn;

		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			if (column.length() > 0) {
				pstmt.setObject(1, value);
			}
			try (ResultSet rs = pstmt.executeQuery();) {

				while (rs.next()) {
					dtoInstance = mapFromResultSet(rs);

					populateSubItems(dtoInstance);

					list.add(dtoInstance);
				}
			}
		}

		return list;
	}

	protected String getOrderByColumn() {
		return orderByColumn;
	}

	protected String getTable() {
		return table;
	}

	protected String getColumnPrimary() {
		return columnPrimary;
	}

}
