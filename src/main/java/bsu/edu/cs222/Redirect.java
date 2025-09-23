package bsu.edu.cs222;

// Similar to Revision, lays framework for redirects to be detected

public class Redirect {
    private final String from;
    private final String to;

    public Redirect(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String toString() {
        return "Redirected from \"" + from + "\" to \"" + to + "\"";
    }
}