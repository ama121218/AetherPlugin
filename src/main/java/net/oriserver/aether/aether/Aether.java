package net.oriserver.aether.aether;

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
    private SQLiteManager sqLiteManager;
    private PlayerManager playerManager;
    private InventoryManager inventoryManager;


    @Override
    public void onEnable(){
        plugin = this;
        sqLiteManager = new SQLiteManager(this);
        playerManager = new PlayerManager(sqLiteManager);
        inventoryManager = new InventoryManager(playerManager);

        Bukkit.getServer().getPluginManager().registerEvents(new UsualListener(playerManager,sqLiteManager),this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemClickListener(playerManager),this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClickListener(inventoryManager),this);
    }
    public void onDisable(){

    }

    public static JavaPlugin getPlugin(){return plugin;}




}
