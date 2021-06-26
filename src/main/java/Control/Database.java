package Control;

import java.io.*;

public class Database {
    private FileReader fr;
    private FileOutputStream fos;
    private PrintWriter pw;
    private BufferedReader br;

    public Database() {
        try {
            this.fr = new FileReader("profiles/players.txt");
            this.br = new BufferedReader(fr);
            this.fos = new FileOutputStream("profiles/players.txt", true);
            this.pw = new PrintWriter(fos);
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }

    public void updatePlayer(String playerHistory) {
        getPw().println();
        getPw().close();
    }

    public void getPlayerHistory() throws IOException {
        pw.println(1);
        pw.println(2);
        pw.println(3);
        pw.println(4);
        pw.println(5);
        pw.close();
        String str = "";
        while (!((str = br.readLine()) == null)) {
            System.out.println(str);
        }
        br.close();
    }


    public FileReader getFr() {
        return fr;
    }

    public void setFr(FileReader fr) {
        this.fr = fr;
    }

    public FileOutputStream getFos() {
        return fos;
    }

    public void setFos(FileOutputStream fos) {
        this.fos = fos;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public PrintWriter getPw() {
        return pw;
    }

    public void setPw(PrintWriter pw) {
        this.pw = pw;
    }
}
