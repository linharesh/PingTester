/**
 * Scheduling Tasks Simulator Developers: Henrique Linhares, Raphael
 * Quintanilha, Fabrizio Moura and Diogo Souza.
 *
 * Universidade Federal Fluminense
 *
 * https://github.com/linharesh/SchedulingTaskSimulator
 *
 * Please check the software documentation for more information.
 */
package pingtester;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class responsible for saving the tasks info into report output file.
 *
 */
public class OutputFileWriter {

    /**
     * The name of the output file. Eg: otuput.txt
     */
    private static final String outputFileName = "output.txt";

    /**
     * A representation of the output file
     */
    private static final File outputFile = new File(OutputFileWriter.outputFileName);

    /**
     * Instance of FileWriter
     */
    private static FileWriter filewriter;

    /**
     * Instance of BufferedWriter
     */
    private static BufferedWriter bwriter;

    /**@deprecated 
     * Method responsible for creating the file output of text and open it for
     * writing data
     *
     */
    public static void openFile() {
        try {
            // if file doesnt exists, then create it
            if (!OutputFileWriter.outputFile.exists()) {
                OutputFileWriter.outputFile.createNewFile();
            }
            OutputFileWriter.filewriter = new FileWriter(OutputFileWriter.outputFile.getAbsoluteFile());
            OutputFileWriter.bwriter = new BufferedWriter(OutputFileWriter.filewriter);
        } catch (IOException ex) {

        }
    }

    /**@deprecated 
     * 
     * @param S 
     */
    public static void writeInOutputFile(String S) {
        try {
            openFile();

            OutputFileWriter.bwriter.newLine();
            OutputFileWriter.bwriter.write(S);
            OutputFileWriter.bwriter.newLine();
            closeFile();
        } catch (IOException ex) {

        }
    }

    
    public static void writeInLog(String S) {
        File log = new File("log.txt");

        try {
            if (!log.exists()) {
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(log, true);

            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.newLine();
                bufferedWriter.write(S);
                bufferedWriter.newLine();
            }

            System.out.println("Done");
        } catch (IOException e) {
            System.out.println("COULD NOT LOG!!");
        }
    }

    /**@deprecated
     * 
     */
    public static void closeFile() {
        try {
            OutputFileWriter.bwriter.close();
        } catch (IOException ex) {

        }
    }
}
