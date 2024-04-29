import java.util.*;
import java.sql.*;
public class Admin {

	public static void AdminLogin() {
		Scanner input = new Scanner(System.in);
		System.out.println("please enter your selection");
		System.out.println("1 : Unapproved attractions");
		String selection = "";
		selection = input.nextLine();
		System.out.println();
		if(selection.equals("1"))
		{
		System.out.println();
		System.out.println("The Unapproved Attractions are listed below ");
		
			Admin.unapprovedattractions();
		}
		else
		{
			profile_creation.Login();
		}
		
	}
	public static void unapprovedattractions() {
		Scanner input = new Scanner(System.in);
		String attractionname = "";
		String status = "false";
		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
	
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			

			
	
			ArrayList<Attractions> sortedAttractions = new ArrayList<Attractions>();

//			int size = sortedAttractions.size();
			String selection = "";
			while(!selection.equals("x")) {
				rs = st.executeQuery("select * from attraction where status = 'false'");
				
				while(rs.next())
				{
					
					attractionname = rs.getString(1);
					if(rs.getString(5).equals(status))
					{
	            		sortedAttractions.add(new Attractions(rs.getString(1),
	                            rs.getString(2), rs.getString(3), 
	                            rs.getString(4),rs.getBoolean(5)));
	            	}
				}
				for(int i=0; i < sortedAttractions.size()  ; i++) {
					System.out.printf("%s: %s \n", i+1, sortedAttractions.get(i).getattractionname());
				}
				System.out.println("10: exit");
				System.out.println("Enter an option to view");
				selection = input.next();
				int sel = Integer.parseInt(selection) - 1;
				if(sel >= 0 && sel <= sortedAttractions.size()) {
					String option = "";
					if(!option.equals("10")) {
						System.out.printf("Name of Attraction: %s\nDescription: %s \nCity: %s\nTag: %s\n", sortedAttractions.get(sel).getattractionname(),sortedAttractions.get(sel).getdescription(),sortedAttractions.get(sel).getCity(),sortedAttractions.get(sel).getTag());
						System.out.println("Enter youy choice");
						System.out.println("1: To Approve Attractions");
						System.out.println("2: To Deny Attractions");
						System.out.println("x: exit");
						option = input.next();
						if(option.equals("1")) {
							Admin.approvattractions(sortedAttractions.get(sel).getattractionname());
							sortedAttractions.clear();
						}
						if(option.equals("2")) {
							Admin.denyattractions(sortedAttractions.get(sel).getattractionname());
							sortedAttractions.clear();
						}
					}
				}
				else
				{
					break;
				}
			}
	
		}
		catch(SQLException e)
		{
			System.out.println("viewing Attractions Failed");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
				rs.close();
				st.close();
				
			}
			
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public static void approvattractions(String attname) {
		String attractionname=attname;

		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			conn.setAutoCommit(false);
			int r = st.executeUpdate("UPDATE `attraction` SET `status`= 'true' WHERE Name ='" + attractionname + "'" );
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("Attraction  approved");
		}
		catch(SQLException e)
		{
			System.out.println("accepted/approved failed");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
				st.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void denyattractions(String attname) {
		String attractionname=attname;
		
		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			conn.setAutoCommit(false);
			int r = st.executeUpdate("DELETE FROM `attraction` WHERE Name =('" + attractionname + "')" );
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("Attraction denied");
		}
		catch(SQLException e)
		{
			System.out.println("Deny failed");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
				st.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	

}
