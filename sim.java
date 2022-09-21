import java.util.Arrays;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.HashMap;
import java.util.Random;
import java.lang.Math;
import java.util.HashMap; 
public class sim{       
    private void CreateFile() {
        try {
            File myObj = new File("results.txt");
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
            FileWriter myWriter = new FileWriter("results.txt");
            myWriter.write(results);
            myWriter.close();
        }catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private double[] MLE(int[] data, int size){
        double[] thetas= new double[6]; 
        for( int i=0;i<data.length;i++){   
            thetas[i] = (double) data[i]/(double) size;    // theta = num element/ total readings  
        }
        return thetas;
    }
    private double BayesRule(int[] data, int size, double theta, double pTheta){
        //calc bayes rule for given theta and associated probability
        double pDTheta = (Math.pow(theta,java.lang.Math.log( data[5])))*(Math.pow((1-theta),java.lang.Math.log((size-data[5])))); 
        double pThetaD =  pDTheta* pTheta; 
        if(size == 2000){
        return pThetaD; 
    }
    private double bayseianInference(int[]data , int size,HashMap<String,String> scenario ){
        //find max pThetaD from given thetas 
        sim mySim = new sim();
        double currResult;
        double bestTheta = 0;
        double highestpT = 0; 
        for(String key : scenario.keySet()){ 
            double theta = Double.parseDouble(key);
            double pTheta = Double.parseDouble(scenario.get(key));
            currResult= mySim.BayesRule(data,size,theta, pTheta);
            if(currResult>highestpT){
                highestpT = currResult; 
                bestTheta = theta; 
            }
        }
        return bestTheta;
    }
    public static void main(String[] args){
        String simRecords = ""; 
        sim mySim = new sim();
        dice test = new dice();
        mySim.CreateFile();
        HashMap<String,String> scenario2 = new HashMap<String,String>();
        HashMap<String,String> scenario3 = new HashMap<String,String>();
        HashMap<String,String> scenario4 = new HashMap<String,String>();
        scenario2.put(".2",".2");
        scenario2.put(".4",".2");
        scenario2.put(".6",".2");
        scenario2.put(".8",".2");
        scenario3.put(".5",".45"); // set scenario variables
        scenario3.put(".6",".35");
        scenario3.put(".16666", ".2");
        scenario4.put(".5",".52");
        scenario4.put(".6",".28");
        scenario4.put(".16666",".2");
        int[] dataCount = {20 , 200 , 2000};
        for(int num:dataCount){ //for each data set size
            int[] data = test.roll(num); //generate data 
            simRecords= simRecords + "\nData set " +String.valueOf(num)+": " + " one: " + String.valueOf(data[0]) + ", two: "+ String.valueOf(data[1]) + ", three: " + String.valueOf(data[2]) + ", four: " + String.valueOf(data[3])+ ", five: " + String.valueOf(data[4]) + ", six: " +String.valueOf(data[5])+"\n";    
            //perform each mL scenario 
            double[] test1= mySim.MLE(data,num);//scenario 1 
            simRecords = simRecords+ "Scenario 1: thetas: " +  Arrays.toString(test1) + "\n";
            double test2 = mySim.bayseianInference(data,num,scenario2);//scenario 2
            simRecords = simRecords+ "Scenario 2: Best theta: " +  String.valueOf(test2) + "\n";
            double test3 = mySim.bayseianInference(data,num,scenario3);  //scenario 3
            simRecords = simRecords+ "Scenario 3: Best theta: " +  String.valueOf(test3) + "\n";
            double test4 = mySim.bayseianInference(data,num, scenario4);//scenario 4
            simRecords = simRecords+ "Scenario 4: Best theta: " +  String.valueOf(test4) + "\n";  
        }
        mySim.writeFile(simRecords);        
}}
