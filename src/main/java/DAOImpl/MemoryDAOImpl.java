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
    int rs=0;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int getScore(Memory memory) {
		
		int result = 0;
		ResultSet rs = null;
		String player = memory.getPlayerId();
		int curr=memory.getScore();
		int finalscore=memory.getScore();
		Connection connection;
		
		try{
			String outcome="";
			PreparedStatement pstmt = null;
		    Statement stmt = null;
		    ResultSet rslt = null;
		    int prevmax=0;
		    int highscore=0;
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			String query  = "SELECT MAX(Score) as max from memscore where Player_id='"+player+"'" ;
			System.out.println(query);
			pstmt = connection.prepareStatement(query);
			rslt = pstmt.executeQuery();
			if(rslt.next())
			{	
			prevmax=rslt.getInt("max");
			}
			else
			{
				prevmax=0;
			}
			System.out.println("MAX:"+prevmax);
			if(prevmax>curr)
			{
				outcome="L";
			}
			else
			{
				outcome="W";
			}
			System.out.println(outcome);
			System.out.println(memory.getPicId());
			 query = "INSERT INTO memscore (Player_id,Score,Outcome,Game_pic_id) values(?,?,?,?)";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, memory.getPlayerId());
				pstmt.setInt(2, memory.getScore());
				pstmt.setString(3, outcome);
				pstmt.setInt(4, memory.getPicId());
				pstmt.executeUpdate();
				System.out.println(query);
				
				String query2 = "SELECT Score FROM memscore where Outcome='W' and Player_id='"+player+"' order by Game_id desc";
				System.out.println(query2);
				PreparedStatement statement = connection.prepareStatement(query2); 
				//statement.setMaxRows(1); 
				rslt = statement.executeQuery();
				if(rslt.next())
				{
					highscore = rslt.getInt("Score");
				}	
				System.out.println(highscore);
				String query3 = "UPDATE users SET game3_highscore='"+highscore+"' where username='"+player+"'";
				System.out.println(query3);
				PreparedStatement statement2 = connection.prepareStatement(query3); 
				statement2.executeUpdate();
				
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return finalscore;
	}

    		
	}

	


