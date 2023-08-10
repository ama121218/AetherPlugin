package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.Item;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getphone implements CommandExecutor {
    final private PlayerManager pm;
    public getphone(PlayerManager pm){
        this.pm = pm;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        String uuid = String.valueOf(player.getUniqueId());
        boolean b = false;
        for(int i=0;i<35;i++) {
            if (player.getInventory().getItem(i) == null) continue;
            if (player.getInventory().getItem(i).getItemMeta().hasDisplayName() && player.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Aether Phone")){
                player.getInventory().setItem(i, Item.getItemPhone(pm.getPlayer(uuid).getPhone()));
                b = true;
            }
        }
        if(!b){
            if(player.getInventory().getItem(8) == null)player.getInventory().setItem(8, Item.getItemPhone(pm.getPlayer(uuid).getPhone()));
            else player.getInventory().addItem(Item.getItemPhone(pm.getPlayer(uuid).getPhone()));
        }
        return true;
    }
}
