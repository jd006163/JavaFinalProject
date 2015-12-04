import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Transactions {
	
	
	//READ STORED TEXT FILE THAT HAS BANK ACCOUNT INFO
	public static ArrayList<ArrayList<String>> readfile(String AccountDataFile)

	{
		//CREATE BANK ACCOUNT REGSTER ARRY
		ArrayList<ArrayList<String>> arrBankAcount=new ArrayList<ArrayList<String>>();
		try
		{
			//READ FILE
			Scanner scanner = new Scanner(new FileReader(AccountDataFile));
			//SET DELIMITER
			scanner.useDelimiter(",");
			
			//LOOP THROUGH FILE AND ADD TO ARRAY
			 while(scanner.hasNext())
	         {
	            String dataInRow=scanner.nextLine();
	            String []dataInRowArray=dataInRow.split(",");
	            ArrayList<String> rowDataFromFile=new ArrayList<String>(Arrays.asList(dataInRowArray));
	            arrBankAcount.add(rowDataFromFile);
	         }
	         scanner.close();
	     }
	     
			
			
		//CATCH EXCEPTIONS
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		//RETURN CREATED ARRY
		return arrBankAcount;
	}
	
	//PERFORM A WITHDRAW OR DEPOSIT ACTION
	public static void Action(String CurrentDate, float Amount, String TypeOfTransaction, String AccountDataFile) throws IOException
	{
		
		//CREATE LIST ARRAY TO STORE THE BANKACCOUNT DATA
		ArrayList<ArrayList<String>> AccountTransaction = new ArrayList<ArrayList<String>>();
		
		//GET THE BANK ACCOUNT DATA FROM STORED TEXT FILE
		AccountTransaction =Transactions.readfile(AccountDataFile);
		
		//GET BALANCE - LAST RECORD IN ARRAY
		float AccountBalance;
		AccountBalance = GetBalance(AccountTransaction);
		
		if(TypeOfTransaction == "Deposit")
			try {
				
					//INCREMENT BALANCE TO INCLUDE DEPOSITED AMOUNT
					float NewBalance = 	AccountBalance + Amount; 
					//CREATE DEPOSIT ARRAY LIST TO STORE THE DEPOSIT AMOUNT
					ArrayList<String> Deposit = new ArrayList<String>();
					//ADD THE APPROPRIATE FIELDS TO THE LEDGER
					Deposit.add(CurrentDate);
					Deposit.add("D");
					Deposit.add(Float.toString(Amount));
					Deposit.add(Float.toString(NewBalance));

					//ADD DEPOSIT ARRAY TO ACCOUNTTRANSACTION ARRAY LIST
					AccountTransaction.add(Deposit);
					//CREATE NEW FILE
					WriteFile.CreateNewFile(AccountTransaction, AccountDataFile);
					
					//FORMAT ACCOUNT BALANCE TO LOOK LIKE CURRENCY
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					String frmtBalance = formatter.format(NewBalance);
					
					//DISPLAY ACCOUNT BALANCE
					System.out.println("Your new account balance is: " + frmtBalance);
			}
		
		catch (Exception e) {
		
				e.printStackTrace();
			}

		else if (TypeOfTransaction == "Withdraw")
		{
			//DECREASE BALANCE TO INCLUDE WITHDRAWN AMOUNT.  A CHECK IS PERFORMED BEFORE GETTING HERE, SO WE SHOULD NEVER HIT NEGATIVE
			float NewBalance = 	AccountBalance - Amount; 
			ArrayList<String> Withdraw = new ArrayList<String>();
			
			Withdraw.add(CurrentDate);
			Withdraw.add("W");
			Withdraw.add(Float.toString(Amount));
			Withdraw.add(Float.toString(NewBalance));
			AccountTransaction.add(Withdraw);
			WriteFile.CreateNewFile(AccountTransaction, AccountDataFile);
			
		}
		
	}
	
	//GET BALANCE
	public static float GetBalance(ArrayList<ArrayList<String>> AccountBalance)
	{
		float balance = 0;
		//GET ACCOUNT BALANCE.  CHECK TO SEE IF ACCOUNTBALANCE ARRAY IS EMPTY
		if (AccountBalance != null && !AccountBalance.isEmpty())
		{
		
			//CREATE NEW ARRAY LIST TO STORE THE LAST ARRAY LIST IN THE ARRAY ACCOUNTBALANCE
			ArrayList<String> ArrayLastTransaction = AccountBalance.get(AccountBalance.size()-1);
		
			balance = Float.parseFloat(ArrayLastTransaction.get(ArrayLastTransaction.size()-1).toString());
		
		}
	else
	{
		System.out.println("There are no transactions in your account");
	}

	return balance;

	
}
}