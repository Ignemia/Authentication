package eu.gamerssociety.mc.utilities.DatabaseClient;

public class DatabaseClient {
    private static DatabaseClient instance;

    public boolean loadRegisteredPlayers() {
        return true;
    }

    public boolean connect() {
        return true;
    }

    public static DatabaseClient Get( ) {
        if (DatabaseClient.instance == null) DatabaseClient.instance = new DatabaseClient();
        return DatabaseClient.instance;
    }
}
