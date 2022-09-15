import java.util.Random;

public class dice{
    Random roll = new Random();
    int[] rollTracker = new int[6];

    public int roll(int rolls){
        int result;
        for(int i=1;i<=rolls;i++){
            result = roll.nextInt(10);
            if(result<=5){
                rollTracker[result-1]++;
            }
            else{
                rollTracker[5]++;
            }
        }
        return rollTracker; 
    }
 }