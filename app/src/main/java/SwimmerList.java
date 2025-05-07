
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SwimmerList extends ArrayList<Swimmer> {
    public SwimmerList(Collection<String> files) {
        for (String fileName : files) {
            try {
                ArrayList<Swimmer> fileSwimmers = loadFile(fileName);
                this.addAll(fileSwimmers);
            } catch (IOException e) {
                System.err.println("IOException: " + e);
            }
        }
    }

    public static ArrayList<Swimmer> loadFile(String fileName) throws IOException {
        ArrayList<Swimmer> list = new ArrayList<Swimmer>();
        for (String line : Files.readLines(fileName)) {
            list.add(new Swimmer(line));
        }
        return list;
    }
}
