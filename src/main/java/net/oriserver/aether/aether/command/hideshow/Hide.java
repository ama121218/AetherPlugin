package net.oriserver.aether.aether.command.hideshow;

import net.oriserver.aether.aether.hideshow.HideShow;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hide implements CommandExecutor {
    final private HideShow hideShow;
    public Hide(HideShow hideShow){
        this.hideShow = hideShow;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはプレイヤーからのみ使用できます。");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            hideShow.allHide(player);
            return true;
        }
        else if (args.length == 1) {
            hideShow.hidePlayerByName(player, args[0]);
            return true;
        }
        return false;
    }
}
