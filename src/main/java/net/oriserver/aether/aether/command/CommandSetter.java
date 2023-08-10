package net.oriserver.aether.aether.command;

import net.oriserver.aether.aether.command.commands.getphone;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandSetter {
    private final JavaPlugin plugin;
    private final PlayerManager pm;
    public CommandSetter(JavaPlugin plugin, PlayerManager playerManager){
        this.plugin = plugin;
        this.pm = playerManager;
        setCommands();
    }
    public void setCommands(){
        plugin.getCommand("getphone").setExecutor(new getphone(pm));
    }

}
