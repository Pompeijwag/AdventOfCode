import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    public static List<String[]> positions = new ArrayList<>();
    public static List<Double[]> edges = new ArrayList<>();
    public static List<List<Integer>> groups = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File file = new File("..\\lib\\input.txt");
        Scanner scan = new Scanner(file);

        while(scan.hasNext()){
            positions.add(scan.nextLine().split(","));
        }

        for (int i = 0; i < positions.size(); i++){
            for (int ii = i; ii < positions.size(); ii++) {
                if(i != ii){
                    double distance = Math.pow(Double.parseDouble(positions.get(i)[0]) - Double.parseDouble(positions.get(ii)[0]), 2);
                    distance += Math.pow(Double.parseDouble(positions.get(i)[1]) - Double.parseDouble(positions.get(ii)[1]), 2);
                    distance += Math.pow(Double.parseDouble(positions.get(i)[2]) - Double.parseDouble(positions.get(ii)[2]), 2);
                    distance = Math.sqrt(distance);
                    Double[] newedge = {(double)i, (double)ii, distance};
                    edges.add(newedge);
                }
            }
            groups.add(new ArrayList<>(Arrays.asList(i)));
        }

        edges.sort(Comparator.comparing(row -> row[2]));

        // for (Double[] arr : edges) {
        //     System.out.println(Arrays.toString(arr));
        // }
        
        part1();
        part2();
    }

    public static void part1(){
        for (int i = 0; i < 1000; i++) {
            int first = find(edges.get(i)[0].intValue());
            int second = find(edges.get(i)[1].intValue());
            if(first != second){
                for (Integer a : groups.get(second)){
                    groups.get(first).add(a);
                }
                groups.get(second).clear(); 
            }
        }

        List<Integer> sizes = new ArrayList<>();
        for (List<Integer> arr : groups){
            sizes.add(arr.size());
        }
        Collections.sort(sizes, Comparator.reverseOrder());
        System.err.println(sizes);
        System.err.println(sizes.get(0) * sizes.get(1) * sizes.get(2));
    }

    public static void part2(){
        for (int i = 1000; i < edges.size(); i++) {
            int first = find(edges.get(i)[0].intValue());
            int second = find(edges.get(i)[1].intValue());
            if(first != second){
                for (Integer a : groups.get(second)){
                    groups.get(first).add(a);
                }
                groups.get(second).clear(); 
                if(groups.get(first).size() >= positions.size()){
                    
                    List<Integer> sizes = new ArrayList<>();
                    for (List<Integer> arr : groups){
                        sizes.add(arr.size());
                    }
                    System.err.println(sizes);
                    System.err.println(Arrays.toString(edges.get(i)));
                    System.err.println(first);
                    System.err.println(edges.get(i)[1].intValue());
                    System.err.println(Double.parseDouble(positions.get(first)[0]) * Double.parseDouble(positions.get(edges.get(i)[1].intValue())[0]));
                    break;
                }
            }
        }
    }

    public static int find(int index){
        for(List<Integer> arr : groups){
            if(arr.contains(index)){
                return groups.indexOf(arr);
            }
        }
        return 0;
    }
}
