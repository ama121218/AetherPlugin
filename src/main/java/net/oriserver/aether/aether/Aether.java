package net.oriserver.aether.aether;

import net.oriserver.aether.aether.TNTRun.TNTRunMain;
import net.oriserver.aether.aether.chart.ChartManager;
import net.oriserver.aether.aether.chat.ChatManager;
import net.oriserver.aether.aether.command.CommandSetter;
import net.oriserver.aether.aether.command.commands.TeleportAether;
import net.oriserver.aether.aether.hideshow.HideShow;
import net.oriserver.aether.aether.hologram.Hologram;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.listener.PressureListener;
import net.oriserver.aether.aether.listener.InventoryClickListener;
import net.oriserver.aether.aether.listener.ItemClickListener;
import net.oriserver.aether.aether.listener.UsualListener;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.saveinventory.SaveInventoryManager;
import net.oriserver.aether.aether.sqlite.PhoneSetting;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Aether extends JavaPlugin{


    private static JavaPlugin plugin;
    PlayerManager playerManager;

    @Override
    public void onEnable(){
        plugin = this;
        SQLiteManager sqLiteManager = new SQLiteManager(this);
        playerManager = new PlayerManager(sqLiteManager);
        InventoryManager inventoryManager = new InventoryManager(playerManager);
        Hologram hologram = new Hologram(sqLiteManager.getChartRankingDB());
        SaveInventoryManager saveInventoryManager = new SaveInventoryManager();
        HideShow hideShow = new HideShow(plugin);
        ChatManager chatManager = new ChatManager();
        TNTRunMain tntRunMain = new TNTRunMain(plugin);
        ChartManager chartManager = new ChartManager(plugin,playerManager,hologram);
        new TeleportAether(plugin,chartManager.getChartStageInfo());

        new CommandSetter(plugin,playerManager,chatManager,saveInventoryManager,hideShow,tntRunMain);

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new UsualListener(playerManager,sqLiteManager, chatManager,saveInventoryManager,hideShow),this);
        pluginManager.registerEvents(new ItemClickListener(inventoryManager,hideShow,plugin,tntRunMain),this);
        pluginManager.registerEvents(new InventoryClickListener(playerManager,inventoryManager,plugin),this);
        pluginManager.registerEvents(new PressureListener(playerManager,sqLiteManager, hologram,plugin),this);




        for (World world : Bukkit.getWorlds()) {
            world.setGameRuleValue("announceAdvancements", "false");
        }

    }
    public void onDisable(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            // ここで、プレイヤーのデータをデータベースに保存する処理を行う
            String uuid = player.getUniqueId().toString();
            PlayerStats playerStats = playerManager.getPlayer(uuid);
            boolean[] setting = playerStats.getSetting();
            SQLiteManager sqLiteManager = playerManager.getSqLiteManager();
            sqLiteManager.getPlayerDBManagerSetting().setPlayerData(uuid,setting);

            PhoneSetting phoneSetting = sqLiteManager.getPhoneSetting();
            phoneSetting.setData(uuid, new int[]{playerStats.getPhone(), playerStats.getPartition(), playerStats.getCheckpoint()});

            playerStats.setPast_time(playerStats.getPast_time() + System.currentTimeMillis()-playerStats.getJoin_time());
            sqLiteManager.getPlayerDBManagerJQ().setData(uuid,new Object[]{playerStats.getJumpcount(), playerStats.getLocation(),playerStats.getPast_time()});
            player.setGameMode(GameMode.ADVENTURE);
        }
    }

    public static JavaPlugin getPlugin(){return plugin;}




}
