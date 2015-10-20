import java.util.*;
public class BankRegister {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("****** Banking Menu ******");
getMenu();
	}
	
	
	public static void getMenu()
	{
		Scanner console = new Scanner(System.in);
		int Selection;
		
		System.out.println();
		System.out.println("Please select the action you want to perform: ");
		System.out.println("Option 1: View Account Balance");
		System.out.println("Option 2: Make a Deposit");
		System.out.println("OPtion 3: Make a Withdrawl");
		
		Selection = console.nextInt();
		
		if (Selection <= 0 || Selection > 3)
		{
			System.out.println("Please select a valid option!");
			getMenu();
		}
		else
		{
			if(Selection == 1)
			{
				GetData.getAccountBalance();
			}
			
		}
		console.close();
		
	}

}
