
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportSQL {

    private String thePath;

    ImportSQL(String path) {
        thePath = path;
    }

    public String getThePath() {
        return thePath;
    }

    public void setThePath(String thePath) {
        this.thePath = thePath;
    }


    public ArrayList<String> fileString = new ArrayList<>();

    public ArrayList<String> splitFileLDDByCommand() {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(getThePath()));//file name with path
            while ((sCurrentLine = br.readLine()) != null) {
                String[] strArr = sCurrentLine.split(";");
                for (String str : strArr) {
                    fileString.add(str);
                    System.out.println(str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return fileString;

    }


    String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    public ArrayList<String> fileStringLMD = new ArrayList<>();
    public ArrayList<String> fileString2 = new ArrayList<>();


    public ArrayList<String> splitFileLMDByCommand(String delimiter) throws IOException {
        String imp;
        imp = readFile(getThePath());

        ArrayList<String> sep = new ArrayList<>();
        ArrayList<String> sepUp = new ArrayList<>();

        String[] ok = imp.split(delimiter);
        for (String str : ok) {
            if (!str.contains("INTO")) {
                sep.add(str);
            } else {
                sepUp.add(str);
            }

        }
        if (delimiter == ";") {
            return sep;
        } else {
            return sepUp;
        }


    }
}
