package net.oriserver.aether.aether.listener;

import net.oriserver.aether.aether.inventory.chart.ChartInventoryClick;
import net.oriserver.aether.aether.inventory.feather.FeatherInventoryClick;
import net.oriserver.aether.aether.inventory.home.HomeInventoryClick;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.inventory.home.admin.giveitem.GiveItemInventoryClick;
import net.oriserver.aether.aether.inventory.home.admin.saveitem.SaveItemInventoryClick;
import net.oriserver.aether.aether.inventory.home.admin.saveteleport.SaveTeleportInventoryClick;
import net.oriserver.aether.aether.inventory.home.appearance.badge.BadgeInventoryClick;
import net.oriserver.aether.aether.inventory.home.appearance.headblock.HeadBlockInventory;
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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    final private InventoryManager inventoryManager;

    final private FeatherInventoryClick featherInventoryClick;
    final private HomeInventoryClick homeInventoryClick;
    final private AthleticInventoryClick athleticInventoryClick;
    final private MiniGameInventoryClick miniGameInventoryClick;
    final private ParticleInventoryClick particleInventoryClick;
    final private HeadBlockInventoryClick headBlockInventoryClick;
    final private BadgeInventoryClick badgeInventoryClick;
    final private SettingInventoryClick settingInventoryClick;
    final private GiveItemInventoryClick giveItemInventoryClick;
    final private SaveItemInventoryClick saveItemInventoryClick;
    final private SaveTeleportInventoryClick saveTeleportInventoryClick;
    final private PhoneAppearanceInventoryClick phoneAppearanceInventoryClick;
    final private PhonePartitionInventoryClick phonePartitionInventoryClick;
    final private PhoneSettingInventoryClick phoneSettingInventoryClick;
    final private ChartInventoryClick chartInventoryClick;
    final private LevelInventoryClick levelInventoryClick;

    public InventoryClickListener(InventoryManager inventoryManager){

        featherInventoryClick = new FeatherInventoryClick();
        this.inventoryManager = inventoryManager;
        athleticInventoryClick = new AthleticInventoryClick(inventoryManager);
        homeInventoryClick = new HomeInventoryClick(inventoryManager);
        miniGameInventoryClick = new MiniGameInventoryClick(inventoryManager);
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
        levelInventoryClick = new LevelInventoryClick(inventoryManager);
        chartInventoryClick = new ChartInventoryClick(inventoryManager);
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        String title = e.getInventory().getTitle();
        final ItemStack clickItem = e.getCurrentItem();
        if(clickItem==null||clickItem.getType()== Material.AIR) return;
        if(!p.isOp())e.setCancelled(true);
        if(title.equals("speed_select")){
            e.setCancelled(true);
            featherInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot());
        }else if(title.equals("Home")){
            e.setCancelled(true);
            homeInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot());
        }
        else if(title.equals("Athletic_Teleport")){
            e.setCancelled(true);
            athleticInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot());
        }else if(title.equals("MiniGame")){
            e.setCancelled(true);
            miniGameInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot());
        }else if(title.equals("Particle")){
            e.setCancelled(true);
            particleInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot());
        }
        else if(title.equals("HeadBlock")){
            e.setCancelled(true);
            headBlockInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot(),e);
        }
        else if(title.equals("Badge")){
            e.setCancelled(true);
            badgeInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot());
        }
        else if(title.equals("Setting")){
            e.setCancelled(true);
            settingInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot());
        }
        else if(title.equals("Give_Item")){
            e.setCancelled(true);
            giveItemInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot());
        }
        else if(title.equals("Save_Item")){
            e.setCancelled(true);
            saveItemInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot(),e);
        }
        else if(title.equals("Save_Teleport")){
            e.setCancelled(true);
            saveTeleportInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot());
        }else if(title.equals("Phone_Setting")){
            e.setCancelled(true);
            phoneSettingInventoryClick.event(p,e.getCurrentItem().getType(),e.getRawSlot());
        }
    }
}