package bsu.edu.cs222;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a Wikipedia article title: ");
        String title = scanner.nextLine().trim();

        String json = WikipediaReader.read(title);
        List<Revision> revisions = RevisionParser.parseRevisions(json);

        String formatted = RevisionFormatter.format(revisions);
        System.out.println("Recent revisions for " + title + ":\n" + formatted);
    }
}
