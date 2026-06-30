package models;

public record LoginData (
    String username,
    String password,
    String expected
) {
    @Override
    public String toString() {
        return username + " (" + expected + ")";
    }
}


