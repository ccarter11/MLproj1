import java.util.Arrays;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.HashMap;

public class sim{
    private int[] results;
    
    private void CreateFile() {
  
        try {
            File myObj = new File("simulatedData.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    private void writeFile(String results){
          try{
            FileWriter myWriter = new FileWriter("simulatedData.txt");
            myWriter.write(results);
            myWriter.close();
        }catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        sim mySim = new sim();
        mySim.CreateFile();
        String simRecords = ""; 
        int[] dataCount = {20 , 200 , 2000};
        dice test = new dice();

        for(int num:dataCount){
            int[] results = test.roll(num);
            simRecords =simRecords + Arrays.toString(results);  
            System.out.println(Arrays.toString(results));
        }

        mySim.writeFile(simRecords);
        
}}
