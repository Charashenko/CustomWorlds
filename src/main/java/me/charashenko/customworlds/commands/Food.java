package me.charashenko.customworlds.commands;

import me.charashenko.customworlds.getCfg;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Food implements CommandExecutor {

    Plugin plugin;

    public Food(Plugin plugin) {
        this.plugin = plugin;
    }


    String pluginPrefix = getCfg.getPluginPrefix(plugin);
    String noPermMsg = getCfg.getNoPermissionMsg(plugin);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender.hasPermission("cw.food")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if (player.getFoodLevel() < 20) {
                    player.setFoodLevel(20);
                    player.sendMessage(pluginPrefix + ChatColor.YELLOW + "Your desire was fulfilled!");
                } else {
                    player.sendMessage(pluginPrefix + ChatColor.YELLOW + "You already have full hunger bar!");
                }
            } else {
                System.out.println("Only players can run this command!");
            }
        } else {
            commandSender.sendMessage(pluginPrefix + noPermMsg);
        }
        return true;
    }
}
