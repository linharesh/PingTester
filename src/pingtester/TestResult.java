/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingtester;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *"
 * @author HenriqueLinhares
 */
public class TestResult {
    private int sucessRate;
    private int failRate;
    private int totalOfTests;

    public String toString(){
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String S = GetRate()+" of sucess rate at test done at time: "+ sdf.format(cal.getTime()) 
            + " # " + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH);
    return S;
    }
    
    public double GetRate(){
       // return "Success: "+getSucessRate() + " Failures: " + getFailRate() + "total: "+ getTotalOfTests();
        double suc, total, rate;
        suc = sucessRate;
        total = totalOfTests;
        rate = suc / total;
        rate = rate*100;
        return rate;
        
    }
    
    public TestResult() {
        this.failRate=0;
        this.sucessRate=0;
        this.totalOfTests=0;
    }

    public void incrementFailRate(){
    this.failRate = this.failRate + 1 ;
    }
    
    public void incrementSucessRate(){
    this.sucessRate = this.sucessRate + 1 ;
    }
    
    
    
    public int getSucessRate() {
        return sucessRate;
    }

    public void setSucessRate(int sucessRate) {
        this.sucessRate = sucessRate;
    }

    public int getFailRate() {
        return failRate;
    }

    public void setFailRate(int failRate) {
        this.failRate = failRate;
    }

    public int getTotalOfTests() {
        return totalOfTests;
    }

    public void setTotalOfTests(int totalOfTests) {
        this.totalOfTests = totalOfTests;
    }


    
    
    
}
