package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetFirstInventory implements CommandExecutor {//./getfirstinvを行ったときの処理
    final private PlayerManager pm;
    public GetFirstInventory(PlayerManager pm){
        this.pm = pm;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if(args.length!=0){
            player.sendMessage("引数の数が違うかコマンドが間違っています。");
            return true;
        }
        String uuid = String.valueOf(player.getUniqueId());
        Item.getFirstInventory(player,pm.getPlayer(uuid).getPhone());

        return true;
    }
}
