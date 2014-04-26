package DAOImpl;

import javax.sql.DataSource;

public class PlayersDAOImpl {
	
	public DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
