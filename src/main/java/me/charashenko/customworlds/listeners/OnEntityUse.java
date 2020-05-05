package me.charashenko.customworlds.listeners;

import com.destroystokyo.paper.event.entity.ProjectileCollideEvent;
import me.charashenko.customworlds.getCfg;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.plugin.Plugin;

public class OnEntityUse implements Listener {

    Plugin plugin;

    public OnEntityUse(Plugin plugin) {
        this.plugin = plugin;
    }

    String noPermission = getCfg.getNoPermissionMsg(plugin);
    String pluginPrefix = getCfg.getPluginPrefix(plugin);

    @EventHandler
    public void onNonPlayerInventoryUse(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        World world = player.getWorld();
        if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
            event.setCancelled(true);
            player.sendMessage(pluginPrefix + noPermission);
        }
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        Player player = (Player) event.getEntered();
        World world = player.getWorld();
        if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
            event.setCancelled(true);
            player.sendMessage(pluginPrefix + noPermission);
        }
    }

    @EventHandler
    public void onVehicleDamage(VehicleDamageEvent event) {
        Player player = (Player) event.getAttacker();
        assert player != null;
        World world = player.getWorld();
        if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
            event.setCancelled(true);
            player.sendMessage(pluginPrefix + noPermission);
        }
    }

    @EventHandler
    public void onItemFrameRotateAndInsert(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        World world = event.getPlayer().getWorld();
        if (entity instanceof ItemFrame && !player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
            event.setCancelled(true);
            player.sendMessage(pluginPrefix + noPermission);
        }
    }

    @EventHandler
    public void onItemFrameBreak(HangingBreakByEntityEvent event) {
        if (event.getRemover() instanceof Player) {
            Player player = (Player) event.getRemover();
            assert player != null;
            World world = player.getWorld();
            if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
                event.setCancelled(true);
                player.sendMessage(pluginPrefix + noPermission);
            }
        } else if (event.getRemover() instanceof Projectile) {
            Player player = (Player) ((Projectile) event.getRemover()).getShooter();
            assert player != null;
            World world = player.getWorld();
            if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
                event.setCancelled(true);
                player.sendMessage(pluginPrefix + noPermission);
            }
        }
    }

    @EventHandler
    public void onItemFramePlace(HangingPlaceEvent event) {
        Player player = event.getPlayer();
        assert player != null;
        World world = player.getWorld();
        if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
            event.setCancelled(true);
            player.sendMessage(pluginPrefix + noPermission);
        }
    }

    @EventHandler
    public void onItemFrameRemoveItem(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            World world = player.getWorld();
            if (event.getEntity() instanceof ItemFrame) {
                if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
                    event.setCancelled(true);
                    player.sendMessage(pluginPrefix + noPermission);
                }
            }
        } else if (event.getDamager() instanceof Projectile) {
            Player player = (Player) ((Projectile) event.getDamager()).getShooter();
            assert player != null;
            World world = player.getWorld();
            if (event.getEntity() instanceof ItemFrame) {
                if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
                    event.setCancelled(true);
                    player.sendMessage(pluginPrefix + noPermission);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDirectDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            World world = player.getWorld();
            if (event.getEntity() instanceof Mob && !(event.getEntity() instanceof Monster)) {
                if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
                    event.setCancelled(true);
                    player.sendMessage(pluginPrefix + noPermission);
                }
            }
        }
    }

    @EventHandler
    public void onEntityProjectileHit(ProjectileCollideEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            World world = player.getWorld();
            if (event.getCollidedWith() instanceof Mob && !(event.getCollidedWith() instanceof Monster)) {
                if (!player.hasPermission("pw.admin") && !player.hasPermission("pw.use." + world.getName()) && !player.getDisplayName().equals(world.getName())) {
                    event.setCancelled(true);
                    player.sendMessage(pluginPrefix + noPermission);
                }
            }
        }
    }



}
