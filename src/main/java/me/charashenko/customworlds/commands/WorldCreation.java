package me.charashenko.customworlds.commands;

import me.charashenko.customworlds.getCfg;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WorldCreation implements CommandExecutor {

    Plugin plugin;

    public WorldCreation(Plugin plugin) {
        this.plugin = plugin;
    }

    String pluginPrefix = getCfg.getPluginPrefix(plugin);
    Player player;
    World world;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        player = (Player) sender;
        player.sendMessage(pluginPrefix + "Your world is being created.");
        WorldCreator creator = new WorldCreator(sender.getName());
        creator.type(WorldType.NORMAL);
        world = creator.createWorld();
        afterCreation();

        return true;
    }

    private void afterCreation(){
        player.sendMessage(pluginPrefix + "Your world is ready, teleporting now.");
        player.teleport(world.getSpawnLocation());
    }
}
