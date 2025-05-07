import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class Files {
    // filename : id
    public static HashMap<String,String> findFiles(String folderName, String format) {
        HashMap<String,String> files = new HashMap<>();
        File folder = new File(folderName);

        Pattern pattern = Pattern.compile(format);
        
        for (File file : folder.listFiles()) {
            String fileName = file.getName();
            Matcher matcher = pattern.matcher(fileName);
            Path path = Paths.get(folderName, fileName);
            if (matcher.find()) {
                String fileID = matcher.group(1);
                files.put(fileID, path.toString());
            }
        }
        return files;
    }

    // "EventX.txt" -> regex
    public static String createPattern(String fileName) {
        return fileName.replaceAll("X", "(.*?)");
    }


    public static ArrayList<String> readLines(String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    // files/club.txt -> club.txt
    public static String getNameOnly(String filePath) {
        return filePath.substring(1 + filePath.lastIndexOf(File.separator));
    }
}
