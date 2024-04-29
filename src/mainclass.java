import java.util.Scanner;

public class mainclass {

	
	public static void main(String[] args)
	{
		
			Scanner input = new Scanner(System.in);
			String selection = "";
			
			while(!selection.equals("x"))
			{
				System.out.println();
				System.out.println("welcome to the trip advisor");
				System.out.println();
				System.out.println("1: enter 1 if you want to register");
				System.out.println("2: enter 2 if you want to login to an account");
				System.out.println("x: Quit");
				selection = input.nextLine();
				
				
				System.out.println();
				
				if(selection.equals("1"))
				{
					profile_creation.createAccount();
				}
				else if(selection.equals("2"))
				{
					profile_creation.Login();
				}
				
			
		}

	}
}
