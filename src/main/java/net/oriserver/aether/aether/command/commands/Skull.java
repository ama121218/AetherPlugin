package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Skull implements CommandExecutor {//./Skullを行ったときの処理

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if(!player.isOp()){
            return true;
        }
        if(args.length==1){
            player.getInventory().addItem(Item.getHead(args[0]));
            return true;
        }
        return true;
    }
}
