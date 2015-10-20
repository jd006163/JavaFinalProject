import java.io.*;
import java.util.*;
public class GetData {

	
	public static void getAccountBalance()
	{

		
		//compile into an array to pass to write file class to right a file
		String FileName = "c:\\finalproject-start.txt";
		
		try
		{
		FileReader FileReader = new FileReader(FileName);
		BufferedReader reader = new BufferedReader(FileReader);

		//print out results
		displayAccountBalance(reader, FileName);
		reader.close();
		FileReader.close();
		}
		
		//catch exceptions
		catch(FileNotFoundException ex)
		{
			System.out.println(
	                "Unable to open file '" + 
	                		FileName + "'");
			
		}
		
		catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + FileName + "'");   

        }
		
		
	}
	
	public static void displayAccountBalance(BufferedReader reader, String FileName)
	{
		System.out.println("****** Account Balance ******");
		
		System.out.println();
		
		try
		{
		while (true)
		{
			String lineItem = reader.readLine();
			if(lineItem == null)
			{
				break;
			}
			System.out.println(lineItem);
		}
		
		}
		
	
		//catch exceptions
				catch(FileNotFoundException ex)
				{
					System.out.println(
			                "Unable to open file '" + 
			                		FileName + "'");
				}
				
				catch(IOException ex) {
		            System.out.println(
		                "Error reading file '" 
		                + FileName + "'");                  
		            // Or we could just do this: 
		            // ex.printStackTrace();
		        }
		
		System.out.println();
		System.out.println("*****************************");
		
		
		//provide a menu to start over
			BankRegister.getMenu();
	}

	

}
