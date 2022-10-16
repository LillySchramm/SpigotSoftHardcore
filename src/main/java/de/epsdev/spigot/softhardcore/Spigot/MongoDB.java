package de.epsdev.spigot.softhardcore.Spigot;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    public MongoClient client;

    public MongoDB(String connectionUri) {
        connect(connectionUri);
    }

    private void connect(String connectionUri) {
        ConnectionString connectionString = new ConnectionString(connectionUri);
        MongoClientSettings settings = MongoClientSettings
            .builder()
            .applyConnectionString(connectionString)
            .build();
        client = MongoClients.create(settings);
    }

    public MongoCollection getLatestDeathCollection() {
        MongoDatabase db = client.getDatabase("softHardcore");
        return db.getCollection("latestDeaths");
    }

    public void disconnect() {
        client.close();
    }
}
