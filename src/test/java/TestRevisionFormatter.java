import bsu.edu.cs222.Revision;
import bsu.edu.cs222.RevisionFormatter;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TestRevisionFormatter {

    @Test
    public void testFormat() {
        List<Revision> revisions = List.of(
                new Revision("User1", "2023-01-01T00:00:00Z"),
                new Revision("User2", "2023-01-02T00:00:00Z")
        );

        String result = RevisionFormatter.format(revisions);
        assertTrue(result.contains("User1"));
        assertTrue(result.contains("2023-01-02"));
    }
}


