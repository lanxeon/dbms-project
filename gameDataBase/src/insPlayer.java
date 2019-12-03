import java.io.IOException;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import gameModel.gameDB;

/**
 * Servlet implementation class getPlayer
 */
@WebServlet("/insPlayer")
public class insPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insPlayer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd= request.getRequestDispatcher("insPlayer.html");
		PrintWriter pw= response.getWriter();
		pw.println("Enter Some Shit");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		rd.forward(request, response);
		try {
		new gameDB();	
		gameDB.insPlayer(usn, lvl, cid);
		gameDB.con.commit();
		} catch(SQLException e) {e.printStackTrace()
			;try{gameDB.con.rollback();}catch(SQLException e1) {}}
	}

}

