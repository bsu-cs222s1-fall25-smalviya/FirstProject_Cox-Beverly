package bsu.edu.cs222;

// App will function as a sort of 'main' class
// It will take user input and run the program from there

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a Wikipedia article title: ");
        String title = scanner.nextLine().trim();

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
        String formatted = RevisionFormatter.format(revisions);
        System.out.println("Recent revisions for " + title + ":\n" + formatted);
    }
}
