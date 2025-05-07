import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static String folder;
    private static SwimmerList allSwimmers;
    private static String swimmerFilePattern;
    private static String eventFilePattern;
    private static HashMap<String,String> swimmerFiles;
    private static HashMap<String,String> eventFiles;
    private static String psychSheetFileName;
    private static int lanes;
    private static Seeding seeding;


    public static void listAllSwimmers() {
        for (Swimmer s : allSwimmers) {
            System.out.println(s);
        }
    }

    public static void listSwimmersFromClub(String club) {
        System.err.println(swimmerFiles);
        String fileName = swimmerFiles.get(club);

        ArrayList<String> files = new ArrayList<>();
        files.add(fileName);

        SwimmerList clubSwimmers = new SwimmerList(files);
        for (Swimmer s : clubSwimmers) {
            System.out.println(s);
        }
    }

    public static void listSwimmersMenu() {
        System.err.println("Enter the ID of the club to list the swimmers of.");
        System.err.println("Ex: if the file name is ClubA.txt, the ID is \"A\".");
        System.err.println("Press Enter to list swimmers from all clubs.");

        String clubID = sc.nextLine();
        if (clubID.equals("")) listAllSwimmers();
        else listSwimmersFromClub(clubID);
    }

    public static void main(String[] args) throws IOException {
        swimmerFilePattern = Files.createPattern("ClubX.txt");
        eventFilePattern = Files.createPattern("EventX.txt");

        System.err.println("Enter the name of the folder where the Swimmer and Event data are located.");
        System.err.println("(Default: \"files\"): ");

        folder = sc.nextLine();
        folder = (folder.equals("")) ? "files" : folder;

        swimmerFiles = Files.findFiles(folder, swimmerFilePattern);
        eventFiles = Files.findFiles(folder, eventFilePattern);

        allSwimmers = new SwimmerList(swimmerFiles.values());

        // listSwimmersMenu();
        System.err.println("How many lanes are there? ");
        System.err.println("(Default: 8)");
        System.err.println("Enter an even integer: ");
        String lanesStr = sc.nextLine();
        lanesStr = lanesStr.equals("") ? "8" : lanesStr;
        lanes = Integer.parseInt(lanesStr);

        System.err.println("Which seeding method should be used to create the heats? ");
        System.err.println("(1) Random seeding ");
        System.err.println("(2) Timed finals (default)");
        System.err.println("(3) Circle seeding ");
        System.err.println("Enter a choice: ");
        String seedingStr = sc.nextLine();
        seedingStr = seedingStr.equals("") ? "2" : seedingStr;
        switch (seedingStr) {
        case "1":
            seeding = Seeding.Random;
            break;
        case "2":
            seeding = Seeding.TimedFinals;
            break;
        case "3":
            seeding = Seeding.Circle;
            break;
        }

        System.err.println("Where should the psych sheet be created?");
        System.err.println("(Default: \"psychsheet.txt\")");
        psychSheetFileName = sc.nextLine();
        psychSheetFileName = (psychSheetFileName.equals("")) ? "psychsheet.txt" : psychSheetFileName;
        
        Meet m = new Meet(allSwimmers, lanes, seeding);
        for (Event ev : m.getEvents()) ev.initHeats();
        m.createPsych(psychSheetFileName);

        System.err.println("Time to schedule the events.");
        System.err.println("What time does the meet begin? Enter a time like \"9:10\"");
        System.err.println("(Default: 8:30)");
        String timeStr = sc.nextLine();
        System.err.println("Where to put the schedule file? ");
        System.err.println("(Default: \"schedule.txt\"): ");
        String schedule = sc.nextLine();
        schedule = schedule.equals("") ? "schedule.txt" : schedule;
        System.err.println("What interval (seconds) should we have in between events? ");
        System.err.println("(Default: 60): ");
        String intervalStr = sc.nextLine();
        intervalStr = intervalStr.equals("") ? "60" : intervalStr;
        int interval = Integer.parseInt(intervalStr);
        m.createSchedule(schedule, timeStr, interval);

        sc.close();
    }
}
