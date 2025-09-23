package bsu.edu.cs222;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
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

            // Read response as string
            String jsonData = new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());

            // Parse JSON and check for missing page
            JsonObject root = JsonParser.parseString(jsonData).getAsJsonObject();
            JsonObject pages = root.getAsJsonObject("query").getAsJsonObject("pages");
            for (String pageId : pages.keySet()) {
                JsonObject page = pages.getAsJsonObject(pageId);
                if (page.has("missing")) {
                    throw new RuntimeException("Wikipedia page not found for title: " + title);
                }
            }

            return jsonData;

        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch article: " + title, e);
        }
    }
}
