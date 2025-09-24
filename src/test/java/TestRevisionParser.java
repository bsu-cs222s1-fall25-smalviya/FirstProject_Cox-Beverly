package bsu.edu.cs222;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestRevisionParser {

    @Test
    public void testParseRedirects_whenRedirectsExist() {
        // Given: sample JSON containing one redirect
        String json = """
        {
            "query": {
                "redirects": [
                    { "from": "Old_Page", "to": "New_Page" }
                ]
            }
        }
        """;

        // When: parsing redirects
        List<Redirect> redirects = RevisionParser.parseRedirects(json);

        // Then: one redirect should be returned with correct values
        assertEquals(1, redirects.size());
        Redirect r = redirects.get(0);
        assertEquals("Old_Page", r.getFrom());
        assertEquals("New_Page", r.getTo());
    }

    @Test
    public void testParseRedirects_whenNoRedirectsField() {
        // Given: sample JSON without redirects field
        String json = """
        {
            "query": {
                "pages": {}
            }
        }
        """;

        // When: parsing redirects
        List<Redirect> redirects = RevisionParser.parseRedirects(json);

        // Then: should return an empty list
        assertTrue(redirects.isEmpty());
    }

    @Test
    public void testParseRedirects_whenEmptyRedirectsArray() {
        // Given: sample JSON with an empty redirects array
        String json = """
        {
            "query": {
                "redirects": []
            }
        }
        """;

        // When: parsing redirects
        List<Redirect> redirects = RevisionParser.parseRedirects(json);

        // Then: list should still be empty
        assertTrue(redirects.isEmpty());
    }

    @Test
    public void testParseRedirects_withMultipleRedirects() {
        // Given: JSON with multiple redirects
        String json = """
        {
            "query": {
                "redirects": [
                    { "from": "Page1", "to": "PageOne" },
                    { "from": "Page2", "to": "PageTwo" }
                ]
            }
        }
        """;

        // When: parsing redirects
        List<Redirect> redirects = RevisionParser.parseRedirects(json);

        // Then: verify both redirects
        assertEquals(2, redirects.size());

        Redirect first = redirects.get(0);
        Redirect second = redirects.get(1);

        assertEquals("Page1", first.getFrom());
        assertEquals("PageOne", first.getTo());

        assertEquals("Page2", second.getFrom());
        assertEquals("PageTwo", second.getTo());
    }
}
