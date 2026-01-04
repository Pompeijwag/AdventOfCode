import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class App {
    public static List<Integer[]> lights = new ArrayList<>();
    public static List<List<List<Integer>>> buttons = new ArrayList<>();
    public static List<List<Integer>> volatges = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);


        while(scan.hasNext()){
            String[] str = scan.nextLine().split(" ");
            char[] light =  str[0].toCharArray();
            Integer[] lightfinal = new Integer[light.length - 2];
            for (int i = 1; i < light.length - 1; i++) {
                if(light[i] == '.'){
                    lightfinal[i - 1] = 0;
                }
                else{
                    lightfinal[i - 1] = 1;
                }
            }
            lights.add(lightfinal);
            List<List<Integer>> buttonsfinal = new ArrayList<>();
            for (int i = 1; i < str.length - 1; i++) {
                String[] button = str[i].substring(1, str[i].length() -1).split(",");
                List<Integer> activations = new ArrayList<>();
                for(String num : button){
                    activations.add(Integer.parseInt(num));
                }
                buttonsfinal.add(activations);
            }
            buttons.add(buttonsfinal);
        }
        for (Integer[] arr : lights) {
            System.out.println(Arrays.toString(arr));
        }

        System.err.println(buttons);
        // part1();
    }

    public static void part1(){
        int answer = 0;
        for (int i = 0; i < lights.size(); i++) {
            Queue<Integer[]> lightqueue = new ArrayDeque<>();
            Queue<List<List<Integer>>> buttonqueue = new ArrayDeque<>();
            Queue<Integer> iteration = new ArrayDeque<>();
            Integer[] startinglight = new Integer[lights.get(i).length];
            for (int ii = 0; ii < startinglight.length; ii++) {
                startinglight[ii] = 0;
            }
            lightqueue.add(startinglight);
            buttonqueue.add(buttons.get(i));
            iteration.add(0);

            while(!lightqueue.isEmpty()){
                Integer iter = iteration.poll();
                Integer[] currentlight = lightqueue.poll();
                currentlight = currentlight.clone();
                if(Arrays.equals(currentlight, lights.get(i))){
                    answer += iter;
                    break;
                }
                List<List<Integer>> currentbutton = new ArrayList<>();
                for (List<Integer> inner : buttonqueue.poll()) {
                    currentbutton.add(new ArrayList<>(inner));
                }
                for (int ii = 0; ii < currentbutton.size(); ii++) {
                    List<List<Integer>> nextbutton = new ArrayList<>();
                    for (int iii = 0; iii < currentbutton.size(); iii++) {
                        if(ii != iii){
                            nextbutton.add(new ArrayList<>(currentbutton.get(iii)));
                        }
                        else{
                            Integer[] nextlight = currentlight.clone();
                            for (Integer iv : currentbutton.get(iii)) {
                                if(nextlight[iv] == 0){
                                    nextlight[iv] = 1;
                                }  
                                else{
                                    nextlight[iv] = 0;
                                }
                            }
                            lightqueue.add(nextlight);
                        }
                    }
                    buttonqueue.add(nextbutton);
                    iteration.add(iter + 1);
                }


            }
        }
        System.err.println(answer);
    }

}


