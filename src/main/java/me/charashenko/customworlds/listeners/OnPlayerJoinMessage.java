package me.charashenko.customworlds.listeners;

import me.charashenko.customworlds.getCfg;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class OnPlayerJoinMessage implements Listener {

    Plugin plugin;

    public OnPlayerJoinMessage(Plugin plugin) {
        this.plugin = plugin;
    }

    String serverName = getCfg.getServerName(plugin);
    String joinMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("JoinMessage")));

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("JoinMessages")) {
            String player = event.getPlayer().getDisplayName();
            event.setJoinMessage(serverName + ChatColor.AQUA + joinMessage + " " + ChatColor.GREEN + player);
        }
    }
}
