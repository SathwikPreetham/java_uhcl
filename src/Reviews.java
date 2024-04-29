import java.sql.*;
import java.util.Scanner;

public class Reviews {
	public static void writereview(String username, String attname) {
		String attractionname = attname;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter score of Attraction");
		String score = input.nextLine();
		System.out.println("Enter comment of Attraction");
		String comment = input.nextLine();
		String time = DateAndTime.DateTime();
		
		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			conn.setAutoCommit(false);
			Float score1 = Float.parseFloat(score);
			if(score1 >= 1 && score1 <= 5) {
			int r = st.executeUpdate("insert into reviews values('" + username + "', '" + attractionname + "','" + comment + "','" + score + "','" + time + "')" );
			}
			else {
				System.out.println("Score should be ranged between 1 and 5");
			}
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("Review received");
		}
		catch(SQLException e)
		{
			System.out.println("Review failed");
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
	
	public static void viewratingAndReviews(String attname) {
		String attractionname = attname;
		int count = 0;
		
		
		final String url ="jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		Statement st2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try 
		{
			conn = DriverManager.getConnection(url, "pendhotas3864", "2203864");
			st = conn.createStatement();
			st2=conn.createStatement();
			conn.setAutoCommit(false);
			rs = st.executeQuery("select AVG(Rating) from reviews where AttractionName = '" + attractionname + "'");
			rs1 = st2.executeQuery("select * from reviews where AttractionName = '" + attractionname + "'");
			while(rs.next())
			{
				System.out.println("Attraction name is "+attractionname);
				System.out.println("Overall Rating of  Attraction: "+rs.getString(1));	
				System.out.println();
				count++;
			}
			while(rs1.next())
			{
				System.out.println("individual rating: "+rs1.getString(4));
				System.out.println("Comment: "+rs1.getString(3));
				System.out.println("Time Rated: "+rs1.getString(5));	
				System.out.println();
			}
			if(count == 0){
				System.out.printf("Attraction %s has no reviews ",attractionname);
				System.out.println();
			}
			
			
			System.out.println();
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(SQLException e)
		{
			System.out.println("viewing reviews Failed");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
				st.close();
				st2.close();
				rs1.close();
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
