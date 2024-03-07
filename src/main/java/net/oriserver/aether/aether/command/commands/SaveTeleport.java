package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.sqlite.adminDB.SaveTeleportDB;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class SaveTeleport implements CommandExecutor {//./stを行ったときの処理
    final private SaveTeleportDB saveTeleportDB;
    public SaveTeleport(SaveTeleportDB saveTeleportDB){
        this.saveTeleportDB = saveTeleportDB;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if(!player.isOp())return true;
        String uuid = String.valueOf(player.getUniqueId());
        int length = args.length;
        if(length<=3){
            if(length==0){
                Random random = new Random();
                String st_name = String.format("%06d", random.nextInt(1000000));
                String item = String.valueOf(Material.STONE);
                Location l = player.getLocation();
                saveTeleportDB.insertData(uuid,st_name,item,l.getWorld().getName(),l.getX(),l.getY(),l.getZ(),l.getYaw(),l.getPitch());
                player.sendMessage(ChatColor.GOLD + "現在地を" + ChatColor.WHITE + st_name + ChatColor.GOLD + "として設定しました");
            }else if(length==1) {
                String st_name = args[0];
                String item = String.valueOf(Material.STONE);
                Location l = player.getLocation();
                saveTeleportDB.insertData(uuid,st_name,item,l.getWorld().getName(),l.getX(),l.getY(),l.getZ(),l.getYaw(),l.getPitch());
                player.sendMessage(ChatColor.GOLD + "現在地を" + ChatColor.WHITE + st_name + ChatColor.GOLD + "として設定しました");
            }else if(length==2){
                String st_name = args[0];
                Material material = Material.getMaterial(Integer.parseInt(args[1]));
                if(material != null && material.isBlock()){
                    String item = String.valueOf(material);
                    Location l = player.getLocation();
                    saveTeleportDB.insertData(uuid,st_name,item,l.getWorld().getName(),l.getX(),l.getY(),l.getZ(),l.getYaw(),l.getPitch());
                    player.sendMessage(ChatColor.GOLD + "現在地を" + ChatColor.WHITE + st_name + ChatColor.GOLD + "として設定しました");
                }else{
                    player.sendMessage("このアイテムIDは保存できるブロックではありません");
                }
            }else {
                if(args[2].equals("a")||args[2].equals("admin")) {
                    String st_name = args[0];
                    Material material = Material.getMaterial(Integer.parseInt(args[1]));
                    if (material != null && material.isBlock()) {
                        String item = String.valueOf(material);
                        Location l = player.getLocation();
                        saveTeleportDB.insertData("admin",st_name,item,l.getWorld().getName(),l.getX(),l.getY(),l.getZ(),l.getYaw(),l.getPitch());
                        player.sendMessage(ChatColor.GOLD + "現在地を" + ChatColor.WHITE + st_name + ChatColor.GOLD + "(admin)として設定しました");
                    } else {
                        player.sendMessage("このアイテムIDは保存できるブロックではありません");
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
