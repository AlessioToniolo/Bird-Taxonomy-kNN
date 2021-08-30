import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

// Example for Reading from File
// T will store number of test cases

public class MyProgram
{
    public static String INPUT_FILE_NAME = "Prob20.in.txt";

    public static void main(String[] args) {
        try {
            // prepare to read the file
            File inFile = new File(INPUT_FILE_NAME);
            FileReader fr = new FileReader(inFile);
            BufferedReader br = new BufferedReader(fr);
            String inLine = null;

            // get the number of test cases
            int T = Integer.parseInt(br.readLine());

            // loop through test cases
            for (int i = 0; i < T; i++) {
                ArrayList<Bird> birds = new ArrayList<Bird>();
                ArrayList<Bird> unknowns = new ArrayList<Bird>();
                BirdWatching grader = new BirdWatching(birds, unknowns);
                // Get the Line
                inLine = br.readLine();
                String[] birdNums = inLine.split(" ");
                for (int b = 0; b<Integer.parseInt(birdNums[0]); b++) {
                    inLine = br.readLine();
                    String[] newLine = inLine.split(" ");
                    birds.add(new Bird(newLine[0], Double.parseDouble(newLine[1]), Double.parseDouble(newLine[2]), Double.parseDouble(newLine[3]), Double.parseDouble(newLine[4])));
                }
                for (int z = 0; z<Integer.parseInt(birdNums[1]); z++) {
                    inLine = br.readLine();
                    String[] newLine = inLine.split(" ");
                    unknowns.add(new Bird("Unknown", Double.parseDouble(newLine[0]), Double.parseDouble(newLine[1]), Double.parseDouble(newLine[2]), Double.parseDouble(newLine[3])));
                }
                for (int l = 0; l<Integer.parseInt(birdNums[1]); l++) {
                    int k = 5;
                    String decision = grader.voter(unknowns.get(l), k);
                    while (decision.equals("Tie")) {
                        k++;
                        decision = grader.voter(unknowns.get(l), k);
                    }
                    System.out.println(decision);
                }
            }

            // clean up
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}