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
public class survivalOfTheFittest {
    private String[] strs;
    private int[] strVals;
    private int pop, popDiv;
    private String goal;
    private int[] theWeak;
    
    public survivalOfTheFittest(String[] s, String g) {
        setVals(s);
        goal = g;
        strVals = new int[pop];
    }
    
    public void setVals(String[] s) {
        strs = s;
        pop = strs.length;
        popDiv = pop/5;
    }
    
    public int[] getTheWeak() {
        return theWeak;
    }
    
    public void runSurvival() {
        resetStringVals();
        assignStringVals();
        killOffTheWeak();
    }
    
    private void resetStringVals() {
        for(int i = 0; i < pop; i++) {
            strVals[i] = 0;
        }
    }
    
    private void assignStringVals() {
        for(int i = 0; i < pop; i++) {
            //System.out.println(strs[i]);
            for(int j = 0; j < goal.length(); j++) {
                strVals[i] += getCharDifference(strs[i].charAt(j), goal.charAt(j));
            }
        }
    }
    
    private int getCharDifference(char a, char b) {
        return Math.abs(a-b);
    }
    
    private void killOffTheWeak() {
        theWeak = new int[popDiv];
        int killed = 0;
        
        for(int i = 0; i < pop; i++) {
            if (i < popDiv) {
                theWeak[i] = i;
            }
            else {
                for(int j = 0; j < popDiv; j++) {
                    if(strVals[theWeak[j]] < strVals[i]) {
                        theWeak[j] = i;
                        break;
                    }
                }
            }
        }
        
        int randomDeath = (int) (Math.random() * 15 + 1);
        
        if (randomDeath == 2) {
            while (killed < popDiv) {
                int kill = (int) (Math.random() * pop);
                if(!strs[kill].isEmpty()) {
                    strs[kill] = "";
                    theWeak[killed] = kill;
                    killed++;
                }
            }
        }
                
        for(int i = 0; i < popDiv; i++) {
            strs[theWeak[i]] = "";
        }
    }
}
