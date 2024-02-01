package net.oriserver.aether.aether.command.commands;


import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.sqlite.SaveCommandDB;
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

public class OpenPhone implements CommandExecutor {
    private final InventoryManager inventoryManager;

    public OpenPhone(InventoryManager inventoryManager){this.inventoryManager = inventoryManager;}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if(!player.isOp())return false;
        inventoryManager.getHomeInventory().setinv(player);
        return true;
    }
}


