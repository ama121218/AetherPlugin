package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Rename implements CommandExecutor {//./renameを行ったときの処理
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if (!player.isOp()) return true;
        if (args.length >= 1) {
            String name = "";
            for (String s : args) name = name + " " + s;
            if (player.getItemInHand().getType() == Material.AIR) {
                player.sendMessage("このコマンドはアイテムに何か持ってください");
                return true;
            }
            ItemStack item = player.getItemInHand();
            ItemStack nitem = Item.changename(item, name.trim());
            player.getInventory().setItem(player.getInventory().getHeldItemSlot(), nitem);
        } else {
            sender.sendMessage("引数の数が違うかコマンドが間違っています。");
        }
        return true;
    }
}
