package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.events.PlaySoundEvent;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class PlaySound implements CommandExecutor, Listener {

    Sound[] sounds = Sound.values();
    Material[] materials = {
            Material.GOLD_RECORD,
            Material.RECORD_3,
            Material.GREEN_RECORD,
            Material.RECORD_3,
            Material.RECORD_4,
            Material.RECORD_5,
            Material.RECORD_6,
            Material.RECORD_7,
            Material.RECORD_8,
            Material.RECORD_9,
            Material.RECORD_10,
            Material.RECORD_12
    };

    private final JavaPlugin plugin;

    public PlaySound(JavaPlugin plugin){
        this.plugin = plugin;
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(this,plugin);
        plugin.getCommand("sound").setExecutor(this);
    }

    public class PlaySoundTask extends BukkitRunnable {
        private Player p;
        int i=0;
        PlaySoundTask(Player p){
            this.p = p;
        }
        @Override
        public void run() {
            if(i>= sounds.length)cancel();
            p.playSound(p.getLocation(),sounds[i],100,100);
            p.sendMessage((i+1)+" "+sounds[i]+"を鳴らしました。");
            i++;
        }
    }

    Map<String,PlaySoundTask> playerMap = new HashMap<>();
    Map<String,Integer> playerPageMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if (player.isOp()) {
            if(args.length == 0){
                player.openInventory(getInventory(player,playerPageMap.getOrDefault(player.getUniqueId().toString(), 1)));
            }
            else if (args.length == 1) {
                if(args[0].equals("check")){
                    if(playerMap.containsKey(player.getUniqueId().toString())){
                        playerMap.get(player.getUniqueId().toString()).cancel();
                        player.sendMessage("再生中のサウンドをやめて初めからにします");
                    }
                    PlaySoundTask playSoundTask = new PlaySoundTask(player);
                    playSoundTask.runTaskTimer(plugin, 0L, 15L);
                    playerMap.put(player.getUniqueId().toString(),playSoundTask);
                }
                else if(args[0].equals("off")) {
                    if(playerMap.containsKey(player.getUniqueId().toString())){
                        playerMap.get(player.getUniqueId().toString()).cancel();
                        playerMap.remove(player.getUniqueId().toString());
                        player.sendMessage("オフにしました");
                    }else{
                        player.sendMessage("再生中ではありません");
                    }
                }
                else {
                    try {
                        int i = Integer.parseInt(args[0]);
                        if (!(i > 0 && i <= 549)) {
                            player.sendMessage("今は549種類までです。");
                            return true;
                        }
                        player.playSound(player.getLocation(), sounds[i], 100, 100);
                        player.sendMessage(sounds[i-1]+"を鳴らしました。");
                    } catch (NumberFormatException e) {
                        player.sendMessage("数字のみです");
                    }
                }
            }else player.sendMessage("引数が違います");
        }
        return true;
    }

    @EventHandler
    public void onSound(PlaySoundEvent e){
        Player p = e.getPlayer();
        int slot = e.getSlot();
        Inventory inv = p.getOpenInventory().getTopInventory();
        ItemStack item = inv.getItem(slot);
        if(item!=null&&item.hasItemMeta()&&item.getItemMeta().hasDisplayName()&&!item.getItemMeta().getDisplayName().equals("")){
            String page = item.getItemMeta().getDisplayName().split(" ")[0];
            if(page.matches("\\d+")) {
                p.openInventory(getInventory(p,Integer.parseInt(page)));
            }else{
                p.playSound(p.getLocation(),Sound.valueOf(item.getItemMeta().getDisplayName()),1.0f,1.0f);
                p.sendMessage(item.getItemMeta().getDisplayName()+"を鳴らしました。");
            }
        }
    }
    public Inventory getInventory(Player p,int page){
        Inventory inv = Bukkit.createInventory(null, 54, "PlaySound");

        inv.setItem(45, Item.createitem(Material.ARROW,1,(page-1)+" ページ目へ"));
        inv.setItem(53, Item.createitem(Material.ARROW,1,(page+1)+" ページ目へ"));
        if(page==1)inv.setItem(45, Item.createitem(Material.STRUCTURE_VOID,1,""));
        else if(page==11)inv.setItem(53, Item.createitem(Material.STRUCTURE_VOID,1,""));

        for(int i=0,j=0;i<=44&&i+page*46<sounds.length;i++,j++){
            inv.setItem(i,Item.createitem(materials[j],1,""+sounds[i+page*46]));
            if(j==materials.length-1)j=0;
        }
        inv.setItem(49,Item.createitem(Material.PAPER,page,page+" page"));
        playerPageMap.put(p.getUniqueId().toString(),page);
        return inv;
    }

    @EventHandler
    public void quitSound(PlayerQuitEvent e){
        if(playerMap.containsKey(e.getPlayer().getUniqueId().toString())){
            playerMap.get(e.getPlayer().getUniqueId().toString()).cancel();
            playerMap.remove(e.getPlayer().getUniqueId().toString());
        }
    }
}
