package de.epsdev.spigot.softhardcore.spigot;

import de.epsdev.spigot.softhardcore.spigot.events.e_PlayerDeath;
import de.epsdev.spigot.softhardcore.spigot.events.e_PlayerJoin;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoftHardcore extends JavaPlugin {

    public static DeathManager deathManager;
    public static Server server;

    @Override
    public void onEnable() {
        server = getServer();
        deathManager = new DeathManager("mongodb://mongodb:27017");

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
