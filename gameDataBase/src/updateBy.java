

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameModel.gameDB;
import java.sql.*;

@WebServlet("/updateBy")
public class updateBy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public updateBy() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd=request.getRequestDispatcher("updateMenu.jsp");
		rd.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String field=request.getParameter("field");
		String pid=request.getParameter("pid");
		String newValue=request.getParameter("value");
		try {
		new gameDB();
		if(field.equals("level"))
		{
			gameDB.rs=gameDB.stmt.executeQuery("select level from player where playerid="+pid);
			gameDB.rs.next();
			int oldValue=gameDB.rs.getInt(1);
			System.out.println("Inside the if "+oldValue+" "+newValue+" "+pid);
			int pid1=Integer.parseInt(pid);
			int newValue1=Integer.parseInt(newValue);
			gameDB.updateLevel(pid1,oldValue,newValue1);
		}
		gameDB.stmt.execute("Update player set "+field+"="+newValue+" where playerid="+pid);
		gameDB.con.commit();
		response.sendRedirect("http://localhost:8080/gameDataBase/getPlayers");
		}catch(SQLException e) {}
	}

}
