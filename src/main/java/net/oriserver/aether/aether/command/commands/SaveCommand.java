package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.sqlite.adminDB.SaveCommandDB;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveCommand implements CommandExecutor {//./scを行ったときの処理
    final private SaveCommandDB saveCommandDB;
    public SaveCommand(SaveCommandDB saveCommandDB){
        this.saveCommandDB = saveCommandDB;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if(!player.isOp())return true;
        int length = args.length;
        if(length>=1){
            if(args[args.length-1].equals("a")||args[args.length-1].equals("admin")) {
                StringBuilder commandString = new StringBuilder();
                for (int i = 0; i < args.length - 1; i++) {
                    commandString.append(args[i]);
                    if (i < args.length - 2) {
                        commandString.append(" "); // 最後の要素の一つ前まで空白を追加
                    }
                }
                if (player.getItemInHand().getType() == Material.AIR || player.getItemInHand().getType() == null) {
                    saveCommandDB.insertData("admin", String.valueOf(Material.COMMAND), commandString.toString());
                    player.sendMessage(ChatColor.GOLD + "Command:"+ ChatColor.WHITE + commandString + ChatColor.GOLD + "を保存しました");
                } else {
                    saveCommandDB.insertData("admin", String.valueOf(player.getItemInHand().getType()), commandString.toString());
                    player.sendMessage(ChatColor.GOLD + "Command:" + ChatColor.WHITE + commandString + ChatColor.GOLD + "を保存しました");
                }
            }else{
                StringBuilder commandString = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    commandString.append(args[i]);
                    if (i < args.length - 1) {
                        commandString.append(" "); // 最後の要素の一つ前まで空白を追加
                    }
                }
                if (player.getItemInHand().getType() == Material.AIR || player.getItemInHand().getType() == null) {
                    saveCommandDB.insertData(String.valueOf(player.getUniqueId()), String.valueOf(Material.COMMAND), commandString.toString());
                    player.sendMessage(ChatColor.GOLD + "Command:"+ ChatColor.WHITE + commandString + ChatColor.GOLD + "を保存しました");
                } else {
                    saveCommandDB.insertData(String.valueOf(player.getUniqueId()), String.valueOf(player.getItemInHand().getType()), commandString.toString());
                    player.sendMessage(ChatColor.GOLD + "Command:" + ChatColor.WHITE + commandString + ChatColor.GOLD + "を保存しました");
                }
            }
        }
        else{
            player.sendMessage("引数の数が違うかコマンドが間違っています。");
        }
        return true;
    }
}

