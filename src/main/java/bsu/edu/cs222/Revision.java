package bsu.edu.cs222;

//Lays framework for formatting and parsing

public class Revision {
    private final String user;
    private final String timestamp;

    public Revision(String user, String timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    public String getUser() {
        return user;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
