import java.sql.*;
import java.util.Scanner;

public class profile_creation {
	
	public static void createAccount()
	{
		Scanner input = new Scanner(System.in);
		
		String id;
		String pswd;
		do {
			System.out.println("please enter the username in 3 to 10 digits");
			id = input.nextLine();
			if(id.length() >= 3 && id.length() <= 10)
			{
				boolean let = false;
				boolean dig = false;
				
				for(char ch : id.toCharArray())
				{
					if(Character.isLetter(ch))
					{
						let = true;
					}
					else if(Character.isDigit(ch))
					{
						dig = true;
					}
				}
				if(let && dig)
				{
					break;
				}
			}
			System.out.println("invalid user id");
			
		} while(true);
		
		do {
			System.out.println("Enter the password");
			pswd = input.nextLine();
			
			if(!pswd.equals(id))
			{
				break;
			}
			System.out.println("Invaid passowrd");
			
		}while(true);
		
		System.out.println("Please enter the tags like History,Shopping Fanatic,BeachLover,Nature lover,Family Vactioner: ");
		String tag = input.nextLine();
		
		System.out.println("Please enter your name: ");
		String name = input.nextLine();
		System.out.println("Please enter your email ");
		String mail = input.nextLine();
		
		//database url
		final String url = "jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		//ResultSet rs = null;
		
		try 
		{
			// connecting to database
			conn = DriverManager.getConnection(url,"pendhotas3864","2203864");
			// create a statement
			st = conn.createStatement();
			
			String s = DateAndTime.DateTime() + "User Created Successfully";
		
			conn.setAutoCommit(false);

			int r  = st.executeUpdate("insert into profile values ('" + id + "','" +
			pswd + "','" + s + "','" + tag + "','" + name + "','" + mail + "')");
			
			conn.commit();// execute the database operations at once
			conn.setAutoCommit(true);
			   System.out.println();
			System.out.println("****** The new user Created! *******");
			   System.out.println();
		}
		catch(SQLException e)
		{
			System.out.println("Account creation failed");
			e.printStackTrace(); // print the exception information
		}
		finally
		{
			// close the database
			try
			{
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
	
	public static void Login()
	{
		Scanner input = new Scanner(System.in);
		String id;
		String pswd;
		System.out.println("enter the login id");
		id = input.nextLine();
		System.out.println("enter the password");
		pswd = input.nextLine();
		
		final String url = "jdbc:mysql://cobmysql.uhcl.edu/pendhotas3864?useSSL=false";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		
		try
		{
			conn = DriverManager.getConnection(url,"pendhotas3864","2203864");
			st = conn.createStatement();
			conn.setAutoCommit(false);
			rs = st.executeQuery("select password from profile where UserName = '" + id +"'");
			if(rs.next())
			{
				String psd = "" + rs.getString(1);
				if(pswd.equals(psd))
				{
					
					if(id.equals("admin"))
					{
						Admin.AdminLogin();
						
					}
					else
					{
						Userpage.Menu(id);
					}
				}
				else
				{
					System.out.println("unable to login,please try again");
				}
			}
			conn.commit();
			conn.setAutoCommit(true);
			
		}
		catch(SQLException e)
		{
			System.out.println("Account Login error");
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
	
}
