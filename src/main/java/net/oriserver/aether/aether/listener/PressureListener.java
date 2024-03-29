package net.oriserver.aether.aether.listener;

import net.oriserver.aether.aether.chart.events.ChartCheckPointEvent;
import net.oriserver.aether.aether.chart.events.ChartStageTPEvent;
import net.oriserver.aether.aether.chart.events.ChartStartGoalEvent;
import net.oriserver.aether.aether.statics.CommonMethods;
import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.inventory.level.LevelLocation;
import net.oriserver.aether.aether.listener.pressurelocation.*;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class PressureListener implements Listener {//プレイヤーが感圧板を踏んだ時のイベントを操作するクラス

    final private SQLiteManager sqLiteManager;
    final private PlayerManager pm;
    final private Plugin plugin;
    final private LevelGoalLocation levelGoalLocation;

    private final HashSet<String> goalCoolTime = new HashSet<>();
    private final ItemStack prismarine = Item.createitem(Material.PRISMARINE_SHARD,1,ChatColor.GREEN +"time_reset","");

    @Autowired
    public PressureListener(JavaPlugin plugin,PlayerManager pm, SQLiteManager sqLiteManager) {
        Bukkit.getPluginManager().registerEvents(this,plugin);
        this.pm = pm;
        this.sqLiteManager = sqLiteManager;
        this.plugin = plugin;
        this.levelGoalLocation = new LevelGoalLocation();
    }

    @EventHandler
    public void Pressure(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getAction() == Action.PHYSICAL) {
            if (e.getClickedBlock().getType() == Material.GOLD_PLATE) {
                if (!isCoolTimeGoal(player.getName()))return;
                if (player.getLocation().getWorld().equals(Bukkit.getWorld("shrine"))) {
                    int level = levelGoalLocation.getLevel(e.getClickedBlock().getLocation());
                    if(level==-1)return;
                    handleLevelGoal(player,level);
                }
                else if(player.getLocation().getWorld().equals(Bukkit.getWorld("chart"))) {
                    Bukkit.getPluginManager().callEvent(new ChartStartGoalEvent(player,e.getClickedBlock().getLocation()));
                }
                else if(player.getLocation().getWorld().equals(Bukkit.getWorld("global"))){
                    handleGlobalGoal(player);
                }
            } else if (e.getClickedBlock().getType() == Material.STONE_PLATE) {
                if(player.getLocation().getWorld().equals(Bukkit.getWorld("chart"))) {
                    Bukkit.getPluginManager().callEvent(new ChartStageTPEvent(player,e.getClickedBlock().getLocation()));
                }
            }else if(e.getClickedBlock().getType() == Material.IRON_PLATE){
                if(player.getLocation().getWorld().equals(Bukkit.getWorld("chart"))){
                    Bukkit.getPluginManager().callEvent(new ChartCheckPointEvent(player,e.getClickedBlock().getLocation()));
                }
            }
        }
    }

    public void handleLevelGoal(Player p,int level){
        String uuid = String.valueOf(p.getUniqueId());
        CommonMethods.setTeleport(p,LevelLocation.getLevelLocation(level+1),"Level_"+(level+1),pm.getPlayer(uuid));
        PlayerStats playerStats = pm.getPlayer(uuid);
        int player_level = playerStats.getLevel();
        if (level - player_level == 1) {
            playerStats.setLevel(level);
            sqLiteManager.getPlayerRealTimeDataDB().setPlayerLevel(uuid,level);
        }
        p.sendMessage(ChatColor.BOLD+"Level Athletic: "+level+" をクリアしました。");
        CommonMethods.setTeleport(p,LevelLocation.getLevelLocation(level+1),"Level_"+(level+1),pm.getPlayer(uuid));
    }

    public String getChartNumber(int number){
        return "Chart"+((number - 1) / 14 + 1)+"_"+((number - 1) % 14 + 1);
    }

    public void handleGlobalGoal(Player p){

    }


    public boolean isCoolTimeGoal(String player_name){
        if(!goalCoolTime.contains(player_name)){
            setCoolTimeGoal(player_name);
            return true;
        }else{
            return false;
        }
    }
    public void setCoolTimeGoal(String player_name){
        goalCoolTime.add(player_name);
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                goalCoolTime.remove(player_name);
            }
        };
        task.runTaskLater(plugin,40);
    }

}
