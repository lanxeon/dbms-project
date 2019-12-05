import java.io.PrintWriter;
import java.sql.SQLException;
import gameModel.gameDB;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insNewArmour
 */
@WebServlet("/updateArmour/insNewArmour")
public class insNewArmour extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insNewArmour() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aid = Integer.parseInt(request.getParameter("aid"));
		PrintWriter pw=response.getWriter();
		
		try {
			new gameDB();
			gameDB.rs=gameDB.stmt.executeQuery("select playerid from player where weaponid is NULL");
			gameDB.rs.next();
			int pid=gameDB.rs.getInt(1);
			gameDB.stmt.executeUpdate("update player set armourid="+aid+" where playerid="+pid);
			gameDB.equipArmour(pid,aid);
			gameDB.con.commit();
			} catch(SQLException e) {e.printStackTrace();
			try{gameDB.con.rollback();}catch(SQLException e1) {}}
		pw.println("UPDATED ARMOUR SUCCESSFULLY NIGGA");
	}

}
