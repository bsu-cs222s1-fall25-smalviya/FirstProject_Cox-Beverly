package bsu.edu.cs222;


import java.io.IOException;
import java.net.UnknownHostException;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class WikipediaReader {

    public static String read(String title) {
        try {
            // Build the Wikipedia API request URL
            String encodedTitle = URLEncoder.encode(title, Charset.defaultCharset());
            String urlString =
                    "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                            encodedTitle +
                            "&rvprop=timestamp|user&rvlimit=15&redirects";

            // Open connection
            URLConnection connection = new java.net.URL(urlString).openConnection();
            connection.setRequestProperty("User-Agent",
                    "FirstProject/0.1 (academic use; https://example.com)");
            connection.connect();

            // Return the raw JSON as a string
            return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());

        } catch (UnknownHostException e) {
            throw new RuntimeException("Unable to connect to Wikipedia. Check your internet connection.", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from Wikipedia for article: " + title, e);
        }
    }
}