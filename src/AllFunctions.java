import java.io.*;
import java.util.*;
import java.sql.*;

public class AllFunctions {
	
    String username;
    private String attname;

	public static void createattraction(String username)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter new Attraction");
		String attractionname = input.nextLine();
		System.out.println("Enter description of Attraction");
		String description = input.nextLine();
		System.out.println("Enter city of Attraction");
		String city = input.nextLine();
		System.out.println("Enter tag of Attraction");
		String tag = input.nextLine();
	//	System.out.println("Enter rating of Attraction");
	//	String rating = input.nextLine();
		boolean status = false;
		
		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			conn.setAutoCommit(false);
			int res = st.executeUpdate("insert into attraction values('" + attractionname + "', '" + description + "', '" + city + "', '" + tag + "', '" + status + "')" );
			conn.commit();
			conn.setAutoCommit(true);
			   System.out.println();
			System.out.println("Your attraction is created.");
			   System.out.println();
			   
			Userpage.Menu(username);
		}
		catch(SQLException e)
		{
			System.out.println("Attraction creation failed");
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
	
	public static void searchattraction(String username)
	{
		String attractionname="";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter city or tag to search Attraction like history,shopping,beach,nature,family");
		String search = input.nextLine();
		
		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			conn.setAutoCommit(false);
			rs = st.executeQuery("select * from attraction where status = 'true' AND tag = '" + search + "' OR status = 'true' AND city = '" + search + "' ");
			ArrayList<Attractions> sortedAttractions = new ArrayList<Attractions>();
			while(rs.next())
			{
				if(rs.getString(4).equals(search))
				{
            		sortedAttractions.add(new Attractions(rs.getString(1),
                            rs.getString(2), rs.getString(3), 
                            rs.getString(4),rs.getBoolean(5)));
            		
            	}
				if(rs.getString(3).equals(search))
				{
            		sortedAttractions.add(new Attractions(rs.getString(1),
                            rs.getString(2), rs.getString(3), 
                            rs.getString(4),rs.getBoolean(5)));

            	}
				
			}
			int size = sortedAttractions.size();
			String selection = "";
			while(!selection.equals("x")) {
				for(int i=0; i < sortedAttractions.size()  ; i++) {
					System.out.printf("%s: %s \n", i+1, sortedAttractions.get(i).getattractionname());
				}
				System.out.println("x: exit");
				System.out.println("Enter an option to view");
				selection = input.next();
				if(selection.equals("x")){
					Userpage.Menu(username);
				}
				else {
				int sel = Integer.parseInt(selection) - 1;
				if(sel >= 0 && sel <= size) {
					String option = "";
					attractionname = sortedAttractions.get(sel).getattractionname();
					if(!option.equals("x")) {
						System.out.printf("Name of Attraction: %s\nDescription: %s \nCity: %s\nTag: %s\n", sortedAttractions.get(sel).getattractionname(),sortedAttractions.get(sel).getdescription(),sortedAttractions.get(sel).getCity(),sortedAttractions.get(sel).getTag());
						System.out.println();
						System.out.println("Enter youy choice");
						System.out.println();
						System.out.println("1: To write a review or give score");
						System.out.println("2: Show rating and reviews");
						System.out.println("3: Add Attraction to favorites");
						System.out.println("4: Ask questions");
						System.out.println("5: Write answers");
						System.out.println("6: Show question answers");
						System.out.println("x: exit");
						option = input.next();	
						if(option.equals("1")) {
							Reviews.writereview(username, attractionname);
						}
						if(option.equals("2")) {
							Reviews.viewratingAndReviews(attractionname);
						}	
						if(option.equals("3")) {
							favouriteattraction(username,attractionname);
						}
						if(option.equals("4"))
						{
							AskQnA(username,attractionname);
						}
						if(option.equals("5"))
						{
							Answers(username, attractionname);
						}
						if(option.equals("6"))
						{
							ShowQuestions(username,attractionname);
						}
						if(option.equals("x")) {
							Userpage.Menu(username);
						}
					}
				}
				}
			}
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(SQLException e)
		{
			System.out.println("Attraction search Failed");
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
	
	public static void favouriteattraction(String username, String attractionname) {
		String attname = attractionname;
		
		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
	
			conn.setAutoCommit(false);
			
			int r = st.executeUpdate("insert into favourite values('" + username + "', '" + attname + "')" );
			
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("Added to favourites");
		}
		catch(SQLException e)
		{
			System.out.println("Adding to favourites failed");
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
	
	public static void viewfavattraction(String username)
	{
final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			conn.setAutoCommit(false);
			rs = st.executeQuery("select AttractionName from favourite where UserName = '" + username + "'");
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(SQLException e)
		{
			System.out.println("viewing favourite Attraction Failed");
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
	
    public static void YouMayLike(String username) {
		
		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			conn.setAutoCommit(false);
			rs = st.executeQuery("select Name from attraction where status = 'true' AND tag = (select tag from profile where UserName = '" + username + "') ");
			while(rs.next())
			{
				System.out.println(rs.getString(1));
				
			}
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(SQLException e)
		{
			System.out.println("viewing You May Like Failed");
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

	public static void AskQnA(String username,String attractionname)
	{
		Scanner input = new Scanner(System.in);
		
		String type = "q";
		String Username = username;
		
		
		String date = DateAndTime.DateTime();
		
		int id = 0;
		int quesID = 0;
		boolean notification = false;
		boolean noticheck = false;
		
		
		final String url = "jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		//ResultSet rs = null;
		
		try {
			
			conn = DriverManager.getConnection(url,"pendhotas3864","2203864");
			st = conn.createStatement();
			conn.setAutoCommit(false);
			
		
//			rs = st.executeQuery("select * from attraction where status = 'true'");
//			while(rs.next())
//				
//			{
//				System.out.println("Available attractions are " + rs.getString(1));
//			}
		
			System.out.println("enter the Question ");
			String content = input.nextLine();
			int r = st.executeUpdate("insert into quesans values('" + id + "','" + type + "','" + quesID+ "','" + Username + "','" + content +"','"  + attractionname + "','" + notification + "', '" + noticheck + "','" + date + "')");
			System.out.println();
			System.out.println("Question added");
			System.out.println();			
			conn.commit();
			conn.setAutoCommit(true);

			
		}
		catch(Exception e)
		{
			System.out.println("unable to write the question");
			e.printStackTrace();
		}
		finally
		{
			try {
			
			conn.close();
			st.close();
			//rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	
	}
	public static void Answers(String username,String attractionname) {
		
		Scanner input = new Scanner(System.in);
		
      final String url = "jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		int id = 0;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Statement st2 = null;
		ResultSet rs2 = null;
		
		
		try
		{
			conn = DriverManager.getConnection(url,"pendhotas3864","2203864");
			st = conn.createStatement();
			st2 = conn.createStatement();
			conn.setAutoCommit(false);

			
			
			
			rs2 = st2.executeQuery("select * from quesans where type = 'q'  and attracname = '" + attractionname + "'");
			if(rs2.next())
			{
				rs = st.executeQuery("select * from quesans where type = 'q'  and attracname = '" + attractionname + "'");
			System.out.println("The available questions to answer are");
			while(rs.next())
			{
				System.out.println("attraction name" + rs.getString(6));
				System.out.println("Question content " + rs.getString(5));
				System.out.println("Question ID " + rs.getInt(1));
			}
			 String type = "a";
			System.out.println("enter the question ID that you want to answer");
			String QuesID = input.nextLine();
		    System.out.println("enter the answer for the selected question");
		    String ans = input.nextLine();
		    
		    String date = DateAndTime.DateTime();
		    boolean notification = false;
			boolean noticheck = false;
			int z = st2.executeUpdate("update quesans set notification = 'true' WHERE quesID = '" + QuesID  + "' ");
		    int x= st.executeUpdate("insert into quesans values('" + id + "','" + type + "','" + QuesID+ "','" + username + "','" + ans +"','"  + attractionname+ "','" + notification + "', '" + noticheck + "','" + date + "')");
		    System.out.println();
		    System.out.println("answer posted");
		    System.out.println();
			}
			else
			{	
				System.out.println();
				System.out.println("you have no questions to answer");
				System.out.println();
			}
		    conn.commit();
			conn.setAutoCommit(true);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("answer was not posted");
		}
		finally
		{
			try
			{
				conn.close();
				st2.close();
				rs.close();
				st.close();
				rs2.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void ShowQuestions(String username,String attractionname)
	{
		final String url = "jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try
		{
		conn = DriverManager.getConnection(url,"pendhotas3864","2203864");
		st = conn.createStatement();
		conn.setAutoCommit(false);
		rs = st.executeQuery("select * from quesans where attracname = '" + attractionname +"'  order by quesID ");
		while(rs.next())
		{
			System.out.println();
			System.out.println("Question/Answer asked by User: "+rs.getString(4));
			System.out.println("Attraction name: "+rs.getString(6));
			System.out.println("Type: "+rs.getString(2));
			System.out.println("Content: "+rs.getString(5));
			System.out.println();
			
		}
		//Userpage.Menu(username);
		}
		catch(Exception e)
		{
			System.out.println("viewing question failed");
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
				st.close();
				rs.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
		}
	}
	
	public static void notification(String username)
	{
		
		Scanner input = new Scanner(System.in);
		String selection = "";
		
		
		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try 
		{
			int count = 0;
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			conn.setAutoCommit(false);
			rs = st.executeQuery("select * from quesans where username  = '" + username + "' and notification = 'true' and noticheck = 'false' and type = 'q'");
			while(rs.next())
			{
				if(rs.getString(7).equals("true") && rs.getString(8).equals("false") && rs.getString(2).equals("q")) {
					System.out.println("You have a notification to check");
					System.out.println("Do You want to check notification (Y/N)?");
					selection = input.nextLine(); 
					System.out.println();
					String attractionname = rs.getString("attracname");
					if(selection.equals("Y") || selection.equals("y"))
					{
						noticheck(username, attractionname);
						count++;
					}
					else if(selection.equals("N") || selection.equals("n")) {
						System.out.println("You have selected not check notification");
					}
				}
			
			}
			
			if(count == 0) 
			{
				System.out.println("You have no notifications");
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			Userpage.Menu(username);
		}
		
		catch(SQLException e)
		{
			System.out.println("viewing notifications Failed");
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

	public static void noticheck(String username, String attractionname) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("You have an ans for your qn");
		String Username = username;
		
		
		
		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		Statement st2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			st2 = conn.createStatement();
			conn.setAutoCommit(false);
			rs = st.executeQuery("select quesID,content,attracname from quesans where type = 'q' and username = '" + Username +"' and notification = 'true' and noticheck = 'false'");
			while(rs.next())
			{
				System.out.println("Your question Id is " + rs.getInt(1));
				System.out.println("your question is " +rs.getString(2));
				System.out.println("The attraction name on which question asked is "+rs.getString(3));
				
			}
			System.out.println();
			System.out.println("these are the questions that you asked for an attraction");
			System.out.println();
			System.out.println("Select the question ID from above");
			String QID = input.nextLine();
			
			rs2 = st2.executeQuery("select * from quesans where type = 'a'  and  questionID = '" + QID + "'");
			while(rs2.next())
			{
				if(rs2.getString(8).equals("false"))
				{
					String ans = rs2.getString(5);
					int id = rs2.getInt(3);
					String user = rs2.getString(4);
					
					System.out.println("Qid : " + id);
					System.out.println("Answers : " + ans);
					System.out.println("username : " + user);
					
					int z = st.executeUpdate("UPDATE quesans SET noticheck= 'true' WHERE quesID = '" + id +"' and type = 'q'");
				}
				else
				{
					System.out.println("You have no notifications for the given Question Id ");
				}
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			
			Userpage.Menu(username);
		}
		catch(SQLException e)
		{
			System.out.println("viewing notifications Failed");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
				st.close();
				st2.close();
				rs.close();
				rs2.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
