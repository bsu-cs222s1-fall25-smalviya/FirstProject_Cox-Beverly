package bsu.edu.cs222;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Uses StringBuilder to format revisions

public class RevisionFormatter {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss")
                    .withZone(ZoneId.systemDefault());

    public static String format(List<Revision> revisions) {
        StringBuilder sb = new StringBuilder();
        for (Revision rev : revisions) {
            String formattedTime = FORMATTER.format(Instant.parse(rev.getTimestamp()));
            sb.append(formattedTime)
                    .append(" - ")
                    .append(rev.getUser())
                    .append("\n");
        }
        return sb.toString();
    }
}