package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import DAO.HintsDAO;
import Entity.Hints;

public class HintsDAOImpl implements HintsDAO {

	private Connection connection;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rslt;
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	@Override
	public void addHints(Hints hint) throws SQLException{
		try{
			connection = dataSource.getConnection();
			String query =  "INSERT INTO hints(answer, hint1, hint2, hint3, difficulty) VALUES (?, ?, ?, ?, ?)";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, hint.getAnswer());
			pstmt.setString(2, hint.getHint1());
			pstmt.setString(3, hint.getHint2());
			pstmt.setString(4, hint.getHint3());
			pstmt.setString(5, hint.getDifficulty());
			pstmt.executeUpdate();
		}
		finally{
			pstmt.close();
			connection.close();
		}
	}

	@Override
	public Hints getEasyHints() throws SQLException {
		Hints hint = new Hints();
		try{
			connection = dataSource.getConnection();
			String query = "SELECT * FROM hints WHERE difficulty = 'Easy'";
			stmt = connection.createStatement();			
			rslt = stmt.executeQuery(query);
			if(rslt.next()){
				hint.setHints_id(rslt.getInt("hints_id"));
				hint.setAnswer(rslt.getString("answer"));
				hint.setHint1(rslt.getString("hint1"));
				hint.setHint2(rslt.getString("hint2"));
				hint.setHint3(rslt.getString("hint3"));
				hint.setDifficulty(rslt.getString("difficulty"));
			}
		}
		finally{
			stmt.close();
			rslt.close();
			connection.close();
		}
		return hint;
	}

	@Override
	public Hints getModerateHints() throws SQLException {
		Hints hint = new Hints();
		try{
			connection = dataSource.getConnection();
			String query = "SELECT * FROM hints WHERE difficulty = 'Moderate'";
			stmt = connection.createStatement();			
			rslt = stmt.executeQuery(query);
			if(rslt.next()){
				hint.setHints_id(rslt.getInt("hints_id"));
				hint.setAnswer(rslt.getString("answer"));
				hint.setHint1(rslt.getString("hint1"));
				hint.setHint2(rslt.getString("hint2"));
				hint.setHint3(rslt.getString("hint3"));
				hint.setDifficulty(rslt.getString("difficulty"));
			}
		}
		finally{
			stmt.close();
			rslt.close();
			connection.close();
		}
		return hint;	}

	@Override
	public Hints getHardHints() throws SQLException {
		Hints hint = new Hints();
		try{
			connection = dataSource.getConnection();
			String query = "SELECT * FROM hints WHERE difficulty = 'Hard'";
			stmt = connection.createStatement();			
			rslt = stmt.executeQuery(query);
			if(rslt.next()){
				hint.setHints_id(rslt.getInt("hints_id"));
				hint.setAnswer(rslt.getString("answer"));
				hint.setHint1(rslt.getString("hint1"));
				hint.setHint2(rslt.getString("hint2"));
				hint.setHint3(rslt.getString("hint3"));
				hint.setDifficulty(rslt.getString("difficulty"));
			}
		}
		finally{
			stmt.close();
			rslt.close();
			connection.close();
		}
		return hint;
	}
	
}
