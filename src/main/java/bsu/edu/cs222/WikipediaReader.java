package bsu.edu.cs222;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class WikipediaReader {

    public static String read(String title) {
        try {
            // Build the Wikipedia API request URL
            String encodedUrlString =
                    "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                            URLEncoder.encode(title, Charset.defaultCharset()) +
                            "&rvprop=timestamp|user&rvlimit=4&redirects";

            // Open connection
            URI uri = new URI(encodedUrlString);
            URLConnection connection = uri.toURL().openConnection();
            connection.setRequestProperty("User-Agent",
                    "FirstProject/0.1 (academic use; https://example.com)");
            connection.connect();

            // Return the raw JSON as a string
            return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
        } catch (IOException | java.net.URISyntaxException e) {
            throw new RuntimeException("Failed to fetch article: " + title, e);
        }
    }
}
