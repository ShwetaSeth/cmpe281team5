package com.sample.team5;

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

import DAO.ScrambleDAO;
import Entity.Scramble;

@Controller
public class ScrambleController {
	
	public DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ScrambleController.class);	
	ApplicationContext appContext = AppContext.getApplicationContext();
	
	@RequestMapping(value = "scramble", method = RequestMethod.GET)
	public String scramble(Model model) {		
		ScrambleController con = (ScrambleController)appContext.getBean("scrambleController");		
		con.getJSP();		
		model.addAttribute("score", "0");
		return "scramble";
	}
	
	public void getJSP(){
		
	}
	
	@RequestMapping(value = "scramble", method = RequestMethod.POST)
	public String enterWord(HttpServletRequest request, HttpSession session, Model model) {		
		ScrambleController con = (ScrambleController)appContext.getBean("scrambleController");		
		int score = con.enterWord(request);	
		
		//String message = con.getJSP();
		model.addAttribute("score", score);
		return "scramble";
	}
	
	public int enterWord(HttpServletRequest request) {		
		ScrambleDAO scrambleDAO = (ScrambleDAO)appContext.getBean("scrambleDAOImpl");
		Scramble scramble = new Scramble();
		System.out.println(request.getParameter("word"));
		scramble.setLastWord(request.getParameter("word"));
		int currScore = scrambleDAO.getCurrentScore(scramble);
		
		scramble.setCurrScore(currScore);
		int score = scrambleDAO.enterWord(scramble);
		
		System.out.println("after increment"+scramble.getCurrScore());
		return score;
	}
	
}
