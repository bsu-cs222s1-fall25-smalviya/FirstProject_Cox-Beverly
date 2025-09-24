import bsu.edu.cs222.Revision;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestRevision {

    @Test
    public void testGetters() {
        Revision revision = new Revision("Alice", "2023-01-01T12:00:00Z");
        assertEquals("Alice", revision.getUser());
        assertEquals("2023-01-01T12:00:00Z", revision.getTimestamp());
    }

    @Test
    public void testToString() {
        Revision revision = new Revision("Bob", "2023-09-23T10:00:00Z");
        String result = revision.toString();
        System.out.println("Result of toString(): " + result);
        assertFalse(result.contains("Bob"));
        assertFalse(result.contains("2023"));
    }
}




