package me.charashenko.customworlds;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class getCfg {

    public static String getServerName(Plugin plugin){
        if(plugin.getConfig().getBoolean("EnablePluginPrefix")){
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("ServerName")));}
        else {
            return "";
        }
    }

    public static String getPluginPrefix(Plugin plugin){
        if(plugin.getConfig().getBoolean("EnablePluginPrefix")){
            return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("PluginPrefix")));}
        else {
            return "";
        }
    }

    public static String getNoPermissionMsg(Plugin plugin){
        if(plugin.getConfig().getBoolean("EnablePluginPrefix")){
            return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("NoPermissionMessage")));}
        else {
            return "";
        }
    }
}
