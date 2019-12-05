import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import gameModel.gameDB;


@WebServlet("/insPlayer")
public class insPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public insPlayer() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd= request.getRequestDispatcher("insPlayer.html");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usn = request.getParameter("usn");
		int lvl = Integer.parseInt(request.getParameter("lvl"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		RequestDispatcher rd=request.getRequestDispatcher("");
		if(request.getParameter("cid").equals("1"))
			rd=request.getRequestDispatcher("insWA1.html");
		else if(request.getParameter("cid").equals("2"))
			rd=request.getRequestDispatcher("insWA2.html");
		else if(request.getParameter("cid").equals("3"))
			rd=request.getRequestDispatcher("insWA3.html");
		else if(request.getParameter("cid").equals("4"))
			rd=request.getRequestDispatcher("insWA4.html");
		rd.include(request, response);
		try {
		new gameDB();	
		gameDB.insPlayer(usn, lvl, cid);
		gameDB.con.commit();
		} catch(SQLException e) {e.printStackTrace()
			;try{gameDB.con.rollback();}catch(SQLException e1) {}}
	}

}

