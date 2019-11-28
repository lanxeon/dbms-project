//select username,cname as 'class',wname as 'weapon',aname as 'armour' from player,class,weapons,armour where classid=cid and armourid=aid and weaponid=wid
//ignore. Above is only for future reference



//comment changes made by specific person so we can know what changes are made. Have fun everyone

import java.util.*;
import java.io.*;
import java.sql.*;

public class gameDB {

	//GLOBAL VARIABLES TO BE USED IN THE ENTIRE PROGRAM
	
	//jdbc variables
	static Connection con = null;
	static ResultSet rs=null;
	static Statement stmt=null;
	static PreparedStatement pstmt=null;
	
	//misc variables
	static int hp,mp,patk,matk,def,mob,rec;//stats
	static int pid,cid,wid,aid,lvl;//player, class, weapon, armour ids and level
	static String usn="";//player username

	//common queries which will be repeated during the entirety of the program
	static String insPlayer="insert into player(username,level,classid,weaponid,armourid) values (?,?,?,?,?)";
	static String insStats="update table stats set maxhp=maxhp+?,maxmp=maxmp+?,phys_attack=phys_attack+?,magic_attack=magic_attack+?,defence=defence+?,"
			+ "recovery=recovery+?,mobility=mobility+?";
	
	//cin is global input using object
	static Scanner cin=new Scanner(System.in);
	
	//END OF GLOBAL VARIABLES
	
	//MAIN FUNCTION WILL BE USED MAINLY FOR MENU TO LET PEOPLE CHOOSE WHAT THEY WANT TO DO. MAP FUNCTIONS FROM HERE
	//WE WILL HAVE 2 FUNCTIONS FOR EACH ARMOUR AND WEAPON(ie,WE WILL HAVE AROUND 48 FUNCTIONS. DW JUST HAVE TO COPY PASTE ONE OF THE FUNCTIONS
	//AND MAKE NECESSARY CHANGES. ONE FUNCTION FOR EQUIPPING WEAPON/ARMOUR AND ANOTHER FOR UNEQUIPPING IT. WE WILL HAVE UNIQUE STATS ADDED
	//FOR EACH ARMOUR/WEAPON(AND CLASS OBV). ALSO KEEP IN MIND CERTAIN ARMOUR AND WEAPONS CAN ONLY BE USED BY A SPECIFIC CLASS
	
	
	/*
	 * Class stats
	 * 
	 * Titan(cid 1): hp+100, mp+25, phyatk+70, magatk+10, def+85, recovery+20, mobility+15
	 * Gunslinger(cid 2): hp+60, mp+55, phyatk+110, magatk+10, def+50, recovery+40, mobility+50
	 * Assassin(cid 3): hp+45, mp+55, phyatk+55, magatk+45, def+30, recovery+70, mobility+100
	 * Mage(cid 4): hp+75, mp+95, phyatk+0, magatk+90, def+80, recovery+50, mobility+40
	 */
	
	/*
	 * Weapon Stats(Only deals with phyatk,magatk,sometimes mobility
	 * 
	 * 1.excalibur: patck+40, mp+15,movility+5
	 * 2.shadowsteel: patck+65,mp+5,mobility-5
	 * 3.battleaxe: patck+50,recovery+10
	 * 4.Duke mk: patk+30, mobility+5, mp+10
	 * 5.smg: patk+15,mobility+15
	 * 6.Shotgun: patk+40, mobility-5,mp+10
	 * 7.dagger:patk+25,magatk+15,mobility+15
	 * 8.blade:patk+15,magatk+25,mobility+15
	 * 9.HiddenBlade: patk+10, matk+10,mobility+25
	 * 10.wand:spatk+25, recovery+40, mobility+15
	 * 11.staff:spatk+45, recovery+10
	 * 12.spellcaster: spatk+35, mobility+30, recovery+10
	 */
	
	/*
	 * Armour Stats
	 * 
	1	transcendance: patk+20,def+15,recovery+20,sp+10
	2	iron will: patk+10,def+40, mobility-10,recovery+10,sp+20
	3	armamentarium: patk+15,def+15,mobility+5,recovery+5, sp+15
	4	hyperion:patk+30,matk+20, mobility+15,sp+15
	5	hallowfire:patk+20,mobility+15,recovery+20,sp+25
	6	actium:patk+10,mobility+10,recovery+10,def+15,sp+20
	7	shadow stepper:patk+15,matk+10,mobility+40,sp+20
	8	dreambane:patk+15,matk+10, recovery+40,sp+30
	9	ophidia spathe:def+15, recovery+25, mobility+25, sp+25
	10	chromatic fire:matk+35,sp+15 recovery+25
	11	phoenix protocol:matk+50, recovery+10,mobility-10
	12	Sanguine Alchemy:matk+20,recovery+15,sp+30,mobility+5
	 */
	
	
	public static void main(String[] args) 
	{
		
		try {
			
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","");
		
		stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		//Menu here to go to different functions
		
		insPlayer();
		
		}
		catch(ClassNotFoundException e) {e.printStackTrace();}
		catch(SQLException e) {e.printStackTrace();}
		
	}
	
	//Function for inserting a player. Also subsequently inserts a tuple into the stats table with pid same as playerid of the newly
	//inserted player.(Make sure you enter values just for checking insertion working then set autoincrement back to 1 for player table.
	public static void insPlayer() throws ClassNotFoundException,SQLException
	{
		usn = cin.nextLine();
		lvl = cin.nextInt();
		cid = cin.nextInt();
		wid = cin.nextInt();
		aid = cin.nextInt();
		
		pstmt=con.prepareStatement(insPlayer);
		pstmt.setString(1, usn);
		pstmt.setInt(2, lvl);
		pstmt.setInt(3, cid);
		pstmt.setInt(4, wid);
		pstmt.setInt(5, aid);
		pstmt.execute();
		
		rs=stmt.executeQuery("select playerid from player where playerid=(select max(playerid) from player)");
		rs.next();
		stmt.execute("insert into stats(pid) values ("+rs.getInt(1)+")");
	}

}
