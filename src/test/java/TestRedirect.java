import bsu.edu.cs222.Redirect;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestRedirect {
    @Test
    public void testGetFromAndTo() {
        Redirect redirect = new Redirect("OldTitle", "NewTitle");
        assertEquals("OldTitle", redirect.getFrom());
        assertEquals("NewTitle", redirect.getTo());
    }

    @Test
    public void testToString() {
        Redirect redirect = new Redirect("Old", "New");
        assertEquals("Redirected from \"Old\" to \"New\"", redirect.toString());
    }
}

