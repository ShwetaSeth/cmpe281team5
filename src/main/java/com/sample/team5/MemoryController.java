package com.sample.team5;

import java.awt.List;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.MemoryDAO;
import Entity.Memory;



@Controller
public class MemoryController {
	
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
    
	private static final Logger logger = LoggerFactory.getLogger(MemoryController.class);	
	ApplicationContext appContext = AppContext.getApplicationContext();
	int pic;
	
	@RequestMapping(value = "memory", method = RequestMethod.GET)
	
	public String memory(Model model) throws SQLException {		
		MemoryController con = (MemoryController)appContext.getBean("memoryController");
		
		//MemoryDAO memoryDAO = (MemoryDAO)appContext.getBean("memoryDAOImpl");
		
		
		int num=con.selectGamePic();
		String pic="wc"+num+".jpg"; 
		model.addAttribute("pic",pic );
		model.addAttribute("picid",num );
		return "memory";
	}
	
	public int selectGamePic() throws SQLException{
		Connection conn;

		conn = dataSource.getConnection();
		
		//Selecting a random picture
		int pic=0;
		int picnum []={1,2,3,4,5,6,7,8,9,10};
		Random random = new Random();
		pic= picnum[random.nextInt(picnum.length)];
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet res = meta.getTables(null, null, "Gamepic", null);
		if(res.next()){
			System.out.println("Table 'Gamepic' already exists.");
			logger.info("Table 'Gamepic' already exists.");
			
			
			
		}
		else{
		
			//create gamepic table
			Statement stmtocc = conn.createStatement();
			String queryocc = "CREATE TABLE IF NOT EXISTS `team5`.`Gamepic`(`"+
					"Game_pic_id` INT NOT NULL AUTO_INCREMENT,"+
					"`Occurence` INT NULL DEFAULT 0,PRIMARY KEY (`Game_pic_id`))";
			stmtocc.executeUpdate(queryocc);
			
			
			
			
// create image table		
			Statement stmt = conn.createStatement();

String query="CREATE TABLE IF NOT EXISTS `team5`.`image`(`Id` INT NOT NULL AUTO_INCREMENT,`Words` VARCHAR(45) NULL,`Game_pic_id` INT NULL,PRIMARY KEY (`Id`))";

			stmt.executeUpdate(query);
			logger.info("Table 'image' created.");
			System.out.println("Table 'image' created.");
			
					
			
					     
		  
			Statement stmt2 = conn.createStatement();
			String query2="";
				 query2 = "INSERT INTO image(Words,Game_pic_id) values('history',1),('flame',1),"
				 		+ "('light',1),('earth',1),('advertisement',1),('account',1),('ice',1),"
				 		+ "('belief',1),('discussion',1),('government',1),"
				 		+ "('memory',2),('committee',2),('servant',2),('light',2),('humor',2),('answer',2),('invention',2),('religion',2),('plant',2),('destruction',2),"
				 		+ "('trade',3),('milk',3),('food',3),('peace',3),('list',3),('condition',3),('art',3),('direction',3),('rest',3),('poison',3),"
				 		+ "('money',4),('twist',4),('butter',4),('fruit',4),('road',4),('silver',4),('copy',4),('authority',4),('distance',4),('wound',4),"
				 		+ "('respect',5),('digestion',5),('competition',5),('breath',5),('liquid',5),('argument',5),('expert',5),('fold',5),('point',5),('winter',5),"
				 		+ "('transport',6),('reward',6),('force',6),('discovery',6),('building',6),('wood',6),('attack',6),('mind',6),('polish',6),('connection',6),"
				 		+ "('experience',7),('mountain',7),('morning',7),('limit',7),('size',7),('print',7),('metal',7),('division',7),('cotton',7),('room',7),"
				 		+ "('position',8),('disease',8),('trouble',8),('rice',8),('burst',8),('attention',8),('sign',8),('minute',8),('cook',8),('friend',8),"
				 		+ "('tin',9),('iron',9),('flight',9),('company',9),('brass',9),('wind',9),('detail',9),('play',9),('expansion',9),('representative',9),"
				 		+ "('mist',10),('river',10),('turn',10),('business',10),('front',10),('disgust',10),('silk',10),('copper',10),('work',10),('powder',10)";	
				 System.out.println(query2);
				 stmt2.executeUpdate(query2);
				
			
		 		
			logger.info("Records inserted in table 'image'.");
			System.out.println("Records inserted in table 'image'.");
			
			//create score table
			String query12 = "CREATE TABLE IF NOT EXISTS `team5`.`memscore`(`"+
					"Game_id` INT NOT NULL AUTO_INCREMENT,"+
					"`Player_id` VARCHAR(50) NOT NULL,"+
					"`Score` INT NULL ,"
					+ "`Outcome` ENUM('W','L') NULL ,"
					+ "`Game_pic_id` INT NOT NULL ,"
					+ "PRIMARY KEY (`Game_id`))"
					+ "ENGINE = InnoDB";
					stmt.executeUpdate(query12);
					logger.info("Table 'memscore' created.");
					System.out.println("Table 'memscore' created.");

			
		}
		Statement stmt = conn.createStatement();
		String query = "UPDATE Gamepic SET Occurence=Occurence+1 where Game_pic_id="+pic;
		
		stmt.executeUpdate(query);
		logger.info("Table 'gamepic' updated.");
		System.out.println("Table 'gamepic' updated.");
		conn.close();
		return pic;
		
	}	
		
@RequestMapping(value = "memoryscore", method = RequestMethod.POST)
	
public String getScore(HttpServletRequest req, HttpSession session, Model model) 
{	
	   session.setMaxInactiveInterval(300);
	   int score=0;
		MemoryController con = (MemoryController)appContext.getBean("memoryController");
		//MemoryDAO memoryDAO = (MemoryDAO)appContext.getBean("memoryDAOImpl");
		model.addAttribute("picid", req.getParameter("picid"));
		String picnum=req.getParameter("picid");
		int picno=Integer.parseInt(picnum);
		System.out.println(req.getParameter("ans"));
		String [] words=req.getParameter("ans").split("\n");
		for(int j=0;j<words.length;j++)
		{
		System.out.println(words[j]);
		}
		try {
			score = con.countWords(picno,words);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int message = con.getGameScore(req);
		model.addAttribute("score",score );
		model.addAttribute("message", "Your score for this game is");
		
		return "memoryscore";
	}

public int getGameScore(HttpServletRequest request) 
{		
	MemoryDAO memoryDAO = (MemoryDAO)appContext.getBean("memoryDAOImpl");
	Memory memory  = new Memory();
		
	//memory.setPlayerId(request.getParameter("username"));
	//memory.setScore(request.getParameter(score));
	//memory.setPicId(request.getParameter(picid));
	//memory.setLname(request.getParameter("lname"));
	
	int result = memoryDAO.getScore(memory);
	return result;
}

public int countWords(int picid,String [] wordlist) throws SQLException{
		Connection conn;

		
		conn = dataSource.getConnection();
		System.out.println(wordlist);
		//finding words used in the game picture
		
		//String query="Select Word1,Word2,Word3,Word4,Word5,Word6,Word7,Word8,Word9,Word10 from gamepic where Game_pic_id='"+picid+"'";
		  String [] arr1={"history","flame","light","earth","advertisement","account","ice","belief","discussion","government"};
			String [] arr2={"memory","committee","servant","light","humor","answer","invention","religion","plant","destruction"};
			String [] arr3={"trade","milk","food","peace","list","condition","art","direction","rest","poison"};
			String [] arr4={"money","twist","butter","fruit","road","silver","copy","authority","distance","wound"};
			String [] arr5={"respect","digestion","competition","breath","liquid","argument","expert","fold","point","winter"};
			String [] arr6={"transport","reward","force","discovery","building","wood","attack","mind","polish","connection"};
			String [] arr7={"experience","mountain","morning","limit","size","print","metal","division","cotton","room"};
			String [] arr8={"position","disease","trouble","rice","burst","attention","sign","minute","cook","friend"};
			String [] arr9={"tin","iron","flight","company","brass","wind","detail","play","expansion","representative"};
			String [] arr10={"mist","river","turn","business","front","disgust","silk","copper","work","powder"};
			
		String query="Select count(*) from image where Game_pic_id='"+picid+"' and Words in("+wordlist+")";
		System.out.println(query);
		//System.out.println(wordlist);
		pstmt = conn.prepareStatement(query);
		rslt = pstmt.executeQuery();
			
		int score=rslt.getInt(0);
				
		System.out.println(score);
		conn.close();
		return score;
		
		
}	
	
	
	
	@RequestMapping(value = "memoryans", method = RequestMethod.POST)
	public String enterWord(HttpServletRequest request, HttpSession session, Model model) {		
		MemoryController con = (MemoryController)appContext.getBean("memoryController");		
		model.addAttribute("picid", request.getParameter("picid"));
		return "memoryscore";
		
		
		
	}
	
					
	}
	
	
	
