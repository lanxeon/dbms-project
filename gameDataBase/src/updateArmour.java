

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameModel.gameDB;

/**
 * Servlet implementation class updateArmour
 */
@WebServlet("/updateArmour")
public class updateArmour extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateArmour() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("updateArmour.jsp");
		rd.include(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid=Integer.parseInt(request.getParameter("pid"));
		try {
			new gameDB();
			gameDB.rs=gameDB.stmt.executeQuery("select armourid from player where playerid="+pid);
			gameDB.rs.next();
			int oldaid=gameDB.rs.getInt(1);
			gameDB.unequipArmour(pid, oldaid);
			gameDB.stmt.executeUpdate("update player set armourid=NULL where playerid="+pid);
			gameDB.con.commit();
			gameDB.rs=gameDB.stmt.executeQuery("select classid from player where playerid="+pid);
			gameDB.rs.next();
			int cid=gameDB.rs.getInt(1);
			System.out.println("Class id chosen is "+cid);
			RequestDispatcher rd=request.getRequestDispatcher("insA"+cid+".html");
			rd.forward(request,response);
		}catch(SQLException e) {e.printStackTrace();}
	}

}
