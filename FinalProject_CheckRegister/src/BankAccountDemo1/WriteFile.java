package BankAccountDemo1;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
public class WriteFile {

	public static void CreateNewFile(ArrayList<ArrayList<String>> Account, String AccountDataFile) throws IOException
	{
		//convert accountdatafile to path object
		Path p = Paths.get(AccountDataFile);
		
		//get file name within the path object
		String FileName = p.getFileName().toString();
		
		//get folder name from accountdatafile string by removing the file name
		String FolderName = AccountDataFile.replace(FileName, "");
		
		//check to see if the client directory exists.  if it does not, create it
		if(Files.isDirectory(Paths.get(FolderName)) == false)
		{
			File Folder = new File(FolderName);
			Folder.mkdirs();
			
		}
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
