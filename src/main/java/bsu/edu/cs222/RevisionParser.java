package bsu.edu.cs222;

import com.google.gson.*;
//Looked up imports to help with parsing

import java.util.ArrayList;
import java.util.List;

public class RevisionParser {
    public static List<Revision> parseRevisions(String jsonData) {
        List<Revision> revisions = new ArrayList<>();

        JsonObject root = JsonParser.parseString(jsonData).getAsJsonObject();
        JsonObject pages = root.getAsJsonObject("query").getAsJsonObject("pages");

        for (String pageId : pages.keySet()) {
            JsonArray revs = pages.getAsJsonObject(pageId).getAsJsonArray("revisions");
            for (JsonElement revElem : revs) {
                JsonObject revObj = revElem.getAsJsonObject();
                String user = revObj.get("user").getAsString();
                String timestamp = revObj.get("timestamp").getAsString();
                revisions.add(new Revision(user, timestamp));
            }
        }

        return revisions;
    }
}
