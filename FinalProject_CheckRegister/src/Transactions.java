import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Transactions {
	
	
	/*
	 * READ BANK ACCOUNT FILE.  TODO: PASS IN INPUT FROM CONSOLE
	 */
	public static ArrayList<ArrayList<String>> readfile()

	{
		//CREATE BANK ACCOUNT REGSTER ARRY
		ArrayList<ArrayList<String>> arrBankAcount=new ArrayList<ArrayList<String>>();
		try
		{
			//READ FILE
			Scanner scanner = new Scanner(new FileReader("c:\\finalproject-start2.txt"));
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
	
	public static ArrayList<ArrayList<String>> Action(String CurrentDate, float Amount, String TypeOfTransaction) throws IOException
	{
		
		//CREATE LIST ARRAY TO STORE THE BANKACCOUNT DATA
		ArrayList<ArrayList<String>> AccountTransaction = new ArrayList<ArrayList<String>>();
		
		//GET THE BANK ACCOUNT DATA FROM STORED TEXT FILE
		AccountTransaction =Transactions.readfile();
		
		//GET BALANCE - LAST RECORD IN ARRAY
		float AccountBalance;
		AccountBalance = GetBalance(AccountTransaction);
		
		if(TypeOfTransaction == "Deposit")
			try {
				{
					//INCREMENT BALANCE TO INCLUDE DEPOSITED AMOUNT
					float NewBalance = 	AccountBalance + Amount; 
					ArrayList<String> Deposit = new ArrayList<String>();
					Deposit.add(CurrentDate);
					Deposit.add("D");
					Deposit.add(Float.toString(Amount));
					Deposit.add(Float.toString(NewBalance));
					AccountTransaction.add(Deposit);
					WriteFile.CreateNewFile(AccountTransaction);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if (TypeOfTransaction == "Withdraw")
		{
			//withdraw funds from account
		}
		
		return AccountTransaction;
	}



public static float GetBalance(ArrayList<ArrayList<String>> AccountBalance)
{
	float balance = 0;
	//GET ACCOUNT BALANCE.  CHECK TO SEE IF ACCOUNTBALANCE ARRAY IS EMPTY
	if (AccountBalance != null && !AccountBalance.isEmpty()) {
		
		//CREATE NEW ARRAY LIST TO STORE THE LAST ARRAY LIST IN THE ARRAY ACCOUNTBALANCE
		ArrayList<String> ArrayLastTransaction = AccountBalance.get(AccountBalance.size()-1);
		
		balance = Float.parseFloat(ArrayLastTransaction.get(ArrayLastTransaction.size()-1).toString());
		
		 //System.out.println(AccountBalance.get(AccountBalance.size() - 1));
		}
	else
	{
		System.out.println("There are no transactions in your account");
	}

	return balance;

	
}
}