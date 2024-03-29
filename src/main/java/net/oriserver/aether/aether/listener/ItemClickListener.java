package net.oriserver.aether.aether.listener;

import net.oriserver.aether.aether.chart.events.*;
import net.oriserver.aether.aether.hideshow.HideShow;
import net.oriserver.aether.aether.inventory.global.GlobalLocation;
import net.oriserver.aether.aether.inventory.level.LevelLocation;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.inventory.feather.FeatherInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ItemClickListener implements Listener {//プレイヤーが手持ちのアイテムをクリックしたイベントを操作するクラス

    private final FeatherInventory featherInventory;
    private final InventoryManager inventoryManager;
    private final HideShow hideShow;
    private final Plugin plugin;
    private final HashSet<String> itemCoolTime = new HashSet<String>();

    @Autowired
    public ItemClickListener(InventoryManager inventoryManager,HideShow hideShow , Plugin plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
        featherInventory = new FeatherInventory();
        this.inventoryManager = inventoryManager;
        this.hideShow = hideShow;
        this.plugin = plugin;
    }

    @EventHandler
    public void ItemClickEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        if(item == null)return;
        if(!p.isOp()){
            e.setCancelled(true);
            if(!isCoolTimeItem(p.getName())) {
                //p.sendMessage(ChatColor.DARK_RED + "高速で連続クリックしないでください");
                return;
            }
        }
        if (p.isOp() && item.getType() == Material.FEATHER) {
            e.setCancelled(true);
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                featherInventory.openinv(p);
            }else if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                if(p.getGameMode()==GameMode.ADVENTURE || p.getGameMode()==GameMode.SURVIVAL)p.setGameMode(GameMode.CREATIVE);
                else if(p.getGameMode()==GameMode.CREATIVE)p.setGameMode(GameMode.SURVIVAL);
            }
        }else if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA+"Aether Phone")) {
            e.setCancelled(true);
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                inventoryManager.getHomeInventory().setinv(p);
            }
        }else if(item.getType() == Material.PRISMARINE_SHARD){
            e.setCancelled(true);
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                Bukkit.getPluginManager().callEvent(new ChartTimeResetEvent(p));
            }
        }else if(p.isOp() && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().charAt(0)=='/') {
            e.setCancelled(true);
            if(item.getItemMeta().getDisplayName().contains(";")){
                String[] left_right = ChatColor.stripColor(item.getItemMeta().getDisplayName()).split(";");
                String left = left_right[0].substring(1);
                String right = left_right[1].substring(1);
                if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                    p.sendMessage(left);
                    p.performCommand(left);
                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    p.sendMessage(right);
                    p.performCommand(right);
                }
            }else{
                String right = ChatColor.stripColor(item.getItemMeta().getDisplayName().substring(1));
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if(right.startsWith("br")||right.startsWith("brush")||right.startsWith("lrbuild")){
                        ItemStack nitem = Item.changename(p.getItemInHand(),right);
                        p.getInventory().setItem(p.getInventory().getHeldItemSlot(),nitem);
                    }
                    p.sendMessage(right);
                    p.performCommand(right);
                }
            }
        }else if(item.getType()==Material.IRON_INGOT){
            e.setCancelled(true);
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
                if(p.getLocation().getWorld().getName().equals("shrine")){
                    inventoryManager.getLevelInventory().setinv(p,playerStats.getLevel_page(),playerStats.getLevel());
                }
                else if(p.getLocation().getWorld().getName().equals("chart")){
                    Bukkit.getPluginManager().callEvent(new ChartIronIngotClickEvent(p,playerStats.getChart_page(),playerStats.getChart()));
                }
                else if(p.getLocation().getWorld().getName().equals("global")){
                    inventoryManager.getGlobalInventory().setinv(p,1);
                }
            }
        }else if (item.getType() == Material.EYE_OF_ENDER){
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if ((item.getItemMeta().getDisplayName() != null) && item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Visible")) {
                    e.setCancelled(true);
                    p.getInventory().remove(Material.EYE_OF_ENDER);
                    p.getInventory().setItem(4, Item.createitem(Material.ENDER_PEARL, 1, ChatColor.GREEN + "Invisible", ""));
                    hideShow.allHide(p);
                }
            }
        } else if (item.getType() == Material.ENDER_PEARL) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if ((item.getItemMeta().getDisplayName() != null) && item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Invisible")) {
                    e.setCancelled(true);
                    p.getInventory().remove(Material.ENDER_PEARL);
                    p.getInventory().setItem(4, Item.createitem(Material.EYE_OF_ENDER, 1, ChatColor.GREEN + "Visible", ""));
                    hideShow.allShow(p);
                }
            }
        }else if(item.getType() == Material.IRON_BARDING){
            e.setCancelled(true);
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (p.getWorld().getName().equals("chart")) {
                    Bukkit.getPluginManager().callEvent(new ChartCheckPointTPEvent(p));
                } else if (p.getWorld().getName().equals("shrine")) {
                    String string_location = inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString()).getLocation();
                    if (string_location.startsWith("Level") && !string_location.equals("Level_Lobby")) {
                        p.teleport(LevelLocation.getLevelLocation(Integer.parseInt(string_location.substring(6))));
                    }
                } else if (p.getWorld().getName().equals("global")) {
                    String string_location = inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString()).getLocation();
                    if (string_location.startsWith("Global") && !string_location.equals("Global_Lobby")) {
                        p.teleport(GlobalLocation.getGlobalLocation(Integer.parseInt(string_location.substring(7))));
                    }
                }
            }
        }else if(p.isOp() && item.getType() == Material.WOOD_PICKAXE){
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("TNTRun_Setting")) {
                e.setCancelled(true);
                //Bukkit.getPluginManager().callEvent(new CreateTNTRunStageClickItemEvent(e));
            }
        }else if(p.isOp() && item.getType() == Material.SHULKER_SHELL){
            e.setCancelled(true);
            /*if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("TNTRun create stage tool")) {
                CreateStage createStage = tntRunMain.getCreateStageManager().getCreateStage(p);
                if(createStage == null)return;
                p.openInventory(createStage.getInvCreateStage());
            }else */if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"Chart Stage Create Tool")){
                Bukkit.getPluginManager().callEvent(new ChartCreateToolClickEvent(p));
            }
        }
    }
    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand(); // 主手に持っているアイテムを取得
        if(!player.isOp())event.setCancelled(true);
        if (event.getRightClicked() instanceof ArmorStand) { // 右クリックされたエンティティがアーマースタンドか確認
            ArmorStand armorStand = (ArmorStand) event.getRightClicked();

            if (!armorStand.isVisible()) { // アーマースタンドが透明であることを確認

                // Aether Phoneを持っている場合の処理
                if (item != null && item.hasItemMeta() &&
                        item.getItemMeta().hasDisplayName() &&
                        item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Aether Phone")) {

                    // Aether Phoneを持っている場合の処理をここに書く
                    event.setCancelled(true); // 任意でイベントをキャンセル
                    inventoryManager.getHomeInventory().setinv(player);
                }
                // 鉄インゴットを持っている場合の処理
                else if (item != null && item.getType() == Material.IRON_INGOT) {

                    event.setCancelled(true); //イベントをキャンセル
                    if(player.getLocation().getWorld().getName().equals("shrine"))inventoryManager.getLevelInventory().setinv(player,1,inventoryManager.getPlayerManager().getPlayer(String.valueOf(player.getUniqueId())).getLevel());
                    else if(player.getLocation().getWorld().getName().equals("chart")){
                        PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(player.getUniqueId().toString());
                        Bukkit.getPluginManager().callEvent(new ChartIronIngotClickEvent(player,playerStats.getChart_page(),playerStats.getChart()));
                    }
                    else if(player.getLocation().getWorld().getName().equals("global"))inventoryManager.getGlobalInventory().setinv(player,1);

                }
            }
        }
    }

    public boolean isCoolTimeItem(String player_name){
        if(!itemCoolTime.contains(player_name)){
            setCoolTimeItem(player_name);
            return true;
        }else{
            return false;
        }
    }
    public void setCoolTimeItem(String player_name){
        itemCoolTime.add(player_name);
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                itemCoolTime.remove(player_name);
            }
        };
        task.runTaskLater(plugin,5);
    }
}
