package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import DAO.MemoryDAO;
import Entity.Memory;


public class MemoryDAOImpl implements MemoryDAO{
	public DataSource dataSource;
	PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rslt = null;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int getScore(Memory memory) {
		
		int result = 0;
		String player = memory.getPlayerId();
		int curr=memory.getScore();
		Connection connection;
		
		try{
			String outcome="";
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			String query  = "SELECT MAX(Score) from memscore where Player_id='"+player+"'" ;
			pstmt = connection.prepareStatement(query);
			ResultSet rslt = pstmt.executeQuery();
			int prevmax=rslt.getInt(0);
			if(prevmax>curr)
			{
				outcome="L";
			}
			else
			{
				outcome="W";
			}
			 query = "INSERT INTO memscore (Player_id,Score,Outcome,Game_pic_id) values(?,?,?,?)";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, memory.getPlayerId());
				pstmt.setInt(2, memory.getScore());
				pstmt.setString(3, outcome);
				pstmt.setInt(4, memory.getPicId());
				pstmt.executeUpdate();
											
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return result;
	}

    	
	
	
		
	
		
	}

	


