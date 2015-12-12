package BankAccountDemo1;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {

	
	public static void getMenu() //throws IOException
	{		
		Scanner console = new Scanner(System.in);
		int Selection;
		
		System.out.println("****** Welcome to MMU Financial Services ******");
		System.out.println();
		System.out.println("Please select the action you want to perform: ");
		System.out.println("Option 1: Create a New Account");
		System.out.println("Option 2: View Account Balance");
		System.out.println("Option 3: Make a Deposit");
		System.out.println("Option 4: Make a Withdraw");
		System.out.println("Option 5: Client Reporting");
		System.out.println("Option 6: MMU Student Financial");
		
		Selection = console.nextInt();
		
		//CHECK TO ENSURE VALID SELECTION IS PICKED
		if (Selection <= 0 || Selection > 6)
		{
			System.out.println("Please select a valid option!");
			getMenu();
		}
		else
		{
			//CREATE NEW ACCOUNT
			if(Selection == 1)
			{
				System.out.println("****** MMU Financial Services - New Client Creation ******");
				System.out.println();
				//GET CURRENT DATE TO BE USED TO STORE THE TRANSACTION
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				//FORMAT DATE APPROPRIATELY
				String CurrentDate = dateFormat.format(date);
				
				//GENERATE ACCOUNT NUMBER
				Random rnd = new Random();
				int randnum = 100000 + rnd.nextInt(900000);
				String AccountNo = Integer.toString(randnum);
				
				//Variable to store amount to be deposited
				float DepositAmount; 
				
				//Type of Transaction
				String TypeOfTransaction = "Create";
				System.out.println("Your account number is: " + AccountNo + " .  Please store this in a safe place!");
				System.out.println("Please enter deposit amount: ");
				
				//GET DEPOSIT AMOUNT From Customer
				DepositAmount = console.nextFloat();

				Transactions.Action(CurrentDate, TypeOfTransaction, DepositAmount, AccountNo);
				//Prompt to perform a transaction
				getMenu(console, "Main");
			}
			//View Account Balance
			else if(Selection == 2)
			{
				System.out.println("****** MMU Financial Services - Client Balance ******");
				System.out.println();
				System.out.println("Please enter in your Account Number:");
				
				String AccountNo = console.next();
				
				File FileName = new File(Transactions.GetFileName(AccountNo));
				
				if(FileName.exists())
				{
				
				//CREATE LIST ARRAY TO STORE THE BANKACCOUNT DATA
				ArrayList<ArrayList<String>> AccountRegister = new ArrayList<ArrayList<String>>();
				
				//GET THE BANK ACCOUNT DATA FROM STORED TEXT FILE
				AccountRegister=Transactions.readfile(Transactions.GetFileName(AccountNo));

				//REVERSING TP DISPLAY NEWEST TRANSACTION FIRST
				Collections.reverse(AccountRegister);
				
				//PRINT TERMINOLOGY
				System.out.println("**** MMU Financial Services - Account Transactions ****");
				System.out.println("W = Withdraw; D = Deposit\n");
				
				//Inject header into first element
				String[] HeaderColumns = { "Date\t", "Trx", "Amount", "Balance" };
				ArrayList<String> HeaderRow = new ArrayList<String>(Arrays.asList(HeaderColumns));
				AccountRegister.add(0,HeaderRow);
				
				//LOOPING THROUGH THE ARRAY TO GET THE LINE ITEMS
				    for(ArrayList<String> rowInFile:AccountRegister)
				    {
				    	//Format the string
						String formattedString = rowInFile.toString()
								
							    .replace(",", "\t\t")  //remove the commas
							    .replace("[", "")  //remove the right bracket
							    .replace("]", "")  //remove the left bracket
							    .trim(); 

						//PRINTOUT LINE ITEM
				        System.out.println(formattedString);
				    }
				   
					//Prompt to perform a transaction
					getMenu(console, "Main");
				}
				else
				{
					System.out.println("ERROR: The Account Number you entered: " + AccountNo + " is not valid");//ex.printStackTrace();
					getMenu(console, "Main");
				}
				
			}
			//DEPOSIT SECTION
			else if(Selection == 3)
			{
				System.out.println("****** MMU Financial Services - Client Deposit ******");
				System.out.println();
				System.out.println("Please enter in your Account Number:");
				//PROMPT FOR ACCOUNT NUMBER
				String AccountNo = console.next();
				
				File FileName = new File(Transactions.GetFileName(AccountNo));
				if(FileName.exists())
				{
				
				//GET CURRENT DATE TO BE USED TO STORE THE TRANSACTION
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				//FORMAT DATE APPROPRIATELY
				String CurrentDate = dateFormat.format(date);
				
				//Variable to store amount to be deposited
				float DepositAmount; 
				
				//Type of Transaction
				String TypeOfTransaction = "Deposit";
				System.out.println("Please enter deposit amount: ");
				
				//GET DEPOSIT AMOUNT From Customer
				DepositAmount = console.nextFloat();

				//COMMIT TRANSACTION
				Transactions.Action(CurrentDate, DepositAmount, TypeOfTransaction, Transactions.GetFileName(AccountNo));
				
				//Prompt to perform a transaction
				getMenu(console, "Main");
				}
				else
				{
					System.out.println("ERROR: The Account Number you entered: " + AccountNo + " is not valid");//ex.printStackTrace();
					getMenu(console, "Main");
				}
				
			}
			//WITHDRAW SECTION
			else if(Selection == 4)
			{
				System.out.println("****** MMU Financial Services - Client Withdraw ******");
				System.out.println();
				System.out.println("Please enter in your Account Number:");
				
				//PROMPT FOR ACCOUNT NUMBER
				String AccountNo = console.next();
				
				//GET CURRENT DATE TO BE USED TO STORE THE TRANSACTION
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				//FORMAT DATE APPROPRIATELY
				String CurrentDate = dateFormat.format(date);
				
				//CREATE LIST ARRAY TO STORE THE BANKACCOUNT DATA
				ArrayList<ArrayList<String>> AccountTransaction = new ArrayList<ArrayList<String>>();
				
				//GET THE BANK ACCOUNT DATA FROM STORED TEXT FILE
				AccountTransaction =Transactions.readfile(Transactions.GetFileName(AccountNo));
				
				//CHECK TO SEE IF FILE EXISTS
				if(AccountTransaction.isEmpty())
				{
					System.out.println("Please verify the Account Information and try again");
					//Prompt to perform a transaction
					getMenu(console, "Main");
				}
				else
				{
				
				//GET BALANCE - LAST RECORD IN ARRAY
				float AccountBalance;
				AccountBalance = Transactions.GetBalance(AccountTransaction);
				
				//FORMAT ACCOUNT BALANCE TO LOOK LIKE CURRENCY
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				String frmtBalance = formatter.format(AccountBalance);
				
				//VARIABLE TO STORE THE AMOUNT TO BE WITHDRAWN
				float WithdrawAmount; 
				
				//TYPE OF TRANSACTION
				String TypeOfTransaction = "Withdraw";
				
				//DISPLAY ACCOUNT BALANCE AND PROMPT FOR AMOUNT TO WITHDRAW
				System.out.println("Your Account Balance is: "+ frmtBalance);
				System.out.println("Please enter an amount you wish to withdraw: ");
				
				//GET WITHDRAW AMOUNT FROM CUSTOMER
				WithdrawAmount = console.nextFloat();
				
				//IF WITHDRAW AMOUNT IS LESS THEN OR EQUAL TO YOUR BALANCE, COMMIT THE TRANSACTION
				if(WithdrawAmount <= AccountBalance )
				{
					
				//COMMIT TRANSACTION
				Transactions.Action(CurrentDate, WithdrawAmount, TypeOfTransaction, Transactions.GetFileName(AccountNo));
				
				}
				//IF WITHDRAW AMOUNT IS GREATER THAN ACCOUNT BALANCE, PROMPT USER
				else
				{
					System.out.println("The amount you are wanting to withdraw is more than your available balance of " + frmtBalance);
					System.out.println("Please withdraw an amount of: " + frmtBalance + " or less.");
					getMenu(console, "Main");
				}
				
				//Prompt to perform a transaction
				getMenu(console, "Main");
				}

			}
			//CLIENT REPORTING
			else if(Selection == 5)
			{
				
				ReportingMenu();
				
				
			}
			//gabby's project
			else if(Selection == 6)
			{
				
				String[] args = null;
				BankAccountDemo2.BankDemo.main(args);
			}
			
		}
		
		console.close();
		
	}

	//DISPLAYS MENU AFTER AN ACTION HAS BEEN PERFORMED
	public static void getMenu(Scanner console, String Menu) //throws IOException
	{
		//PROMPT FOR ANOTHER TRANSACTION
		 System.out.println("\nWould you like to perform another transaction? (Y/N)");
		 //GET ANSWER
		    String answer = console.next();

		    //IF Y OR YES, GET THE MENU
		    if(answer.toLowerCase().contains("y") || answer.toLowerCase().contains("yes"))
		    {
		    	if(Menu == "Main")
		    	{
		    	getMenu();
		    	}
		    	else if (Menu == "Reporting")
		    	{
		    		MainMenu.ReportingMenu();
		    	}
		    }
		    //ELSE EXIT THE PROGRAM
		    else
		    {
		    	System.out.println("Thank you for banking with us");
		    }
	}
	
	//REPORTING MENU
	public static void ReportingMenu()
	{
		Scanner console = new Scanner(System.in);
		int Selection;
		
		System.out.println("****** MMU Financial Services - Client Reporting ******");
		System.out.println();
		System.out.println("Please select the action you want to perform: ");
		System.out.println("Option 1: View Client List");
		System.out.println("Option 2: View Total Client Balance");
		System.out.println("Option 3: Main Menu");
		
		Selection = console.nextInt();
		
		//CHECK TO ENSURE VALID SELECTION IS PICKED
		if (Selection <= 0 || Selection > 3)
		{
			System.out.println("Please select a valid option!");
			ReportingMenu();
		}
		else
		{
			//DISPLAY CLIENT LISTING
			if(Selection == 1)
			{
				Reporting.displayClientList(Reporting.getClientList());
				
				getMenu(console, "Reporting");
			}
			//GET TOTAL CLIENT BALANCES
			else if (Selection == 2)
			{
				Float TotalBalance = Reporting.getTotalClientBalance(Reporting.getClientList());
				
				//FORMAT ACCOUNT BALANCE TO LOOK LIKE CURRENCY
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				String frmtBalance = formatter.format(TotalBalance);
				
				System.out.println("Total Asset Balance is:");
				System.out.println(frmtBalance);

				getMenu(console, "Reporting");
			}
			//GO BACK TO MAIN MENU			
			else if(Selection == 3)
			{

				getMenu();
			}
			
		}
		console.close();
	}
}
