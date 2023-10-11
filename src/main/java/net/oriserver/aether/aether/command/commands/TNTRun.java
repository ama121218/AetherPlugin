package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.TNTRun.TNTRunMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TNTRun implements CommandExecutor {
    final private TNTRunMain tntRunMain;
    public TNTRun(TNTRunMain tntRunMain){this.tntRunMain = tntRunMain;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはプレイヤーからのみ使用できます。");
            return false;
        }
        Player player = (Player) sender;
        if(!player.isOp())return false;
        if (args.length == 1) {
            if(args[0].equals("create")){
                tntRunMain.getCreateStageManager().createStage(player);
                return true;
            }
        }
        return false;
    }
}

