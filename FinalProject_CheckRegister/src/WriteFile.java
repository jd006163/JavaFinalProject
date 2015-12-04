import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
public class WriteFile {

	public static void CreateNewFile(ArrayList<ArrayList<String>> Account, String AccountDataFile) throws IOException
	{
		FileWriter wr = new FileWriter(AccountDataFile);
		
         try
         {
 //loop through the array list of arrays and write to the file
        	 for (ArrayList<String> lineItem : Account)
              {
        		 //get size of the array to know when to insert the delimter
        		 int lastitem = lineItem.size();
        		 //item counter used to know when you hit the end of the array
        		 int itemcounter = 0;
        		 
        		 //get the values of the given array list
        		for (String value: lineItem)
        		{
        			//increase the counter
        		 itemcounter++;
        		 //write the value to the text file
        		 wr.write(value) ;
        		 //insert delimiter if you are not on the last item in the array
        		 if(itemcounter != lastitem)
        		 {
        		 wr.write(",");
        		 }
        		 
        		}
        		//create line seperator
        		 wr.write(System.lineSeparator());
  
              }
            
         }
         catch(Exception e)
         {
              e.printStackTrace();
         }
         finally {
        	//close the connection
        	 wr.close();
         }
	}

}
