package net.oriserver.aether.aether;

import net.oriserver.aether.aether.TNTRun.TNTRunMain;
import net.oriserver.aether.aether.chart.ChartManager;
import net.oriserver.aether.aether.chat.ChatManager;
import net.oriserver.aether.aether.chat.ChatRoom;
import net.oriserver.aether.aether.command.CommandSetter;
import net.oriserver.aether.aether.command.commands.TeleportAether;
import net.oriserver.aether.aether.command.hideshow.Hide;
import net.oriserver.aether.aether.hideshow.HideShow;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.listener.PressureListener;
import net.oriserver.aether.aether.listener.InventoryClickListener;
import net.oriserver.aether.aether.listener.ItemClickListener;
import net.oriserver.aether.aether.listener.UsualListener;
import net.oriserver.aether.aether.particle.ParticleManager;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.saveinventory.SaveInventoryManager;
import net.oriserver.aether.aether.sqlite.PhoneSetting;
import net.oriserver.aether.aether.sqlite.PlayerDBManagerUUID;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

public final class Aether extends JavaPlugin{
    //plugin起動時のmainクラス

    private static JavaPlugin plugin;
    private PlayerManager playerManager;
    private SQLiteManager sqLiteManager;
    private ChatManager chatManager;
    private HideShow hideShow;

    @Override
    public void onEnable(){
        plugin = this;
        sqLiteManager = new SQLiteManager(this);
        playerManager = new PlayerManager(sqLiteManager);
        InventoryManager inventoryManager = new InventoryManager(playerManager);
        SaveInventoryManager saveInventoryManager = new SaveInventoryManager();
        hideShow = new HideShow(plugin);
        chatManager = new ChatManager();
        TNTRunMain tntRunMain = new TNTRunMain(plugin);
        ParticleManager particleManager = new ParticleManager(plugin);
        ChartManager chartManager = new ChartManager(plugin,playerManager,sqLiteManager.getChartRankingDB());
        new TeleportAether(plugin,chartManager.getChartStageInfo());

        new CommandSetter(plugin,playerManager,chatManager,saveInventoryManager,hideShow,tntRunMain,particleManager);

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new UsualListener(playerManager,sqLiteManager, chatManager,saveInventoryManager,hideShow,particleManager),this);
        pluginManager.registerEvents(new ItemClickListener(inventoryManager,hideShow,plugin,tntRunMain),this);
        pluginManager.registerEvents(new InventoryClickListener(playerManager,inventoryManager,particleManager,plugin),this);
        pluginManager.registerEvents(new PressureListener(playerManager,sqLiteManager,plugin),this);

        for (World world : Bukkit.getWorlds()) {
            world.setGameRuleValue("announceAdvancements", "false");
        }
        for(Player p:Bukkit.getServer().getOnlinePlayers())onJoin(p);
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


    public void onJoin(Player p){
        p.setWalkSpeed(0.2f);
        p.setFlySpeed(0.1f);
        for(PotionEffect effect : p.getActivePotionEffects()) {p.removePotionEffect(effect.getType());}
        p.setCollidable(false);

        String uuid = String.valueOf(p.getUniqueId());
        PlayerDBManagerUUID playerDBManagerUUID = sqLiteManager.getPlayerDBManagerUUID();

        p.getInventory().setItem(35, Item.getHead(p.getName()));

        if(!playerDBManagerUUID.isPlayerInDatabase(uuid)){//firstJoinEvent
            p.teleport(new Location(Bukkit.getWorld("world"),171.500,96,-4.5,90,30));
            playerDBManagerUUID.insertPlayer_name(uuid,p.getName());
            sqLiteManager.getPlayerDBManagerR().insertPlayerData(uuid);
            sqLiteManager.getPlayerDBManagerSetting().insertPlayerData(uuid);
            sqLiteManager.getPlayerDBManagerJQ().insertPlayerData(uuid);
            sqLiteManager.getPhoneSetting().insertData(uuid);
            sqLiteManager.getPlayerDBManagerHeadBlock().insertData(uuid);
        }
        else if(!playerDBManagerUUID.isChangeName(uuid,p.getName())){
            playerDBManagerUUID.updatePlayer_name(uuid, p.getName());
        }
        if(!playerManager.isPlayer(uuid)) {
            playerManager.addPlayer(p);
        }
        //サーバー内のプレイヤーデータ
        PlayerStats playerStats = playerManager.getPlayer(uuid);
        playerStats.setPlayer(p);
        if(playerStats.isChatroomonoff()){
            playerStats.setChatroom("");
        }
        else{
            String roomName = "General";
            ChatRoom chatRoom = chatManager.getChatrooms().get(roomName);
            chatRoom.getMembers().add(p);
            chatManager.getPlayerRooms().put(p,chatRoom);
        }

        Item.player_partition.put(uuid,playerStats.getPartition());
        //if(!playerStats.isPlayersidebaronoff())playerStats.getPlayerSidebar().setSidebar(player);
        //else playerStats.getPlayerSidebar().cancelSidebar();

        if(!p.isOp()){
            p.getInventory().clear();
            Item.getFirstInventory(p,playerStats.getPhone());
        }
        playerStats.setListName();
        hideShow.handleNewPlayerJoin(p);
        playerStats.setJoin_time(System.currentTimeMillis());

        p.setGameMode(GameMode.ADVENTURE);
    }

}
