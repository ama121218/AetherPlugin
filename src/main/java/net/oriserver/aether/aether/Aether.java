package net.oriserver.aether.aether;

import net.oriserver.aether.aether.listener.UsualListener;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.sqlite.playerDB.PlayerPhoneSetting;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public final class Aether extends JavaPlugin{
    //plugin起動時のmainクラス
    private AnnotationConfigApplicationContext context;
    @Override
    public void onEnable(){//plugin起動時
        ///////////////Spring Bootの設定///////////////////////////////////////
        context = new AnnotationConfigApplicationContext();//Spring Bootのコンテキスト宣言
        context.setClassLoader(getClassLoader());//クラスロード設定
        context.register(AppConfig.class);//Spring Bootの管理クラス(AppConfig)の宣言
        context.getBeanFactory().registerSingleton("javaPlugin",this);//このクラスのインスタンス保存
        context.refresh();//ロード

        for(String s: context.getBeanFactory().getBeanDefinitionNames()){//管理下の各インスタンスの確認
            Bukkit.getServer().getLogger().info("Bean: "+s);
        }
        //////////////////////////////////////////////////////////////////////
        for(Player p:Bukkit.getServer().getOnlinePlayers())context.getBean(UsualListener.class).onJoin(p);//plugin読み込み時にプレイヤーが居たときの処理
    }
    @Override
    public void onDisable(){//plugin停止時

        ///////////////plugin停止時にいるプレイヤーのデータを保存/////////////////////
        PlayerManager playerManager = context.getBean(PlayerManager.class);//プレイヤー管理クラスの読み込み
        for (Player player : Bukkit.getOnlinePlayers()) {//
            String uuid = player.getUniqueId().toString();
            PlayerStats playerStats = playerManager.getPlayer(uuid);
            boolean[] setting = playerStats.getSetting();
            SQLiteManager sqLiteManager = playerManager.getSqLiteManager();
            sqLiteManager.getPlayerSettingDB().setPlayerData(uuid,setting);

            PlayerPhoneSetting playerPhoneSetting = sqLiteManager.getPlayerPhoneSetting();
            playerPhoneSetting.setData(uuid, new int[]{playerStats.getPhone(), playerStats.getPartition(), playerStats.getCheckpoint()});

            playerStats.setPast_time(playerStats.getPast_time() + System.currentTimeMillis()-playerStats.getJoin_time());
            sqLiteManager.getPlayerJoinQuitDataDB().setData(uuid,new Object[]{playerStats.getJumpcount(), playerStats.getLocation(),playerStats.getPast_time()});
            player.setGameMode(GameMode.ADVENTURE);
        }
        //////////////////////////////////////////////////////////////////////
        if (context != null && context.isActive()) {//Spring Bootが閉じられていない場合
            context.close();
        }
    }
}
