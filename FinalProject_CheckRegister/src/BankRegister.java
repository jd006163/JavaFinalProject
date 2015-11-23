import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class BankRegister {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("****** Banking Menu ******");
getMenu();
	}
	
	
	public static void getMenu() throws IOException
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
				//CREATE LIST ARRAY TO STORE THE BANKACCOUNT DATA
				ArrayList<ArrayList<String>> AccountRegister = new ArrayList<ArrayList<String>>();
				
				//GET THE BANK ACCOUNT DATA FROM STORED TEXT FILE
				AccountRegister=Transactions.readfile();

				
				    for(ArrayList<String> rowInFile:AccountRegister)
				    {
				    	//Format the string
						String formattedString = rowInFile.toString()
								
							    .replace(",", "")  //remove the commas
							    .replace("[", "")  //remove the right bracket
							    .replace("]", "")  //remove the left bracket
							    .trim(); 
						
				        System.out.println(formattedString);
				    }

			}
			else if(Selection == 2)
			{
				//ITEMS TO DO:
				/*
				 * READ EXISTING TEXT FILE THAT HAS EXISTING BANK ACCOUNT INFO
				 * ADD ITEM TO LIST ARRAY
				 * OVERWRITE EXISTING TEXT FILE WITH UPDATED TEXT FILE
				 * 
				 */
				//GET CURRENT DATE TO BE USED TO STORE THE TRANSACTION
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				String CurrentDate = dateFormat.format(date);
				
				//Variable to store amount to be deposited
				float DepositAmount; 
				
				//Type of Transaction
				String TypeOfTransaction = "Deposit";
				System.out.println("Please enter deposit amount: ");
				
				//GET DEPOSIT AMOUNT From Customer
				DepositAmount = console.nextFloat();

				//COMMIT TRANSACTION
				Transactions.Action(CurrentDate, DepositAmount, TypeOfTransaction);
				
				//ADD DEPOSIT AMOUNT TO ARRAY
				
			}
			
		}
		console.close();
		
	}

}
