package net.oriserver.aether.aether;

import net.oriserver.aether.aether.chat.ChatManager;
import net.oriserver.aether.aether.command.CommandSetter;
import net.oriserver.aether.aether.hideshow.HideShow;
import net.oriserver.aether.aether.hologram.Hologram;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.listener.PressureListener;
import net.oriserver.aether.aether.listener.InventoryClickListener;
import net.oriserver.aether.aether.listener.ItemClickListener;
import net.oriserver.aether.aether.listener.UsualListener;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.saveinventory.SaveInventoryManager;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Aether extends JavaPlugin{


    private static JavaPlugin plugin;
    SQLiteManager sqLiteManager;
    PlayerManager playerManager;
    InventoryManager inventoryManager;
    CommandSetter cm;
    ChatManager chatManager;
    Hologram hologram;
    SaveInventoryManager saveInventoryManager;
    HideShow hideShow;

    @Override
    public void onEnable(){
        plugin = this;
        sqLiteManager = new SQLiteManager(this);
        playerManager = new PlayerManager(sqLiteManager);
        inventoryManager = new InventoryManager(playerManager);
        hologram = new Hologram(sqLiteManager.getChartRankingDB());
        saveInventoryManager = new SaveInventoryManager();
        hideShow = new HideShow(plugin);
        chatManager = new ChatManager();

        PressureListener pressureListener = new PressureListener(playerManager,sqLiteManager, hologram,plugin);
        cm = new CommandSetter(plugin,playerManager,chatManager,saveInventoryManager,hideShow);
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();

        pluginManager.registerEvents(new UsualListener(playerManager,sqLiteManager, chatManager,saveInventoryManager,hideShow),this);
        pluginManager.registerEvents(new ItemClickListener(inventoryManager,pressureListener,plugin),this);
        pluginManager.registerEvents(new InventoryClickListener(playerManager,inventoryManager,plugin),this);
        pluginManager.registerEvents(pressureListener,this);
    }
    public void onDisable(){

    }

    public static JavaPlugin getPlugin(){return plugin;}




}
