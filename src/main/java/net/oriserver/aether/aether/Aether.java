package net.oriserver.aether.aether;

import net.oriserver.aether.aether.command.CommandSetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Aether extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println("aaa");
        System.out.println("bbb");


        new CommandSetter(this).setCommands();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
