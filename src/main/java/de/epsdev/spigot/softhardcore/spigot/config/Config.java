package de.epsdev.spigot.softhardcore.spigot.config;

import de.epsdev.spigot.softhardcore.spigot.SoftHardcore;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private final FileConfiguration fileConfiguration;
    private final Server server;

    public Config(FileConfiguration fileConfiguration, Server server) {
        this.fileConfiguration = fileConfiguration;
        this.server = server;

        initializeConfig();
    }

    private void initializeConfig() {
        fileConfiguration.addDefault("mongodbUri", "");
        fileConfiguration.addDefault("deathTime", 60L * 60L);

        fileConfiguration.options().copyDefaults(true);
        saveConfig();
    }

    private void saveConfig() {
        SoftHardcore.plugin.saveConfig();
    }

    public Long getDeathTime() {
        return fileConfiguration.getLong("deathTime");
    }

    public String getMongodbUri() {
        return fileConfiguration.getString("mongodbUri");
    }
}
