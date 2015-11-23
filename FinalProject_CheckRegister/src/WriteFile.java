import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteFile {

	public static void CreateNewFile(ArrayList<ArrayList<String>> Account) throws IOException
	{
		
		 String fileString = "c:\\test\\CDDatabase.txt";
         File file01 = new File(fileString);

         try
         {
              FileOutputStream fileOutputStream = new FileOutputStream(file01);
              ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
              objectOutputStream.writeObject(Account); //now the whole ArrayList
              //you can now just use an ObjectInputStream to deserialize the whole
              //ArrayList later

              //You also better flush() the ObjectOutputStream. Otherwise not all of the
              //object will be serialized (the stream keeps a buffer and only writes
              //occassionally for efficiency reasons
              objectOutputStream.close();
         }
         catch(FileNotFoundException e)
         {
              e.printStackTrace();
         }
	}
	//create method to compile a list of line items and either add/substract from your overall balance.
	//the data needs to be saved to an array and sorty by the date column
	
	/*
	 String[] temp;
String line;
String delims = ",";
int rowCounter = 0;
while ((line = input.readLine())!= null) {
    temp = line.split(delims);  // Moved inside the loop.
    for(int i = 0; i<6; i++){
    X[rowCounter][i] = Double.parseDouble(temp[i]);
} 
	 
	 */
}
