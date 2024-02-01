package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.createinventory.CreateInventoryManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateInventory implements CommandExecutor {
    private final CreateInventoryManager saveInventoryManager;

    public CreateInventory(CreateInventoryManager saveInventoryManager){
        this.saveInventoryManager = saveInventoryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if (!player.isOp()) return true;
        if (args.length <= 1) {
            if(args.length==0){
                saveInventoryManager.createInventory(player,"");
                player.sendMessage("新しいInventoryを作成しました");
            }else{
                if(args[0].equals("delete")){
                    saveInventoryManager.deleteInventory(player);
                }else{
                    saveInventoryManager.createInventory(player,args[0]);
                    player.sendMessage("新しいInventory("+ChatColor.YELLOW+args[0]+ ChatColor.RESET +")を作成しました");
                }
            }
        }else if(args.length == 2){
            if(args[0].equals("setname")||args[0].equals("changename")){
                saveInventoryManager.changeName(player,args[1]);
            }else{
                sender.sendMessage("引数の数が違うかコマンドが間違っています。");
            }
        }
        else {
            sender.sendMessage("引数の数が違うかコマンドが間違っています。");
        }

        return true;
    }
}
