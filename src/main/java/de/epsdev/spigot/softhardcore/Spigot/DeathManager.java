package de.epsdev.spigot.softhardcore.Spigot;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;
import org.bson.Document;
import org.bson.conversions.Bson;

public class DeathManager {

    private MongoDB mongoDB;
    private final HashMap<UUID, Long> deadPlayers = new HashMap<>();
    private final Long DEATH_TIME = 120L;

    public DeathManager() {}

    public DeathManager(String connectionUri) {
        this.mongoDB = new MongoDB(connectionUri);
    }

    public void registerDeath(UUID playerUUID) {
        long unixTimestamp = Instant.now().getEpochSecond();

        if (mongoDB != null) {
            MongoCollection latestDeaths = mongoDB.getLatestDeathCollection();

            Bson filter = Filters.eq("uuid", playerUUID);
            Bson update = Updates.set("latestDeath", unixTimestamp);
            UpdateOptions options = new UpdateOptions().upsert(true);
            latestDeaths.updateOne(filter, update, options);
        }

        deadPlayers.put(playerUUID, unixTimestamp);
    }

    public Long canJoin(UUID playerUUID) {
        long unixTimestamp = Instant.now().getEpochSecond();
        long lastDeath = deadPlayers.getOrDefault(playerUUID, 0L);

        if (mongoDB != null) {
            MongoCollection latestDeaths = mongoDB.getLatestDeathCollection();

            Bson filter = Filters.eq("uuid", playerUUID);
            FindIterable documents = latestDeaths.find(filter);

            if (documents.first() != null) {
                Document document = ((Document) documents.first());
                lastDeath = document.getLong("latestDeath");
            }
        }

        return lastDeath + DEATH_TIME - unixTimestamp;
    }

    public String getFormattedDeathTime() {
        return formatTime(DEATH_TIME);
    }

    public static String formatTime(long secs) {
        return String.format(
            "%02d:%02d:%02d",
            secs / 3600,
            (secs % 3600) / 60,
            secs % 60
        );
    }

    public void cleanUp() {
        if (mongoDB != null) mongoDB.disconnect();
    }
}
