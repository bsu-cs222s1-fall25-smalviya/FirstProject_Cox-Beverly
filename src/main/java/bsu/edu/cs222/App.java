package bsu.edu.cs222;

//This class will operate as a 'main' class

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a Wikipedia article title: ");
        String title = scanner.nextLine().trim();

        String json = WikipediaReader.read(title);

        System.out.println("Raw JSON from Wikipedia:");
        System.out.println(json);
    }

}
