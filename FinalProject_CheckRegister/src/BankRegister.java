import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class BankRegister {

	public static void main(String[] args) throws IOException {
		System.out.println("****** Banking Menu ******");
getMenu();
	}
	
	
	public static void getMenu() throws IOException
	{
		//HARDCODING LOCATION OF THE FILE.  THIS IS BEING STORED IN THE ROOT OF THE PROJECT FOLDER
		String AccountDataFile = "finalproject-start2.txt";
		
		Scanner console = new Scanner(System.in);
		int Selection;
		
		System.out.println();
		System.out.println("Please select the action you want to perform: ");
		System.out.println("Option 1: View Account Balance");
		System.out.println("Option 2: Make a Deposit");
		System.out.println("Option 3: Make a Withdraw");
		System.out.println("Option 4: Create New Account");
		
		Selection = console.nextInt();
		
		if (Selection <= 0 || Selection > 4)
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
				AccountRegister=Transactions.readfile(AccountDataFile);

				
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
				    System.out.println("Would you like to make a transaction? (Y/N)");
				    String answer = console.next();
				    if(answer.contains("y") || answer.contains("Yes"))
				    {
				    	getMenu();
				    }
				    else
				    {
				    	System.out.println("Thank you for banking with us");
				    }
				    
			}
			//DEPOSIT SECTION
			else if(Selection == 2)
			{
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
				Transactions.Action(CurrentDate, DepositAmount, TypeOfTransaction, AccountDataFile);
				
			}
			//WITHDRAW SECTION
			else if(Selection == 3)
			{

				//GET CURRENT DATE TO BE USED TO STORE THE TRANSACTION
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				String CurrentDate = dateFormat.format(date);
				
				//CREATE LIST ARRAY TO STORE THE BANKACCOUNT DATA
				ArrayList<ArrayList<String>> AccountTransaction = new ArrayList<ArrayList<String>>();
				
				//GET THE BANK ACCOUNT DATA FROM STORED TEXT FILE
				AccountTransaction =Transactions.readfile(AccountDataFile);
				
				//GET BALANCE - LAST RECORD IN ARRAY
				float AccountBalance;
				AccountBalance = Transactions.GetBalance(AccountTransaction);
				
				//FORMAT ACCOUNT BALANCE TO LOOK LIKE CURRENCY
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				String frmtBalance = formatter.format(AccountBalance);
				
				//Variable to store amount to be deposited
				float WithdrawAmount; 
				
				//Type of Transaction
				String TypeOfTransaction = "Withdraw";
				System.out.println("Your Account Balance is: "+ frmtBalance);
				System.out.println("Please enter an amount you wish to withdraw: ");
				
				//GET DEPOSIT AMOUNT From Customer
				WithdrawAmount = console.nextFloat();
				
				//IF WITHDRAW AMOUNT IS LESS THEN OR EQUAL TO YOUR BALANCE, COMMIT THE TRANSACTION
				if(WithdrawAmount <= AccountBalance )
				{
				//COMMIT TRANSACTION
				Transactions.Action(CurrentDate, WithdrawAmount, TypeOfTransaction, AccountDataFile);
				}
				else
				{
					System.out.println("The Amount you are wanting to withdraw is more than your available balance: " + AccountBalance);
					System.out.println("Please withdraw an amount of: " + frmtBalance + "or less.");
					BankRegister.getMenu();
				}
				
			}
			//create new account
			if(Selection == 4)
			{
				String[] args = null;
				 BankAccountDemo2.BankDemo.main(args);
			}
			
		}
		console.close();
		
	}

}
