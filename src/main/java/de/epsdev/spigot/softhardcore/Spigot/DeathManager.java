package de.epsdev.spigot.softhardcore.Spigot;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

public class DeathManager {

    private HashMap<UUID, Long> deadPlayers = new HashMap<>();
    private Long DEATH_TIME = 60L;

    public void registerDeath(UUID playerUUID) {
        long unixTimestamp = Instant.now().getEpochSecond();
        deadPlayers.put(playerUUID, unixTimestamp);
    }

    public Long canJoin(UUID playerUUID) {
        long unixTimestamp = Instant.now().getEpochSecond();
        long lastDeath = deadPlayers.getOrDefault(playerUUID, 0L);

        return lastDeath + DEATH_TIME - unixTimestamp;
    }

    public String getFormattedDeathTime() {
        return formatTime(DEATH_TIME);
    }

    public static final String formatTime(long secs) {
        return String.format(
            "%02d:%02d:%02d",
            secs / 3600,
            (secs % 3600) / 60,
            secs % 60
        );
    }
}
