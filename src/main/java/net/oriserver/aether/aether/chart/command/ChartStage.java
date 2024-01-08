package net.oriserver.aether.aether.chart.command;

import net.oriserver.aether.aether.chart.stage.ChartStageCreateManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChartStage implements CommandExecutor {
    final private ChartStageCreateManager createChartStageManager;
    public ChartStage(ChartStageCreateManager createChartStageManager){this.createChartStageManager = createChartStageManager;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはプレイヤーからのみ使用できます。");
            return false;
        }
        Player player = (Player) sender;
        if(!player.isOp())return false;

        if (args.length == 2) {
            if (args[0].equals("create")) {
                if (!isChart(args[1])) {
                    player.sendMessage("例：/checkpoint chart create 1_1");
                    return false;
                }
                createChartStageManager.create(player, args[1]);
            } else if (args[0].equals("rework")) {
                if (!isChart(args[1])) {
                    player.sendMessage("例：/checkpoint chart create 1_1");
                    return false;
                }
                createChartStageManager.rework(player, args[1]);
            } else {
                player.sendMessage("例：/checkpoint chart create 1_1");
            }

        }else if(args.length == 1) {
            if (args[0].equals("quit")) {
                player.sendMessage("作成をやめました");
                createChartStageManager.quit(player);
            }

        }
        else{
            player.sendMessage("例：/chart create 1_1 or /chart rework 1_1");
        }
        return true;
    }

    public boolean isChart(String stage_id){
        if (stage_id == null || stage_id.isEmpty())return false;
        String[] parts = stage_id.split("_");
        if(parts.length != 2)return false;

        try {
            int main = Integer.parseInt(parts[0]);
            int sub = Integer.parseInt(parts[1]);
            return main >= 1 && main <= 4 && sub >= 1 && sub <= 14;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
