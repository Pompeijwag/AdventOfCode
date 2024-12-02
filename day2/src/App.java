import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);
        int answer = 0;
        while(scan.hasNextLine())
        {
            String[] str = scan.nextLine().split(" ");
            int[] array = new int[str.length];
            for(int i = 0; i < str.length; i++){
                array[i] = Integer.parseInt(str[i]);
            }
            int increased = answer;
            int[] smallerarray = new int[array.length - 1];
            for(int p = 0; p < array.length; p++)
            {
                if(answer > increased){break;}
                int index = 0;
                for(int i = 0; i < array.length; i++){
                    if(i != p){
                        smallerarray[index] = array[i];
                        index++;
                    }
                }
                int inc = 0;
                if(smallerarray[0] > smallerarray[1]){inc = 1;}
                else{inc = -1;}
                for(int i = 0; i < smallerarray.length - 1; i++){
                    int diff = inc * smallerarray[i] - inc * smallerarray[i + 1];
                    if(!(diff >= 1 && diff <= 3)){break;}
                    if(i == smallerarray.length - 2){answer++;}
                }
            }
            
        }
        System.out.println(answer);
    }
    //this is so disgusting
}
