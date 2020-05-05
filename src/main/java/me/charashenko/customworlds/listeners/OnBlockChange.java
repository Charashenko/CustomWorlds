package me.charashenko.customworlds.listeners;

import me.charashenko.customworlds.getCfg;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class OnBlockChange implements Listener {

    Plugin plugin;

    public OnBlockChange(Plugin plugin) {
        this.plugin = plugin;
    }

    String pluginPrefix = getCfg.getPluginPrefix(plugin);
    String noPermission = getCfg.getNoPermissionMsg(plugin);

    @EventHandler
    public void onBlockChange(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        World world = block.getWorld();

        if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.build.*") && !player.hasPermission("pw.build." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
            event.setCancelled(true);
            player.sendMessage(pluginPrefix + noPermission);
        }
    }

    @EventHandler
    public void onBlockChange(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        World world = block.getWorld();

        if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.build.*") && !player.hasPermission("pw.build." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
            event.setCancelled(true);
            player.sendMessage(pluginPrefix + noPermission);
        }

    }
}
