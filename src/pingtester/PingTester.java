/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingtester;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HenriqueLinhares
 */
public class PingTester extends TimerTask {

    private boolean testWillContinue;

    public boolean getTestWillContinue() {
        return testWillContinue;
    }

    public void setTestWillContinue(boolean testWillContinue) {
        this.testWillContinue = testWillContinue;
    }

    public void startTest() throws IOException, InterruptedException {
        setTestWillContinue(true);
        Timer timer = new Timer();
        
        timer.scheduleAtFixedRate(this, 0, 1000);
    }

    @Override
    public void run() {
        try {
            System.out.println(completeHostlistTest().toString());
        } catch (IOException ex) {
            Logger.getLogger(PingTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finishTest() {
        setTestWillContinue(false);
    }

    public static TestResult completeHostlistTest() throws IOException {

        TestResult returningTestResult = new TestResult();

        String[] hostList = {"http://www.google.com", "https://www.youtube.com", "http://www.apple.com",
            "https://www.netflix.com/br/", "https://twitter.com", "https://www.wikipedia.org", "http://www.tempo.uff.br"};

        returningTestResult.setTotalOfTests(hostList.length);

        for (int i = 0; i < hostList.length; i++) {
            String URL = hostList[i];
            boolean status = singularURLTest(URL);
            if (status == true) {
                returningTestResult.incrementSucessRate();
            } else {
                returningTestResult.incrementFailRate();
            }

            // System.out.println(URL + "\t\tStatus:" + status);
        }

        return returningTestResult;
    }

    public static boolean singularURLTest(String url) throws IOException {

        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}
