package bsu.edu.cs222;

import java.util.List;

public class RevisionFormatter {
    public static String format(List<Revision> revisions) {
        StringBuilder sb = new StringBuilder();
        for (Revision r : revisions) {
            sb.append("Revision by ")
                    .append(r.getUser())
                    .append(" at ")
                    .append(r.getTimestamp())
                    .append("\n");
        }
        return sb.toString();
    }
}

