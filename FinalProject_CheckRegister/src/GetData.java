import java.io.*;
import java.util.*;
import java.util.ArrayList;
public class GetData {

	/*
	 * READ BANK ACCOUNT FILE.  TODO: PASS IN INPUT FROM CONSOLE
	 */
	public static ArrayList<String> readfile()
	{
		//OPEN A READER
		BufferedReader buffer = null;
		int LineNumber = 0;
		String LineItem = null;
		//CREATE BANK ACCOUNT REGSTER ARRY
		ArrayList<String> arrBankAcount = new ArrayList<String>();
		
		try
		{
			
			String line = null;
			
			//READ FILE
			buffer = new BufferedReader(new FileReader("c:\\finalproject-start2.txt"));
			
			while ((line = buffer.readLine()) != null)
			{
				//CONVERT DATA TO AN ARRY AND CAST TO A STRING
				LineItem = fileToArray(line).toString();
				//ADD LINE ITEM TO LIST ARRAY
				arrBankAcount.add(LineNumber,LineItem);
				//INCREASE LINENUMBER
				LineNumber++;
				
			}
		}
		//CATCH EXCEPTIONS
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			//DISPOSE BUFFER
			try
			{
				if (buffer != null) buffer.close();
			}
			//CATCH EXCEPTIONS FROM CLOSING THE BUFFER
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		//RETURN CREATED ARRY
		return (arrBankAcount);
	}
	
	/*
	 * Convert CSV File to an Array and return the array.
	 * Params: String
	 */
	public static ArrayList<String> fileToArray(String strcsv)
	{
		
		//CREATE ARRAY LIST VARIABLE
		ArrayList<String> arrList = new ArrayList<String>();
		if (strcsv != null)
		{
			//SPLIT FILE ON DELIMETER
			String[] splitData = strcsv.split("\\s*,\\s*");
		
			for (int i = 0; i < splitData.length; i++)
			{
				//CHECK TO ENSURE THERE ISN'T ANY INVALID DATA
				if (!(splitData[i] == null) || !(splitData[i].length() == 0 ))
				{
					//ADD DATA TO THE ARRAY LIST
					arrList.add(splitData[i].trim());
					//return (splitData[i]);
					//arrList.add();
				}
			}
		}

		//RETURN ARRAY
		return arrList;
		
	}
	}
