package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class I implements CommandExecutor {//./iを行ったときの処理(現在位置の座標をコンソールに出力)

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
        Location l = player.getLocation();

        int xInt = (int) l.getX();
        int yInt = (int) l.getY();
        int zInt = (int) l.getZ();

        String formattedX = xInt + ".500";
        String formattedY = yInt + ".75";
        String formattedZ = zInt + ".500";
        //String formattedYaw = (int) convertYaw(l.getYaw())+"";
        //String formattedPitch = (int) l.getPitch()+"";
        Bukkit.getServer().getLogger().info(formattedX + "," + formattedY + "," + formattedZ);
        //Bukkit.getServer().getLogger().info(formattedX + "," + formattedY + "," + formattedZ + "," + formattedYaw + "," + formattedPitch);

        return true;
    }
    public float convertYaw(float yaw) {
        yaw %= 360;
        if (yaw > 180) {
            yaw -= 360;
        } else if (yaw < -180) {
            yaw += 360;
        }
        return yaw;
    }
}
