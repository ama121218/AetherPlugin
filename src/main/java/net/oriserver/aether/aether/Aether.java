package net.oriserver.aether.aether;

import net.oriserver.aether.aether.command.CommandSetter;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.listener.InventoryClickListener;
import net.oriserver.aether.aether.listener.ItemClickListener;
import net.oriserver.aether.aether.listener.UsualListener;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Aether extends JavaPlugin{


    private static JavaPlugin plugin;
    SQLiteManager sqLiteManager;
    PlayerManager playerManager;
    InventoryManager inventoryManager;
    CommandSetter cm;

    @Override
    public void onEnable(){
        plugin = this;
        sqLiteManager = new SQLiteManager(this);
        playerManager = new PlayerManager(sqLiteManager);
        inventoryManager = new InventoryManager(playerManager);
        cm = new CommandSetter(plugin,playerManager);

        Bukkit.getServer().getPluginManager().registerEvents(new UsualListener(playerManager,sqLiteManager),this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemClickListener(inventoryManager),this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClickListener(playerManager,inventoryManager),this);
    }
    public void onDisable(){

    }

    public static JavaPlugin getPlugin(){return plugin;}




}
