package de.epsdev.spigot.softhardcore.spigot;

import de.epsdev.spigot.softhardcore.spigot.config.Config;
import de.epsdev.spigot.softhardcore.spigot.events.e_PlayerDeath;
import de.epsdev.spigot.softhardcore.spigot.events.e_PlayerJoin;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoftHardcore extends JavaPlugin {

    public static DeathManager deathManager;
    public static Plugin plugin;
    public static Config config;
    public static Server server;

    @Override
    public void onEnable() {
        plugin = this;
        server = getServer();

        Config config = new Config(getConfig(), getServer());
        if (config.getMongodbUri().isEmpty()) deathManager =
            new DeathManager(config.getDeathTime()); else deathManager =
            new DeathManager(config.getDeathTime(), config.getMongodbUri());

        registerEvents();
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new e_PlayerDeath(), this);
        pm.registerEvents(new e_PlayerJoin(), this);
    }

    @Override
    public void onDisable() {
        deathManager.cleanUp();
    }
}
