package wolfscriptbot.database;


import wolfscriptbot.config.Config;

public class Connection {
    public String getConnectionString() {
        return Config.getMongodb();
    }
}