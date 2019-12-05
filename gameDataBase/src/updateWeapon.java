

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import gameModel.gameDB;


@WebServlet("/updateWeapon")
public class updateWeapon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public updateWeapon() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("updateWeapon.jsp");
		rd.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid=Integer.parseInt(request.getParameter("pid"));
		try {
			new gameDB();
			gameDB.rs=gameDB.stmt.executeQuery("select weaponid from player where playerid="+pid);
			gameDB.rs.next();
			int oldwid=gameDB.rs.getInt(1);
			gameDB.unequipWeapon(pid, oldwid);
			gameDB.stmt.executeUpdate("update player set weaponid=NULL where playerid="+pid);
			gameDB.con.commit();
			gameDB.rs=gameDB.stmt.executeQuery("select classid from player where playerid="+pid);
			gameDB.rs.next();
			int cid=gameDB.rs.getInt(1);
			System.out.println("Class id chosen is "+cid);
			RequestDispatcher rd=request.getRequestDispatcher("insW"+cid+".html");
			rd.forward(request,response);
		}catch(SQLException e) {e.printStackTrace();}
	}
}
