package Control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveProgress {
    FileWriter fw;
    PrintWriter pw;

    public SaveProgress() {
        try {
            this.fw = new FileWriter("profiles/players.txt");

            this.pw = new PrintWriter(fw);
            pw.append("This is a test");
            pw.close();
        } catch (IOException e) {
            System.out.println("File does not exist");
        }


    }

    public void updatePlayer() {

    }
}
