package me.charashenko.customworlds.listeners;

import me.charashenko.customworlds.getCfg;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.plugin.Plugin;

public class OnLavaBucketUse implements Listener {

    Plugin plugin;

    public OnLavaBucketUse(Plugin plugin) {
        this.plugin = plugin;
    }

    String noPermission = getCfg.getNoPermissionMsg(plugin);

    @EventHandler
    public void onLavaBucketUse(PlayerBucketEmptyEvent event) {

        String pluginPrefix = getCfg.getPluginPrefix(plugin);
        boolean onOff = plugin.getConfig().getBoolean("LavaBucketProtection");

        if (onOff) {

            Player player = event.getPlayer();
            Location location = player.getLocation();
            Material material = event.getBucket();
            String pos = " WORLD: "+ location.getWorld().getName() + ", X: " + location.getBlockX() + ", Y: " + location.getBlockY() + ", Z: " + location.getBlockZ();

            if (material.equals(Material.LAVA_BUCKET) && !player.hasPermission("pw.lavabucket") && !player.hasPermission("pw.admin")) {
                event.setCancelled(true);
                player.sendMessage(pluginPrefix + noPermission);
                System.out.println(pluginPrefix + player.getDisplayName() + " tried to use LAVA_BUCKET at" + pos);
                sendMessage(pluginPrefix + player.getDisplayName() + " tried to use LAVA_BUCKET at" + pos);
            } else if (material == Material.LAVA_BUCKET) {
                System.out.println(pluginPrefix + player.getDisplayName() + " used LAVA_BUCKET at" + pos);
                sendMessage(pluginPrefix + player.getDisplayName() + " used LAVA_BUCKET at" + pos);
            }
        }
    }

    private void sendMessage(String msg){
        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
            if (all.hasPermission("pw.admin")){
                all.sendMessage(msg);
            }
        }
    }
}
