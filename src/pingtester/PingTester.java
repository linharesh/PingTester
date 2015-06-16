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

    private boolean isRunning;
    private Timer timer;

    public PingTester(){
        OutputFileWriter.openFile();
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean testWillContinue) {
        this.isRunning = testWillContinue;
    }

    public void startTest() throws IOException, InterruptedException {
        if (isRunning == false) {
            isRunning = true;
            timer = new Timer();
            timer.scheduleAtFixedRate(this, 1000, 900000); //900000 - 15 min
        }

    }

    @Override
    public void run() {
        System.out.println("começou a rodar");
        try {
            String S = completeHostlistTest().toString();
            System.out.println(S);
            OutputFileWriter.writeInOutputFile(S);
        } catch (IOException ex) {
            Logger.getLogger(PingTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finishTest() {
        this.isRunning = false;
        timer.cancel();
        OutputFileWriter.closeFile();
        System.exit(0);
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

             System.out.println(URL + "\t\tStatus:" + status);
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
