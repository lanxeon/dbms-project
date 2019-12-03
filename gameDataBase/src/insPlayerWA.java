import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameModel.gameDB;
import java.sql.*;
/**
 * Servlet implementation class insPlayerWA
 */
@WebServlet("/insPlayer/WA")
public class insPlayerWA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insPlayerWA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int wid = Integer.parseInt(request.getParameter("wid"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		PrintWriter pw=response.getWriter();
		
		try {
			new gameDB();	
			gameDB.insWeaponAndArmour(wid,aid);
			gameDB.con.commit();
			} catch(SQLException e) {e.printStackTrace();
			try{gameDB.con.rollback();}catch(SQLException e1) {}}
		pw.println("POSTED DATA SUCCESSFULLY NIGGA");
	}
}
