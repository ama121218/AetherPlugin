package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.inventory.level.LevelLocation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static net.oriserver.aether.aether.AthleticLocation.getLocation;


public class TeleportAether implements CommandExecutor {//./tpaを行ったときの処理

    private final ChartStageInfo chartStageInfo;

    public TeleportAether(ChartStageInfo chartStageInfo){
        this.chartStageInfo = chartStageInfo;
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
                    if(args[1].contains("_")){
                        String[] parts = args[1].split("_");
                        if(parts.length != 2)return false;
                        try {
                            i  = (Integer.parseInt(parts[0])-1)*14 +Integer.parseInt(parts[1]);
                        } catch (NumberFormatException e) {
                            player.sendMessage("無効");
                            return false;
                        }
                    }else {
                        try {
                            i = Integer.parseInt(args[1]);
                        } catch (NumberFormatException e) {
                            player.sendMessage("無効");
                            return false;
                        }
                    }
                    if(i>=1&&56>=i){
                        if(!chartStageInfo.getStageName(i).equals("")) {
                            player.teleport(chartStageInfo.getStageTP(i));
                        }else{
                            player.teleport(AthleticLocation.getChartLocation(i));
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
