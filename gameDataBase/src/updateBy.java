

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
		int found=0;
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
			gameDB.stmt.execute("Update player set "+field+"="+newValue+" where playerid="+pid);
		}
		else {
			gameDB.rs=gameDB.stmt.executeQuery("select username from player");
			while(gameDB.rs.next())
				if(gameDB.rs.getString(1).equals(newValue))
					{found=1; break;}
			if(found==1)
			{
				response.setContentType("text/html");
				PrintWriter pw=response.getWriter();//make sure to import PrintWriter as well
				pw.println("<b><font color='red'>User already taken. Please Insert values again</font></b></br>");
				RequestDispatcher rd= request.getRequestDispatcher("updateMenu.jsp");
				rd.include(request, response);
			}
			else
				gameDB.stmt.execute("Update player set "+field+"='"+newValue+"' where playerid="+pid);
		}
		if(found==0) {
		gameDB.con.commit();
		response.sendRedirect("http://localhost:8080/gameDataBase/getPlayers");
		}
		}catch(SQLException e) {e.printStackTrace();}
	}

}
