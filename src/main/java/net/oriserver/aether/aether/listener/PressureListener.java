package net.oriserver.aether.aether.listener;

import net.oriserver.aether.aether.chart.events.ChartCheckPointEvent;
import net.oriserver.aether.aether.chart.events.ChartGoalEvent;
import net.oriserver.aether.aether.chart.events.ChartStartEvent;
import net.oriserver.aether.aether.chart.events.ChartTimeResetEvent;
import net.oriserver.aether.aether.statics.CommonMethods;
import net.oriserver.aether.aether.hologram.Hologram;
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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PressureListener implements Listener {

    final private SQLiteManager sqLiteManager;
    final private PlayerManager pm;
    final private Plugin plugin;
    final private LevelGoalLocation levelGoalLocation;

    private final HashSet<String> goalCoolTime = new HashSet<String>();
    private final ItemStack prismarine = Item.createitem(Material.PRISMARINE_SHARD,1,ChatColor.GREEN +"time_reset","");



    public PressureListener(PlayerManager pm, SQLiteManager sqLiteManager, Hologram hologram, Plugin plugin) {
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
                    Bukkit.getPluginManager().callEvent(new ChartGoalEvent(player,e.getClickedBlock().getLocation()));
                }
                else if(player.getLocation().getWorld().equals(Bukkit.getWorld("global"))){
                    handleGlobalGoal(player);
                }
            } else if (e.getClickedBlock().getType() == Material.STONE_PLATE) {
                if(player.getLocation().getWorld().equals(Bukkit.getWorld("chart"))) {
                    Bukkit.getPluginManager().callEvent(new ChartStartEvent(player,e.getClickedBlock().getLocation()));
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
            sqLiteManager.getPlayerDBManagerR().setPlayerLevel(uuid,level);
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
