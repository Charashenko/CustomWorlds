/*
-----------------------------------------
Plugin name: Player Worlds
Description: A java plugin for Minecraft Paper Server
Author: Charashenko (Stefan Olenocin)
Do not redistribute without my knowledge!
-----------------------------------------
 */
package me.charashenko.customworlds;

//TODO Things
// --------------
// - async world creation
// - new thread world creation
// - inactive map unloader
// - nether portal event
// - end portal event
// -----------------------------------------

//TODO Commands
// -------------------
// - worldadmin - universal command for admins: /wadmin [info (Owner, members, flags?, perms?), add, remove, delete, create,load/unload, set (flags?, type?...), rename, list, user]
// - worldinfo - shows information about world: Owner, Members, ...
// - pw reload - only admin
// - worldsethome - change tp world spawn
// - worldtp - tp world spawn
// -----------------------------------------

//TODO Permissions
// -------------------
// - pw.info.<world>
// - pw.tp.<world>
// - pw.reload
// ----------------------------------------

//TODO Ideas
// ------------
// - /pvp - enables player PVP mode, and gives him access to pvp world
// - /shop - opens gui server shop for player
// - central nether/end
// - /creative - tps player to plotme, creative world
// - Or not /creative but rather set option in world creation to set the world to creative or survival mode
// - custom minigame/events....

import me.charashenko.customworlds.commands.Food;
import me.charashenko.customworlds.commands.WorldAddMember;
import me.charashenko.customworlds.commands.WorldCreation;
import me.charashenko.customworlds.listeners.OnBlockChange;
import me.charashenko.customworlds.listeners.OnEntityUse;
import me.charashenko.customworlds.listeners.OnLavaBucketUse;
import me.charashenko.customworlds.listeners.OnPlayerJoinMessage;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;
import java.util.logging.Logger;

public final class CustomWorlds extends JavaPlugin {

    public static Permission permission = null;
    private static final Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        String pluginPrefix = getCfg.getPluginPrefix(this);
        setupPermissions();
        System.out.println(pluginPrefix + "Is enabled!");

        if (!setupPermissions() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Plugin Listeners Section
        getServer().getPluginManager().registerEvents(new OnLavaBucketUse(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoinMessage(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockChange(this), this);
        getServer().getPluginManager().registerEvents(new OnEntityUse(this), this);

        // Plugin Commands Section
        Objects.requireNonNull(getCommand("food")).setExecutor(new Food(this));
        Objects.requireNonNull(getCommand("world")).setExecutor(new WorldCreation(this));
        Objects.requireNonNull(getCommand("worldadd")).setExecutor(new WorldAddMember(this));
    }



    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

}
