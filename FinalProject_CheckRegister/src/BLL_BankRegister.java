import 
java.io.*;
import java.util.*;
import java.util.ArrayList;
public class BLL_BankRegister {

	
	/*
	 * PRINT OUT ARRAY RESULTS
	 */
	public static void printAccountBalance(ArrayList<String> AccountRegister){
		StringBuilder sb = new StringBuilder();
		
		for(String s : AccountRegister)
		{
			sb.append(AccountRegister).append(s) + "\n";
		}
		
		for(String s : AccountRegister)
		
	//	String result = "Date|Type of Action|Amount|Balance\n";
		
		//for (int i = 0; i < AccountRegister.size(); i++)
		//{

			//result += String.format("%2d: ",  i + 1) + " " + (AccountRegister.get(i)) + "\n";
			
				System.out.print(sb);
	
		//}
//System.out.print(result);
	
	}
	
}
