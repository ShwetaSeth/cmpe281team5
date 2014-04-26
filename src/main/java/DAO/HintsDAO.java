package DAO;

import java.sql.SQLException;

import Entity.Hints;

public interface HintsDAO {

	public void addHints(Hints hint) throws SQLException;
	public Hints getEasyHints() throws SQLException;
	public Hints getModerateHints() throws SQLException;
	public Hints getHardHints() throws SQLException;
	
	
}
