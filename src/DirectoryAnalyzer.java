import java.io.File;
import java.util.Scanner;

/**
 * DirectoryAnalyzer
 *
 * Main class for the Directory Analysis System.
 * Allows users to analyze multiple directories in one run.
 */
public class DirectoryAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Java Directory Analysis System ===");
        System.out.println("This system analyzes directories and counts:");
        System.out.println("1. Number of Java files (.java)");
        System.out.println("2. Number of files representing solved issues");
        System.out.println("=======================================\n");

        while (true) {
            // Prompt user for directory path
            System.out.print("Enter directory path (or 'quit' to exit): ");
            String path = scanner.nextLine().trim();

            // Exit condition
            if (path.equalsIgnoreCase("quit")) {
                System.out.println("Thank you for using Directory Analysis System!");
                break;
            }

            // Create service to analyze directory
            DirectoryService service = new DirectoryService(path);

            // Check if folder is valid
            if (!service.isValidDirectory()) {
                System.out.println("ERROR: Invalid directory path. Please check and try again.\n");
                continue;
            }

            // Count Java files and solved issues
            int javaFiles = service.countJavaFiles();
            int solvedIssues = service.countSolvedIssues();

            // Display results
            System.out.println("\n=== Analysis Results ===");
            System.out.println("Number of Java Files = " + javaFiles + "  Number of Issues = " + solvedIssues);
            System.out.println("========================\n");
        }

        scanner.close();
    }
}

/**
 * DirectoryService
 *
 * Handles directory validation and counting of files.
 */
class DirectoryService {
    private final File directory;

    public DirectoryService(String path) {
        this.directory = new File(path);
    }

    // Check if directory exists and is valid
    public boolean isValidDirectory() {
        return directory.exists() && directory.isDirectory();
    }

    // Count .java files in directory
    public int countJavaFiles() {
        File[] files = directory.listFiles();
        if (files == null) return 0;

        int count = 0;
        for (File f : files) {
            if (f.isFile() && f.getName().toLowerCase().endsWith(".java")) {
                count++;
            }
        }
        return count;
    }

    // Count files containing "solved" in the name
    public int countSolvedIssues() {
        File[] files = directory.listFiles();
        if (files == null) return 0;

        int count = 0;
        for (File f : files) {
            if (f.isFile() && f.getName().toLowerCase().contains("solved")) {
                count++;
            }
        }
        return count;
    }
}
