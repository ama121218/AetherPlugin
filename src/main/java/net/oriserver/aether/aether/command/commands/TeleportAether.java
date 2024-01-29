package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.inventory.level.LevelLocation;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static net.oriserver.aether.aether.AthleticLocation.getLocation;

public class TeleportAether implements CommandExecutor {

    private final ChartStageInfo chartStageInfo;
    private final JavaPlugin plugin;

    public TeleportAether(JavaPlugin plugin, ChartStageInfo chartStageInfo){
        this.chartStageInfo = chartStageInfo;
        this.plugin = plugin;
        plugin.getCommand("teleportaether").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if(!player.isOp())return true;
        if(args.length<=2){
            if(args.length==1){
                if(args[0].equals("chart")){
                    player.teleport(getLocation(AthleticLocation.CHART));
                }else if(args[0].equals("level")){
                    player.teleport(getLocation(AthleticLocation.SHRINE));
                }
            }
            else if(args.length==2){
                if(args[0].equals("chart")){
                    int i = 0;
                    try {
                        i = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    if(i>=1&&56>=i){
                        if(chartStageInfo.getStageTP(i)!=null) {
                            player.teleport(chartStageInfo.getStageTP(i));
                        }
                    }else{
                        player.sendMessage("最大ステージ数オーバー");
                    }
                }else if(args[0].equals("level")){
                    int i = 0;
                    try {
                        i = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    if(i>=1&&121>=i){
                        player.teleport(LevelLocation.getLevelLocation(i));
                    }else{
                        player.sendMessage("最大ステージ数オーバー");
                    }
                }
            }
        }
        else{
            player.sendMessage("引数の数が違うかコマンドが間違っています。");
        }
        return true;
    }
}
