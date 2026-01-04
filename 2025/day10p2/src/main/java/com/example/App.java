package com.example;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.ejml.simple.SimpleMatrix;

public class App {
    public static List<Integer[]> lights = new ArrayList<>();
    public static List<List<List<Integer>>> buttons = new ArrayList<>();
    public static List<SimpleMatrix> voltages = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        InputStream in = App.class.getResourceAsStream("/input.txt");
        Scanner scan = new Scanner(in);


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
            String[] volt = str[str.length - 1].substring(1, str[str.length - 1].length() -1).split(",");

            SimpleMatrix col = new SimpleMatrix(volt.length, 1);
            for (int i = 0; i < volt.length; i++) {
                col.set(i, 0, Double.parseDouble(volt[i]));
            }

            voltages.add(col);

        }
        for (Integer[] arr : lights) {
            System.out.println(Arrays.toString(arr));
        }

        // System.err.println(buttons);
        
        System.err.println(voltages);
        // part1();
        part2();
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

    public static void part2(){
        int answer = 0;
        for (int i = 0; i < lights.size(); i++) {
            List<List<Integer>> but = buttons.get(i);
            int rows = lights.get(i).length;
            int columns = but.size();
            double[][] matrix = new double[rows][columns];
            for (int ii = 0; ii < columns; ii++) {
                List<Integer> currentcolumn = but.get(ii);
                for (int iii = 0; iii < rows; iii++) {
                    if(currentcolumn.contains(iii)){
                        matrix[iii][ii] = 1;
                    }
                    else{
                        matrix[iii][ii] = 0;
                    }
                }
            }
            SimpleMatrix A = new SimpleMatrix(matrix);
            
            System.out.println(A);
            System.out.println(A.pseudoInverse());


            SimpleMatrix x = A.pseudoInverse().mult(voltages.get(i));
            System.out.println(x);
        }
    }

}


