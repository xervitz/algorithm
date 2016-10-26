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
public class mateAndMutate {
    private String[] strs;
    private int[] empties;
    private int pop, popDiv;
    private int[] parents;
    private int strLen;
    
    public mateAndMutate (int p, int l) {
        parents = new int[(p/5) * 2];
        strLen = l;
    }
    
    public void setVals(String[] s, int[] e) {
        strs = s;
        empties = e;
        pop = strs.length;
        popDiv = pop/5;
    }
    
    public String[] mate() {
        selectParents();
        sexytime();
        return strs;
    }
    
    private void selectParents() {
        int selected = 0;
        int num = 0;
    
        while(selected < parents.length) {
            num = (int) (Math.random() * pop);
            if(!isAnEmpty(num)) {
                parents[selected] = num;
                selected++;
            }
        }
    }
    
    private boolean isAnEmpty(int n) {
        for(int i = 0; i < popDiv; i++) {
            if(n == empties[i]) return true;
        }
        return false;
    }
    
    private void sexytime() {
        for(int i = 0; i < popDiv; i++) {
            double whichWay = Math.random();
           //System.out.println("There should be nothing after this:" + strs[empties[i]]);
            if (whichWay < .15) {
                strs[empties[i]] = swaps(strs[parents[i*2]], strs[parents[(i*2)+1]]);
            }
            else if(whichWay < .50) {
                strs[empties[i]] = ranChars(strs[parents[i*2]], strs[parents[(i*2)+1]]);
            }
            else {
                strs[empties[i]] = mutate(strs[parents[i*2]], strs[parents[(i*2)+1]]);
            }
            //System.out.println("There should be something after this:" + strs[empties[i]]);
        }
    }
    
    private String swaps(String str1, String str2) {
        String child = "";
        int spot = (int) (Math.random() * str1.length());
        int x = (int) (Math.random() * (2) + 1);
        
        if(x == 1) {
            child = str1.substring(0, spot) + str2.substring(spot);
        }
        else {
            child = str2.substring(0, spot) + str1.substring(spot);
        }
        
        return child;
    }
    
    private String ranChars(String str1, String str2) {
        String child = "";
        for(int i = 0; i < strLen; i++) {
            int x = (int) (Math.random() * 2 + 1);
            if(x == 1) {
                child = child + str1.charAt(i);
            }
            else {
                child = child + str2.charAt(i);
            }
        }
        
        return child;
    }
    
    private String mutate(String str1, String str2) {
        String child = "";
        String parent = "";
        int x = (int) (Math.random() * 2 + 1);
        firstGenerationGen charMake = new firstGenerationGen();
        
        if(x == 1) {
            parent = str1;
        }
        else {
            parent = str2;
        }
        
        for(int i = 0; i < strLen; i++) {
            //System.out.println("This is the goal".substring(0, strLen-1));
            x = (int) (Math.random() * 10 +1);
            if(x==1) {
                child = child + parent.charAt(i);
            }
            else {
                child = child + charMake.pickRandomChar();
            }
        }
        
        return child;
    }
}
