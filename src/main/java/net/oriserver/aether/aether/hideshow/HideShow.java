package net.oriserver.aether.aether.hideshow;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;

@Component
public class HideShow {//プレイヤーを表示非表示するクラス
    final private Plugin plugin;
    final private HashSet<String> hide_player = new HashSet<>();

    @Autowired
    public HideShow(Plugin plugin){this.plugin = plugin;}

    public void allHide(Player player){
        for(Player ph : Bukkit.getOnlinePlayers()){
            player.hidePlayer(plugin,ph);
        }
        player.sendMessage(ChatColor.BOLD + "全員を非表示にしました");
        hide_player.add(player.getName());
    }

    public void allShow(Player player){
        for(Player ps : Bukkit.getOnlinePlayers()){
            player.showPlayer(plugin,ps);
        }
        player.sendMessage(ChatColor.BOLD + "全員を表示しました");
        hide_player.remove(player.getName());
    }

    public void hidePlayerByName(Player executor, String targetName){
        Player target = Bukkit.getPlayer(targetName);

        if(target == null) {
            executor.sendMessage(ChatColor.BOLD + "そのような名前のプレイヤーはいません");
            return;
        }

        executor.hidePlayer(plugin, target);
        executor.sendMessage(ChatColor.LIGHT_PURPLE + targetName + ChatColor.WHITE + " を非表示にしました");
    }

    public void showPlayerByName(Player executor, String targetName) {
        Player target = Bukkit.getPlayer(targetName);

        if (target == null) {
            executor.sendMessage(ChatColor.BOLD + "そのような名前のプレイヤーはいません");
            return;
        }

        executor.showPlayer(plugin, target);
        executor.sendMessage(ChatColor.LIGHT_PURPLE + targetName + ChatColor.WHITE + " を表示しました");
    }

    public void handleNewPlayerJoin(Player newPlayer) {
        for (String hiddenPlayerName : hide_player) {
            Player hiddenPlayer = Bukkit.getPlayer(hiddenPlayerName);
            if (hiddenPlayer != null) {
                hiddenPlayer.hidePlayer(plugin, newPlayer);
            }
        }
    }

}
