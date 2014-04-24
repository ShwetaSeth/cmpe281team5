package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import DAO.ScrambleDAO;
import Entity.Scramble;
import Entity.User;

public class ScrambleDAOImpl implements ScrambleDAO{
	public DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rslt = null;
    	
	
	public boolean enterWord(Scramble scramble) {
		int i=0;
		String result = "";
		String lastWord = scramble.getLastWord().toLowerCase();
	
		System.out.println(lastWord);
		Connection connection;
		
		try{
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			
			String query = "Select count(*) AS count from scramblewords where word = '" + lastWord +"'";
			System.out.println(query);
			rslt = stmt.executeQuery(query);
			
			//System.out.println("1."+ rslt.getInt(0));
			while (rslt.next()) {
			System.out.println("count is"+ rslt.getInt("count"));
			connection.close();
			if(rslt.getInt("count") > 0){
				    return true;
				}
			
			
			else
				return false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		
		}
		return false;
		
	}

	

}
