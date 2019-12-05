
import java.sql.*;
import gameModel.gameDB;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updateWeapon/insNewWeapon")
public class insNewWeapon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public insNewWeapon() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*int pid=Integer.parseInt(request.getParameter("pid"));
		try {
		new gameDB();
		gameDB.rs=gameDB.stmt.executeQuery("select classid from player where playerid="+pid);
		gameDB.rs.next();
		int cid=gameDB.rs.getInt(1);
		System.out.println("lol "+cid);
		RequestDispatcher rd=request.getRequestDispatcher("insW4.html");
		rd.include(request,response);
		}catch(SQLException e) {e.printStackTrace(); try {gameDB.con.rollback();}catch(SQLException e1) {}}*/
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int wid = Integer.parseInt(request.getParameter("wid"));
		PrintWriter pw=response.getWriter();
		
		try {
			new gameDB();
			gameDB.rs=gameDB.stmt.executeQuery("select playerid from player where weaponid is NULL");
			gameDB.rs.next();
			int pid=gameDB.rs.getInt(1);
			gameDB.stmt.executeUpdate("update player set weaponid="+wid+" where playerid="+pid);
			gameDB.equipWeapon(pid,wid);
			gameDB.con.commit();
			} catch(SQLException e) {e.printStackTrace();
			try{gameDB.con.rollback();}catch(SQLException e1) {}}
		pw.println("UPDATED WEAPON SUCCESSFULLY NIGGA");
	}
}

