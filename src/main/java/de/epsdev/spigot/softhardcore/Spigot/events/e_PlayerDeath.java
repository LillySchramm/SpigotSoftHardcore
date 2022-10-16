package de.epsdev.spigot.softhardcore.Spigot.events;

import de.epsdev.spigot.softhardcore.Spigot.SoftHardcore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class e_PlayerDeath implements Listener {

    @EventHandler
    void onBlockDestroyed(PlayerDeathEvent e) {
        Player player = e.getEntity();
        SoftHardcore.deathManager.registerDeath(player.getUniqueId());
        SoftHardcore.server.broadcastMessage(
            ChatColor.RED +
            "The player '" +
            ChatColor.GREEN +
            player.getDisplayName() +
            ChatColor.RED +
            "' died. He won't be able to join for: " +
            ChatColor.GOLD +
            SoftHardcore.deathManager.getFormattedDeathTime()
        );
        player.kickPlayer(
            ChatColor.RED +
            "You died. You won't be able to join for: " +
            ChatColor.GOLD +
            SoftHardcore.deathManager.getFormattedDeathTime()
        );
    }
}
