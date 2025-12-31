import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    
    public static List<String[]> positions = new ArrayList<>();
    public static List<List<String>> grid = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);


        while(scan.hasNext()){
            positions.add(scan.nextLine().split(","));
        }
        part1();
    }

    public static Double[][] range = new Double[][] {{0.0,0.0},{0.0,0.0}};
    public static void part1(){
        double answer = 0;
        for (int i = 0; i < positions.size(); i++) {
            for (int ii = i + 1; ii < positions.size(); ii++) {
                double onex = Double.parseDouble(positions.get(i)[0]);
                double twox = Double.parseDouble(positions.get(ii)[0]);
                double oney = Double.parseDouble(positions.get(i)[1]);
                double twoy = Double.parseDouble(positions.get(ii)[1]);
                double area = (Math.abs(onex - twox) + 1) * (Math.abs(oney - twoy) + 1);
                boolean breaking = false;

                if(onex <= twox){
                    range[0][0] = onex;
                    range[0][1] = twox;
                }
                else{
                    range[0][0] = twox;
                    range[0][1] = onex;
                }
                if(oney <= twoy){
                    range[1][0] = oney;
                    range[1][1] = twoy;
                }
                else{
                    range[1][0] = twoy;
                    range[1][1] = oney;
                }
                
                // System.err.println("Case: " + Arrays.deepToString(range));
                // System.out.println("");
                for (int iii = 0; iii < positions.size(); iii++) {
                    String[] start = positions.get(iii);
                    String[] end = positions.get((iii + 1) % positions.size());
                    Double[][] line;
                    if(start[0].equals(end[0])){
                        if(Double.parseDouble(start[1]) < Double.parseDouble(end[1])){
                            line = new Double[][]{{Double.parseDouble(start[0])}, {Double.parseDouble(start[1]), Double.parseDouble(end[1])}};
                        }
                        else{
                            line = new Double[][]{{Double.parseDouble(start[0])}, {Double.parseDouble(end[1]), Double.parseDouble(start[1])}};
                        }
                    }
                    else{
                        if(Double.parseDouble(start[0]) < Double.parseDouble(end[0])){
                            line = new Double[][]{{Double.parseDouble(start[0]), Double.parseDouble(end[0])}, {Double.parseDouble(start[1])}};
                        }
                        else{
                            line = new Double[][]{{Double.parseDouble(end[0]), Double.parseDouble(start[0])}, {Double.parseDouble(start[1])}};
                        }
                    }
                    // System.err.println(" testing: " +  Arrays.deepToString(line));
                        if(intercept(line)){
                            breaking = true;
                            // System.err.println("Break!");
                            break;
                        }
                }
                
                if(answer < area && !breaking){
                    answer = area;
                    // System.err.println("new answer: " + answer);
                }
            }
            
        }
        System.out.println(answer);
    }

    public static boolean intercept(Double[][] b){
        if(b[0].length == 1){
            if(b[0][0] > range[0][0] && b[0][0] < range[0][1]){
                if(b[1][0] < range[1][1]){
                    if(b[1][0] > range[1][0]){
                        return true;
                    }
                    else if(b[1][1] > range[1][0]){
                        return true;
                    }
                }
            }
        }
        else{
            if(b[1][0] > range[1][0] && b[1][0] < range[1][1]){
                if(b[0][0] < range[0][1]){
                    if(b[0][0] > range[0][0]){
                        return true;
                    }
                    else if(b[0][1] > range[0][0]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
