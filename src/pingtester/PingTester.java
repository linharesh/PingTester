/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingtester;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author HenriqueLinhares
 */
public class PingTester {

//    public static void main(String args[]) throws Exception {
//        
//        System.out.println(completeHostlistTest().toString());
//        TimeUnit.SECONDS.sleep(5);
//        System.out.println(completeHostlistTest().toString());
//        
//    }
    private boolean testWillContinue;

    public boolean getTestWillContinue() {
        return testWillContinue;
    }

    public void setTestWillContinue(boolean testWillContinue) {
        this.testWillContinue = testWillContinue;
    }

    public void startTest() throws IOException, InterruptedException {
        setTestWillContinue(true);

        while (getTestWillContinue()) {
            System.out.println(completeHostlistTest().toString());
            try {
                Thread.sleep(1000);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
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
