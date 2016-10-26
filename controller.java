/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.genetic.mutation.algorithm;

import java.util.Scanner;

/**
 *
 * @author milesnecker
 */
public class controller {
    private String[] strs;
    private int pop;
    private String goal;
    
    public controller(String g, int p) {
        goal = g;
        pop = p;
        
        int genCount = 1;
        int flag = 0;
        String input = "";
        Scanner in = new Scanner(System.in);
        
        firstGenerationGen first = new firstGenerationGen(goal, pop);
        strs = first.getStrs();
        printStrs();
        survivalOfTheFittest survive = new survivalOfTheFittest(strs, goal);
        mateAndMutate mate = new mateAndMutate(pop, goal.length());
        
        while(!(input.toLowerCase().equals("end"))) {
            for(int i = 0; i < 1000; i++) {
                survive.runSurvival();
                mate.setVals(strs, survive.getTheWeak());
                strs = mate.mate();
                survive.setVals(strs);
                genCount++;
                for(int j = 0; j < pop; j++) {
                        if(strs[j].equals(goal)) flag = 1;
                }
                if(genCount%1000 == 0) {
                    //printStrs();
                }
                if (flag == 1) break;
            }
            if (flag == 1) break;
        }
        printStrs();
        System.out.println("Found the string: \"" + goal + "\" in " +genCount+ " generations.");
    }
    
    private void printStrs() {
        for(int i = 0; i < pop; i++) {
            System.out.println(strs[i]);
        }
    }
    
    private void checkForEmpty() {
        for(int i = 0; i < pop; i++) {
            if(strs[i].isEmpty()) {
                System.out.println("THERE ARE EMPTY STRINGS! THESE SHOULD BE GONE!");
            }
        }
    }
}
