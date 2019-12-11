import java.io.IOException;
import java.io.PrintWriter;

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
		int found=0;
		//IMPORTANT: MOVE THE CODE FROM BElOW TO HERE
		try {
			new gameDB();
			//new lines to add
			gameDB.rs=gameDB.stmt.executeQuery("select username from player");
			while(gameDB.rs.next())
				if(gameDB.rs.getString(1).equals(usn))
					{found=1; break;}
			if(found==1)
			{
				response.setContentType("text/html");
				PrintWriter pw=response.getWriter();//make sure to import PrintWriter as well
				pw.println("<b><font color='red'>User already taken. Please Insert values again</font></b></br>");
				rd= request.getRequestDispatcher("insPlayer.html");
				rd.include(request, response);
			}
			else {//add
			//end of new lines to add
			gameDB.insPlayer(usn, lvl, cid);
			gameDB.con.commit();
			}//add
			} catch(SQLException e) {e.printStackTrace(); try{gameDB.con.rollback();}catch(SQLException e1) {}}
		
		if(found==0)
		{
		if(request.getParameter("cid").equals("1"))
			rd=request.getRequestDispatcher("insWA1.html");
		else if(request.getParameter("cid").equals("2"))
			rd=request.getRequestDispatcher("insWA2.html");
		else if(request.getParameter("cid").equals("3"))
			rd=request.getRequestDispatcher("insWA3.html");
		else if(request.getParameter("cid").equals("4"))
			rd=request.getRequestDispatcher("insWA4.html");
		rd.include(request, response);
		}
		
	}

}

