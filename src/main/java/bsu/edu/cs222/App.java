package bsu.edu.cs222;

// App will function as a sort of 'main' class
// It will take user input and run the program from there

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // keepGoing is used to loop code until user wants to exit

        boolean keepGoing = true;

        while (keepGoing) {
            System.out.print("Enter a Wikipedia article title (or type 'exit' to quit): ");
            String title = scanner.nextLine().trim();

            if (title.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                keepGoing = false;
                continue;
            }

            try {
                String json = WikipediaReader.read(title);

                // Check for redirects
                List<Redirect> redirects = RevisionParser.parseRedirects(json);
                if (!redirects.isEmpty()) {
                    for (Redirect r : redirects) {
                        System.out.println(r);
                    }
                }

                // Print revisions
                List<Revision> revisions = RevisionParser.parseRevisions(json);
                if (revisions.isEmpty()) {
                    System.out.println("No revisions found for: " + title);
                } else {
                    String formatted = RevisionFormatter.format(revisions);
                    System.out.println("Recent revisions for " + title + ":\n" + formatted);
                }

            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Try again with a different title.");
                System.out.println("");
            }
        }
        scanner.close();
    }
}