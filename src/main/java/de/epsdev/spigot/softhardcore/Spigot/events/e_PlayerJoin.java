package de.epsdev.spigot.softhardcore.Spigot.events;

import static de.epsdev.spigot.softhardcore.Spigot.DeathManager.formatTime;

import de.epsdev.spigot.softhardcore.Spigot.SoftHardcore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class e_PlayerJoin implements Listener {

    @EventHandler
    void onBlockDestroyed(PlayerLoginEvent e) {
        Player player = e.getPlayer();

        long timeout = SoftHardcore.deathManager.canJoin(player.getUniqueId());
        if (timeout > 0) {
            e.disallow(
                PlayerLoginEvent.Result.KICK_BANNED,
                ChatColor.RED +
                "You died recently. Next life in: " +
                ChatColor.GOLD +
                formatTime(timeout)
            );
        }
    }
}
