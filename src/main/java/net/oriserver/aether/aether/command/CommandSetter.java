package net.oriserver.aether.aether.command;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandSetter {
    private final JavaPlugin plugin;
    public CommandSetter(JavaPlugin plugin){
        this.plugin = plugin;
    }
    public void setCommands(){
        plugin.getCommand("").setExecutor();
    }

}
