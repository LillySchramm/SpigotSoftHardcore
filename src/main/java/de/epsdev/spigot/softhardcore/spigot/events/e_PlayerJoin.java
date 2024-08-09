package de.epsdev.spigot.softhardcore.spigot.events;

import static de.epsdev.spigot.softhardcore.spigot.DeathManager.formatTime;

import de.epsdev.spigot.softhardcore.spigot.SoftHardcore;
import net.md_5.bungee.api.ChatColor;
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
