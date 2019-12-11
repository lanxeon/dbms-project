
import gameModel.gameDB;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public delete() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd= request.getRequestDispatcher("delete.jsp");
		rd.include(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usn=request.getParameter("usn");
		
		try {
			new gameDB();
			gameDB.rs=gameDB.stmt.executeQuery("select username from player where username='"+usn+"'");
			if(gameDB.rs.next())
			{
				gameDB.stmt.executeUpdate("delete from player where username='"+usn+"'");
				gameDB.con.commit();
				response.sendRedirect("getPlayers");
			}
			else {
				response.setContentType("text/html");
				PrintWriter pw=response.getWriter();
				pw.print("<h3><font color='red'>No such username. Please enter valid usename</font></h3><br/>");
				System.out.println("lol");
				RequestDispatcher rd=request.getRequestDispatcher("delete.jsp");
				rd.include(request, response);
			}
		}catch(SQLException e) {e.printStackTrace(); try{gameDB.con.rollback();}catch(SQLException e1){}}
	}

}
