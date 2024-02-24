package net.oriserver.aether.aether.inventory;

import net.oriserver.aether.aether.inventory.global.GlobalInventory;
import net.oriserver.aether.aether.inventory.home.HomeInventory;
import net.oriserver.aether.aether.inventory.home.admin.giveitem.GiveItemInventory;
import net.oriserver.aether.aether.inventory.home.admin.savecommand.SaveCommandInventory;
import net.oriserver.aether.aether.inventory.home.admin.saveinventory.SaveInventoryInventory;
import net.oriserver.aether.aether.inventory.home.admin.saveitem.SaveItemInventory;
import net.oriserver.aether.aether.inventory.home.admin.saveteleport.SaveTeleportInventory;
import net.oriserver.aether.aether.inventory.home.appearance.AppearanceInventory;
import net.oriserver.aether.aether.inventory.home.appearance.badge.BadgeInventory;
import net.oriserver.aether.aether.inventory.home.appearance.headblock.HeadBlockInventory;
import net.oriserver.aether.aether.inventory.home.appearance.particle.ParticleInventory;
import net.oriserver.aether.aether.inventory.home.appearance.tag.TagInventory;
import net.oriserver.aether.aether.inventory.home.athletic.AthleticInventory;
import net.oriserver.aether.aether.inventory.home.minigame.MiniGameInventory;
import net.oriserver.aether.aether.inventory.home.phonesetting.PhoneSettingInventory;
import net.oriserver.aether.aether.inventory.home.phonesetting.appearance.PhoneAppearanceInventory;
import net.oriserver.aether.aether.inventory.home.phonesetting.partition.PhonePartitionInventory;
import net.oriserver.aether.aether.inventory.home.setting.SettingInventory;
import net.oriserver.aether.aether.inventory.home.shop.ShopInventory;
import net.oriserver.aether.aether.inventory.level.LevelInventory;
import net.oriserver.aether.aether.player.PlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.HTML;

@Component
public class InventoryManager {
    private final PlayerManager playerManager;
    private final HomeInventory homeInventory;
    private final AthleticInventory athleticInventory;
    private final MiniGameInventory miniGameInventory;
    private final AppearanceInventory appearanceInventory;
    private final ParticleInventory particleInventory;
    private final HeadBlockInventory headBlockInventory;
    private final BadgeInventory badgeInventory;
    private final TagInventory tagInventory;
    private final SettingInventory settingInventory;
    private final GiveItemInventory giveItemInventory;
    private final SaveItemInventory saveItemInventory;
    private final SaveTeleportInventory saveTeleportInventory;
    private final SaveInventoryInventory saveInventoryInventory;
    private final SaveCommandInventory saveCommandInventory;
    private final ShopInventory shopInventory;
    private final PhoneAppearanceInventory phoneAppearanceInventory;
    private final PhonePartitionInventory phonePartitionInventory;
    private final PhoneSettingInventory phoneSettingInventory;
    private final LevelInventory levelInventory;
    private final GlobalInventory globalInventory;

    @Autowired
    public InventoryManager(PlayerManager playerManager){
        this.playerManager = playerManager;
        homeInventory = new HomeInventory();
        athleticInventory = new AthleticInventory();
        miniGameInventory = new MiniGameInventory();
        appearanceInventory = new AppearanceInventory();
        particleInventory = new ParticleInventory();
        headBlockInventory = new HeadBlockInventory();
        badgeInventory = new BadgeInventory();
        tagInventory = new TagInventory();
        settingInventory = new SettingInventory();
        giveItemInventory = new GiveItemInventory();
        saveItemInventory = new SaveItemInventory(playerManager);
        saveTeleportInventory = new SaveTeleportInventory(playerManager);
        saveInventoryInventory = new SaveInventoryInventory(playerManager);
        saveCommandInventory = new SaveCommandInventory(playerManager);
        shopInventory = new ShopInventory();
        phoneAppearanceInventory = new PhoneAppearanceInventory();
        phonePartitionInventory = new PhonePartitionInventory();
        phoneSettingInventory = new PhoneSettingInventory();
        levelInventory = new LevelInventory();
        globalInventory = new GlobalInventory();
    }
    public PlayerManager getPlayerManager(){return this.playerManager;}
    public HomeInventory getHomeInventory(){return this.homeInventory;}
    public AthleticInventory getAthleticInventory(){return this.athleticInventory;}
    public MiniGameInventory getMiniGameInventory(){return this.miniGameInventory;}
    public AppearanceInventory getAppearanceInventory(){return this.appearanceInventory;}
    public ParticleInventory getParticleInventory(){return  this.particleInventory;}
    public HeadBlockInventory getHeadBlockInventory(){return this.headBlockInventory;}
    public BadgeInventory getBadgeInventory(){return this.badgeInventory;}
    public TagInventory getTagInventory(){return this.tagInventory;}
    public SettingInventory getSettingInventory(){return this.settingInventory;}
    public GiveItemInventory getGiveItemInventory(){return this.giveItemInventory;}
    public SaveItemInventory getSaveItemInventory(){return this.saveItemInventory;}
    public SaveTeleportInventory getSaveTeleportInventory(){return this.saveTeleportInventory;}
    public SaveInventoryInventory getSaveInventoryInventory(){return this.saveInventoryInventory;}
    public SaveCommandInventory getSaveCommandInventory(){return this.saveCommandInventory;}
    public ShopInventory getShopInventory(){return this.shopInventory;}
    public PhoneAppearanceInventory getPhoneAppearanceInventory(){return this.phoneAppearanceInventory;}
    public PhonePartitionInventory getPhonePartitionInventory(){return this.phonePartitionInventory;}
    public PhoneSettingInventory getPhoneSettingInventory(){return this.phoneSettingInventory;}
    public LevelInventory getLevelInventory(){return levelInventory;}
    public GlobalInventory getGlobalInventory(){return  globalInventory;}
}
