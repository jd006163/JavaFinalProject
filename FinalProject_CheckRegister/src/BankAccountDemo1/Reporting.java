package BankAccountDemo1;
import java.io.File;
import java.io.FileFilter;


public class Reporting {


	//EXTRACTS CLIENT LIST
	public static File[] getClientList()
	{
		//SETUP DIRECTORY TO SEARCH
		   File dir = new File("Clients\\");
		   //ARRAY TO STORE THE DIRECTORY OF FOLDERS
		      File[] ClientList = dir.listFiles();
		      
		      FileFilter fileFilter = new FileFilter()
		      {
		    	  //CHECK TO SEE IF OBJECT FOUND IS A DIRECTORY
		    	  public boolean accept(File ClientList)
		    	  {
		    		  return ClientList.isDirectory();
		    	  }
		      };
		      //RESET CLIENT LIST TO THE FILTERED LIST
		      ClientList = dir.listFiles(fileFilter);

		//RETURN CLIENT LIST
		return ClientList;
	}
	
	//DISPLAYS TOTAL CLIENT ACCOUNT NUMBER LIST
	public static void displayClientList(File[] ClientList)
	{
		System.out.println("**** MMU Financial Services Client List ****\n");
		
		 //GET COUNT OF FOLDERS
	      System.out.println("Number of Accounts: "+ClientList.length);

	      //IF COUNT IS 0, THEN THERE ARE NOT ANY CUSTOMERS IN THE SYSTEM
	      if (ClientList.length == 0) {
	         System.out.println("Either dir does not exist or is not a directory");
	      }
	      
	      //LOOP AND PRINT OUT LISTING
	      else
	      	{
	    	  for (int i=0; i< ClientList.length; i++)
	    	  {
	    		  //GET FOLDER NAME
		            File Folder = ClientList[i];
		          //GET File Name
		           String FileName = Transactions.GetFileName(Folder);

		            File f = new File(FileName);
		          //Check to see if a File Exists
		            if(f.exists() && !f.isDirectory())
		            { 
		            	//DENOTE AS AN ACTIVE ACCOUNT
		            	System.out.println("Active Account: \t" + Folder.toString());
		            }
		            else 
		            {
		            	//DENOTE AS AN IN-ACTIVE ACCOUNT
		            	System.out.println("In-Active Account: \t" + Folder.toString());
		            }
	    	  }
	      	}
	
	}

	//GETS CURRENT BALANCE OF EVERY ACTIVE CUSTOMER.  ACTIVE IS DENOTED BY HAVING A TEXT FILE
	public static float getTotalClientBalance(File[] ClientList)
	{
		System.out.println("**** MMU Financial Services Total Number of Assets ****\n");
		
		float TotalBalance = 0;
		 //IF COUNT IS 0, THEN THERE ARE NOT ANY CUSTOMERS IN THE SYSTEM
	      if (ClientList.length == 0) {
	         System.out.println("Either dir does not exist or is not a directory");
	      }
	      
	      //LOOP AND PRINT OUT LISTING
	      else
	      	{
	    	  for (int i=0; i< ClientList.length; i++)
	    	  {
	    		  //GET FOLDER NAME
	            File Folder = ClientList[i];
	          //GET File Name
	           String FileName = Transactions.GetFileName(Folder);

	            File f = new File(FileName);
	            
	          //Check to see if File Exists
	            if(f.exists() && !f.isDirectory())
	            { 
	            	//IF EXISTS, GET BALANCE
	            	 TotalBalance += Transactions.GetBalance(Transactions.readfile(FileName));
	            	 //System.out.println(Folders.toString());
	            }
	            
	    	  }
	      	}
	      
	      return TotalBalance;
	}
	
	
}
