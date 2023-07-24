package net.oriserver.aether.aether.inventory;

import net.oriserver.aether.aether.inventory.home.HomeInventory;
import net.oriserver.aether.aether.inventory.home.admin.giveitem.GiveItemInventory;
import net.oriserver.aether.aether.inventory.home.admin.giveitem.GiveItemInventoryClick;
import net.oriserver.aether.aether.inventory.home.admin.saveitem.SaveItemInventory;
import net.oriserver.aether.aether.inventory.home.admin.saveteleport.SaveTeleportInventory;
import net.oriserver.aether.aether.inventory.home.appearance.AppearanceInventory;
import net.oriserver.aether.aether.inventory.home.appearance.badge.BadgeInventory;
import net.oriserver.aether.aether.inventory.home.appearance.headblock.HeadBlockInventory;
import net.oriserver.aether.aether.inventory.home.appearance.particle.ParticleInventory;
import net.oriserver.aether.aether.inventory.home.appearance.particle.ParticleInventoryClick;
import net.oriserver.aether.aether.inventory.home.athletic.AthleticInventory;
import net.oriserver.aether.aether.inventory.home.minigame.MiniGameInventory;
import net.oriserver.aether.aether.inventory.home.phonesetting.PhoneSettingInventory;
import net.oriserver.aether.aether.inventory.home.phonesetting.appearance.PhoneAppearanceInventory;
import net.oriserver.aether.aether.inventory.home.phonesetting.partition.PhonePartitionInventory;
import net.oriserver.aether.aether.inventory.home.setting.SettingInventory;
import net.oriserver.aether.aether.inventory.home.shop.ShopInventory;
import net.oriserver.aether.aether.player.PlayerManager;

public class InventoryManager {
    final private PlayerManager pm;
    final private HomeInventory homeInventory;
    final private AthleticInventory athleticInventory;
    final private MiniGameInventory miniGameInventory;
    final private AppearanceInventory appearanceInventory;
    final private ParticleInventory particleInventory;
    final private HeadBlockInventory headBlockInventory;
    final private BadgeInventory badgeInventory;
    final private SettingInventory settingInventory;
    final private GiveItemInventory giveItemInventory;
    final private SaveItemInventory saveItemInventory;
    final private SaveTeleportInventory saveTeleportInventory;
    final private ShopInventory shopInventory;
    final private PhoneAppearanceInventory phoneAppearanceInventory;
    final private PhonePartitionInventory phonePartitionInventory;
    final private PhoneSettingInventory phoneSettingInventory;

    public InventoryManager(PlayerManager pm){
        this.pm = pm;
        homeInventory = new HomeInventory();
        athleticInventory = new AthleticInventory();
        miniGameInventory = new MiniGameInventory();
        appearanceInventory = new AppearanceInventory();
        particleInventory = new ParticleInventory();
        headBlockInventory = new HeadBlockInventory();
        badgeInventory = new BadgeInventory();
        settingInventory = new SettingInventory();
        giveItemInventory = new GiveItemInventory();
        saveItemInventory = new SaveItemInventory();
        saveTeleportInventory = new SaveTeleportInventory();
        shopInventory = new ShopInventory();
        phoneAppearanceInventory = new PhoneAppearanceInventory();
        phonePartitionInventory = new PhonePartitionInventory();
        phoneSettingInventory = new PhoneSettingInventory();
    }
    public PlayerManager getPlayerManager(){return this.pm;}
    public HomeInventory getHomeInventory(){return this.homeInventory;}
    public AthleticInventory getAthleticInventory(){return this.athleticInventory;}
    public MiniGameInventory getMiniGameInventory(){return this.miniGameInventory;}
    public AppearanceInventory getAppearanceInventory(){return this.appearanceInventory;}
    public ParticleInventory getParticleInventory(){return  this.particleInventory;}
    public HeadBlockInventory getHeadBlockInventory(){return this.headBlockInventory;}
    public BadgeInventory getBadgeInventory(){return this.badgeInventory;}
    public SettingInventory getSettingInventory(){return this.settingInventory;}
    public GiveItemInventory getGiveItemInventory(){return this.giveItemInventory;}
    public SaveItemInventory getSaveItemInventory(){return this.saveItemInventory;}
    public SaveTeleportInventory getSaveTeleportInventory(){return this.saveTeleportInventory;}
    public ShopInventory getShopInventory(){return this.shopInventory;}
    public PhoneAppearanceInventory getPhoneAppearanceInventory(){return this.phoneAppearanceInventory;}
    public PhonePartitionInventory getPhonePartitionInventory(){return this.phonePartitionInventory;}
    public PhoneSettingInventory getPhoneSettingInventory(){return this.phoneSettingInventory;}
}
