package net.oriserver.aether.aether.listener;

import net.oriserver.aether.aether.chart.events.ChartInventoryClickEvent;
import net.oriserver.aether.aether.chart.events.ChartStartEvent;
import net.oriserver.aether.aether.events.*;
import net.oriserver.aether.aether.inventory.feather.FeatherInventoryClick;
import net.oriserver.aether.aether.inventory.global.GlobalInventoryClick;
import net.oriserver.aether.aether.inventory.home.HomeInventoryClick;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.inventory.home.admin.giveitem.GiveItemInventoryClick;
import net.oriserver.aether.aether.inventory.home.admin.saveitem.SaveItemInventoryClick;
import net.oriserver.aether.aether.inventory.home.admin.saveteleport.SaveTeleportInventoryClick;
import net.oriserver.aether.aether.inventory.home.appearance.AppearanceInventoryClick;
import net.oriserver.aether.aether.inventory.home.appearance.badge.BadgeInventoryClick;
import net.oriserver.aether.aether.inventory.home.appearance.headblock.HeadBlockInventoryClick;
import net.oriserver.aether.aether.inventory.home.appearance.particle.ParticleInventoryClick;
import net.oriserver.aether.aether.inventory.home.athletic.AthleticInventoryClick;
import net.oriserver.aether.aether.inventory.home.minigame.MiniGameInventoryClick;
import net.oriserver.aether.aether.inventory.home.phonesetting.PhoneSettingInventoryClick;
import net.oriserver.aether.aether.inventory.home.phonesetting.appearance.PhoneAppearanceInventoryClick;
import net.oriserver.aether.aether.inventory.home.phonesetting.partition.PhonePartitionInventoryClick;
import net.oriserver.aether.aether.inventory.home.setting.SettingInventoryClick;
import net.oriserver.aether.aether.inventory.level.LevelInventoryClick;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class InventoryClickListener implements Listener {

    private final InventoryManager inventoryManager;

    private final FeatherInventoryClick featherInventoryClick;
    private final HomeInventoryClick homeInventoryClick;
    private final AthleticInventoryClick athleticInventoryClick;
    private final MiniGameInventoryClick miniGameInventoryClick;
    private final AppearanceInventoryClick appearanceInventoryClick;
    private final ParticleInventoryClick particleInventoryClick;
    private final HeadBlockInventoryClick headBlockInventoryClick;
    private final BadgeInventoryClick badgeInventoryClick;
    private final SettingInventoryClick settingInventoryClick;
    private final GiveItemInventoryClick giveItemInventoryClick;
    private final SaveItemInventoryClick saveItemInventoryClick;
    private final SaveTeleportInventoryClick saveTeleportInventoryClick;
    private final PhoneAppearanceInventoryClick phoneAppearanceInventoryClick;
    private final PhonePartitionInventoryClick phonePartitionInventoryClick;
    private final PhoneSettingInventoryClick phoneSettingInventoryClick;
    private final LevelInventoryClick levelInventoryClick;
    private final GlobalInventoryClick globalInventoryClick;
    private final Plugin plugin;

    final public PlayerManager pm;

    private final Map<String, InventoryAction> actionMap = new HashMap<>();
    private final HashSet<String> clickCoolTime = new HashSet<String>();

    public InventoryClickListener(PlayerManager pm,InventoryManager inventoryManager, Plugin plugin){

        featherInventoryClick = new FeatherInventoryClick();
        this.pm = pm;
        this.plugin = plugin;
        this.inventoryManager = inventoryManager;
        athleticInventoryClick = new AthleticInventoryClick(inventoryManager,pm);
        homeInventoryClick = new HomeInventoryClick(inventoryManager,pm);
        miniGameInventoryClick = new MiniGameInventoryClick(inventoryManager);
        appearanceInventoryClick = new AppearanceInventoryClick(inventoryManager);
        particleInventoryClick = new ParticleInventoryClick(inventoryManager);
        headBlockInventoryClick = new HeadBlockInventoryClick(inventoryManager);
        badgeInventoryClick = new BadgeInventoryClick(inventoryManager);
        settingInventoryClick = new SettingInventoryClick(inventoryManager);
        giveItemInventoryClick = new GiveItemInventoryClick(inventoryManager);
        saveItemInventoryClick = new SaveItemInventoryClick(inventoryManager);
        saveTeleportInventoryClick = new SaveTeleportInventoryClick(inventoryManager);
        phoneAppearanceInventoryClick = new PhoneAppearanceInventoryClick(inventoryManager);
        phonePartitionInventoryClick = new PhonePartitionInventoryClick(inventoryManager);
        phoneSettingInventoryClick = new PhoneSettingInventoryClick(inventoryManager);
        levelInventoryClick = new LevelInventoryClick(inventoryManager,pm);
        globalInventoryClick = new GlobalInventoryClick(pm);


        actionMap.put("Speed Select", (player, type, slot, event) -> featherInventoryClick.event(player, type, slot));
        actionMap.put("Home", (player, type, slot, event) -> homeInventoryClick.event(player, type, slot));
        actionMap.put("Athletic Teleport", (player, type, slot, event) -> athleticInventoryClick.event(player, type, slot));
        actionMap.put("MiniGame", (player, type, slot, event) -> miniGameInventoryClick.event(player, type, slot));
        actionMap.put("Appearance", (player, type, slot, event) -> appearanceInventoryClick.event(player, type, slot));
        actionMap.put("Particle", (player, type, slot, event) -> particleInventoryClick.event(player, type, slot));
        actionMap.put("HeadBlock", headBlockInventoryClick::event);
        actionMap.put("Badge", (player, type, slot, event) -> badgeInventoryClick.event(player, type, slot));
        actionMap.put("Setting", (player, type, slot, event) -> settingInventoryClick.event(player, type, slot));

        actionMap.put("Give Item", (player, type, slot, event) -> giveItemInventoryClick.event(player, type, slot));
        actionMap.put("Save Item", saveItemInventoryClick::event);
        actionMap.put(ChatColor.DARK_RED + "Delete Save Item", saveItemInventoryClick::deleteevent);
        actionMap.put(ChatColor.YELLOW + "Select Save Item 1", saveItemInventoryClick::selectevent1);
        actionMap.put(ChatColor.YELLOW + "Select Save Item 2", saveItemInventoryClick::selectevent2);
        actionMap.put(ChatColor.LIGHT_PURPLE + "Admin Save Item", saveItemInventoryClick::adminevent);
        actionMap.put(ChatColor.DARK_RED + "Admin Delete Save Item", saveItemInventoryClick::admindeleteevent);

        actionMap.put("Save Teleport", saveTeleportInventoryClick::event);
        actionMap.put(ChatColor.DARK_RED + "Delete Save Teleport", saveTeleportInventoryClick::deleteevent);
        actionMap.put(ChatColor.YELLOW + "Select Save Teleport 1", saveTeleportInventoryClick::selectevent1);
        actionMap.put(ChatColor.YELLOW + "Select Save Teleport 2", saveTeleportInventoryClick::selectevent2);
        actionMap.put(ChatColor.LIGHT_PURPLE + "Admin Save Teleport", saveTeleportInventoryClick::adminevent);
        actionMap.put(ChatColor.DARK_RED + "Admin Delete Save Teleport", saveTeleportInventoryClick::admindeleteevent);

        actionMap.put("Level Athletic", levelInventoryClick::event);
        actionMap.put("Chart Athletic", (player, type,slot, event) -> Bukkit.getPluginManager().callEvent(new ChartInventoryClickEvent(player,type,slot,event.getInventory())));

        actionMap.put("Phone Setting", (player, type, slot, event) -> phoneSettingInventoryClick.event(player, type, slot));
        actionMap.put("Phone Appearance", (player, type, slot, event) -> phoneAppearanceInventoryClick.event(player, type, slot));
        actionMap.put("Phone Partition 1", (player, type, slot, event) -> phonePartitionInventoryClick.event1(player, type, slot));
        actionMap.put("Phone Partition 2", (player, type, slot, event) -> phonePartitionInventoryClick.event2(player, type, slot));
        actionMap.put("Global Athletic",(player,type,slot, event)-> globalInventoryClick.event(player,type,slot));
        
        actionMap.put("TNTRun_CreateStage",((player, type, slot, event) -> Bukkit.getPluginManager().callEvent(new CreateTNTRunStageInventoryEvent(player,slot))));

        actionMap.put("PlaySound",(player,type,slot,event)->Bukkit.getPluginManager().callEvent(new PlaySoundEvent(player,slot)));
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        String title = e.getInventory().getTitle();
        final ItemStack clickItem = e.getCurrentItem();
        if(clickItem==null||clickItem.getType()== Material.AIR) return;
        if(!p.isOp()) {
            e.setCancelled(true);
            if (!isCoolTimeClick(p.getName())) {
                p.sendMessage(ChatColor.DARK_RED + "高速で連打しないでください");
            }
        }
        //AnvilInventoryだった場合
        if(e.getInventory().getType() == InventoryType.ANVIL){
            e.setCancelled(true);
            AnvilClickEvent customEvent = new AnvilClickEvent(e);
            Bukkit.getPluginManager().callEvent(customEvent);
            return;
        }
        //CreateChart
        if(title.endsWith("CreateChartStage")||title.endsWith("CreateCheckPoint")){
            e.setCancelled(true);
            Bukkit.getPluginManager().callEvent(new CreateChartStageInventoryEvent(p,e.getRawSlot(),clickItem.getType(),title));
            return;
        }
        InventoryAction action = actionMap.get(title);
        if (action != null) {
            e.setCancelled(true);
            action.execute(p, e.getCurrentItem().getType(), e.getRawSlot(), e);
        }
    }
    @FunctionalInterface
    interface InventoryAction {
        void execute(Player player, Material type, int slot, InventoryClickEvent event);
    }

    public boolean isCoolTimeClick(String player_name){
        if(!clickCoolTime.contains(player_name)){
            setCoolTimeItem(player_name);
            return true;
        }else{
            return false;
        }
    }
    public void setCoolTimeItem(String player_name){
        clickCoolTime.add(player_name);
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                clickCoolTime.remove(player_name);
            }
        };
        task.runTaskLater(plugin,5);
    }
}