package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.sqlite.SaveItemDB;
import net.oriserver.aether.aether.sqlite.SaveTeleportDB;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class SaveItem implements CommandExecutor {
    final private SaveItemDB saveItemDB;
    public SaveItem(SaveItemDB saveItemDB){
        this.saveItemDB = saveItemDB;
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
        if(length<=1){
            if(length==0){
                if(player.getItemInHand().getType()== Material.AIR || player.getItemInHand().getType() == null){
                    player.sendMessage("このコマンドはアイテムを何か持ってください");
                    return true;
                }
                if(!player.getItemInHand().hasItemMeta()||!player.getItemInHand().getItemMeta().hasDisplayName())return true;
                saveItemDB.insertData(String.valueOf(player.getUniqueId()), String.valueOf(player.getItemInHand().getType()),player.getItemInHand().getItemMeta().getDisplayName());
                player.sendMessage(ChatColor.GOLD + "Itemを" + ChatColor.WHITE + player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.GOLD + "として設定しました");
            }else {
                if(args[0].equals("a")||args[0].equals("admin")) {
                    if(!player.getItemInHand().hasItemMeta()||!player.getItemInHand().getItemMeta().hasDisplayName())return true;
                    saveItemDB.insertData("admin", String.valueOf(player.getItemInHand().getType()),player.getItemInHand().getItemMeta().getDisplayName());
                    player.sendMessage(ChatColor.GOLD + "Itemを" + ChatColor.WHITE + player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.GOLD + "(admin)として設定しました");
                }
            }
        }
        else{
            player.sendMessage("引数の数が違うかコマンドが間違っています。");
        }
        return true;
    }
}
