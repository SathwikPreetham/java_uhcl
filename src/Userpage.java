import java.util.*;
public class Userpage {
		String attracname;
		public static void Menu(String id)
		{
			String username = id;
			Scanner input = new Scanner(System.in);
			String selection = "";
			
			System.out.println();
			System.out.println("Two highest scored attractions 'You May Like' based on your tag");
			AllFunctions.YouMayLike(username);
			System.out.println();
			System.out.println("Please make your selection: ");
			System.out.println("1: Create Attraction");
			System.out.println("2: Search Attraction");
			System.out.println("3: View favorite Attractions");

			System.out.println("4: View Notifications");
			System.out.println("x: Finish");
			
			selection = input.nextLine(); 
			System.out.println();
			
			if(selection.equals("1"))
			{
				AllFunctions.createattraction(username);
			}
			else if(selection.equals("2"))
			{
				AllFunctions.searchattraction(username);
			}
			else if(selection.equals("3"))
			{
				AllFunctions.viewfavattraction(username);
			}
		
			else if(selection.equals("4"))
			{
				AllFunctions.notification(username);
			}
			else if(selection.equals("x"))
			{
				System.out.println("Finish");
			}	
		}
}
