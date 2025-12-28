import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static List<String> grid = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);

        while(scan.hasNext()){
            grid.add(scan.nextLine());
        }


        //part1();
        part2();
    }

    public static void part1(){
        List<Integer> beams = new ArrayList<>();
        int answer = 0;
        beams.add(grid.get(0).indexOf("S"));
        for (int i = 1; i < grid.size(); i++) {
            
            List<Integer> newbeams = new ArrayList<>();
            String line = grid.get(i);
            for(int beam : beams){
                if(line.charAt(beam) == '^'){
                    if(!newbeams.contains(beam - 1)){
                        newbeams.add(beam - 1);
                    }
                    if(!newbeams.contains(beam + 1)){
                        newbeams.add(beam + 1);
                    }
                    answer++;
                }
                else{
                    if(!newbeams.contains(beam)){
                        newbeams.add(beam);
                    }
                }
            }
            beams = newbeams;
            System.err.println(beams);

        }
        System.err.println(answer);
    }

    public static void part2(){
        Map<Integer, Double> beams = new HashMap<>();
        double answer = 1;
        beams.put(grid.get(0).indexOf("S"), 1.0);
        for (int i = 1; i < grid.size(); i++) {
            
            Map<Integer, Double> newbeams = new HashMap<>();
            String line = grid.get(i);
            for(int beam : beams.keySet()){
                if(line.charAt(beam) == '^'){
                    newbeams.put(beam - 1, newbeams.getOrDefault(beam - 1, 0.0) + beams.get(beam));
                    newbeams.put(beam + 1, newbeams.getOrDefault(beam + 1, 0.0) + beams.get(beam));
                    answer += beams.get(beam);
                }
                else{
                    newbeams.put(beam, newbeams.getOrDefault(beam, 0.0) + beams.get(beam));
                }
            }
            beams = newbeams;
            // System.err.println(beams);

        }
        System.err.println(answer);
    }
}
