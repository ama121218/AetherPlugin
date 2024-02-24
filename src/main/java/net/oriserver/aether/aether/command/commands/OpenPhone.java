package net.oriserver.aether.aether.command.commands;


import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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


