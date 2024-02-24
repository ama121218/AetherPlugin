package net.oriserver.aether.aether;

import net.oriserver.aether.aether.listener.UsualListener;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.sqlite.playerDB.PhoneSetting;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public final class Aether extends JavaPlugin{
    //plugin起動時のmainクラス
    private AnnotationConfigApplicationContext context;
    @Override
    public void onEnable(){
        context = new AnnotationConfigApplicationContext();
        context.setClassLoader(getClassLoader());
        context.register(AppConfig.class);
        context.getBeanFactory().registerSingleton("javaPlugin",this);
        context.refresh();
        for(String s: context.getBeanFactory().getBeanDefinitionNames()){
            Bukkit.getServer().getLogger().info("Bean: "+s);
        }
        for (World world : Bukkit.getWorlds()) {
            world.setGameRuleValue("announceAdvancements", "false");
        }
        for(Player p:Bukkit.getServer().getOnlinePlayers())context.getBean(UsualListener.class).onJoin(p);
    }
    public void onDisable(){
        PlayerManager playerManager = context.getBean(PlayerManager.class);
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
        if (context != null) {
            context.close();
        }
    }
}
