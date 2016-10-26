/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.genetic.mutation.algorithm;

/**
 *
 * @author milesnecker
 */
public class firstGenerationGen {
    private String[] strs;
    private int population;
    private String goal;
    private int len;
    
    public firstGenerationGen(String g, int pop) {
        strs = new String[pop];
        goal = g;
        len = g.length();
        population = pop;
        initilizeArray();
        generateFirstStrings();
    }
    
    public firstGenerationGen() {
        
    }
    
    public String getGoal() {
        return goal;
    }
    
    private void initilizeArray() {
        for(int i = 0; i < population; i++) {
            strs[i] = "";
        }
    }
    
    private void generateFirstStrings() {
        for (int i = 0; i < population; i++) {
            for (int j = 0; j < len; j++) {
                strs[i] = strs[i] + pickRandomChar();
            }
        }
    }
    
    public char pickRandomChar() {
        String allChars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM ";
        int charNum = (int) (Math.random() * (allChars.length()));
        return allChars.charAt(charNum);
    }
    
    public String[] getStrs() {
        return strs;
    }
}
