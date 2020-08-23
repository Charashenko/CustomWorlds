package me.charashenko.customworlds.commands;

import me.charashenko.customworlds.getCfg;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.util.UUID;

public class WorldAddMember implements CommandExecutor {

    Plugin plugin;

    public WorldAddMember(Plugin plugin) {
        this.plugin = plugin;
    }

    String pluginPrefix = getCfg.getPluginPrefix(plugin);
    Permission permission;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        World world = player.getWorld();
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(getUUID(args[0], player)); //FIXME Repair bukkit.getPlayer returning NULL
            assert target != null;
            if (!player.getDisplayName().equals(target.getDisplayName())) {
                    if (!target.hasPermission("cw.build." + world) && !target.hasPermission("cw.use." + world)) {
                        player.sendMessage(pluginPrefix + "You have successfully added player " + ChatColor.BLUE + target.getDisplayName() + " to your world.");
                        if (target.isOnline()) {
                            target.sendMessage(pluginPrefix + "You have been added to the world owned by " + ChatColor.GREEN + player.getDisplayName());
                        }
                        if (!target.hasPermission("cw.build." + world)) {
                            permission.playerAdd(target, "cw.build." + world);
                        }
                        if (!target.hasPermission("cw.use." + world)) {
                            permission.playerAdd(target, "cw.use." + world);
                        }
                    } else {
                        player.sendMessage(pluginPrefix + "Player " + ChatColor.GREEN + player.getDisplayName() + " is already a member of your world.");
                    }
                } else {
                    player.sendMessage(pluginPrefix + ChatColor.RED + "You can`t add yourself to your own world.");
                }
                return true;
        } else {
            return false;
        }
    }

    @Nonnull
    private UUID getUUID(String playerName, Player self){
        for (Player all : Bukkit.getServer().getOnlinePlayers()){
            if (all.getDisplayName().equals(playerName)){
                return all.getUniqueId();
            }
        }
        return self.getUniqueId();
    }

}
