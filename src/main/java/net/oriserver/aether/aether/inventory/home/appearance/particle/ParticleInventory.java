package net.oriserver.aether.aether.inventory.home.appearance.particle;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ParticleInventory {//パーティクルインベントリークラス
    Inventory invParticle = Bukkit.createInventory(null, 54, "Particle");
    public ParticleInventory(){
        invParticle.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invParticle.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invParticle.setItem(1, Item.createitem(Material.TOTEM, 1, ChatColor.GREEN + "Appearance", ""));
        invParticle.setItem(10, Item.createitem(Material.END_CRYSTAL, 1, ChatColor.GREEN + "Particle", ""));

        invParticle.setItem(3, Item.createitem(Material.BLAZE_POWDER, 1, ChatColor.RED + "火", ""));
        invParticle.setItem(4, Item.createitem(Material.NETHER_STAR, 1, ChatColor.YELLOW + "光", ""));
        invParticle.setItem(5, Item.createitem(Material.FEATHER, 1, ChatColor.WHITE + "羽", ""));
        invParticle.setItem(12, Item.createitem(Material.SHULKER_SHELL, 1, ChatColor.DARK_PURPLE + "シールド", ""));
        invParticle.setItem(7, Item.createitem(Material.IRON_HELMET, 1, ChatColor.GREEN + "HeadBlock", ""));

        invParticle.setItem(16, Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.GREEN + "Badge", ""));
        invParticle.setItem(25, Item.createitem(Material.JUKEBOX, 1, ChatColor.GREEN + "Music", ""));
        invParticle.setItem(49, Item.createitem(Material.BARRIER, 1, ChatColor.RED + "パーティクルを止める", ""));
    }
    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invParticle);
        Item.setInventory(p,openinv);
    }
}
