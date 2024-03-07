package net.oriserver.aether.aether.inventory.home.appearance.headblock;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.particle.Particle;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.statics.CommonMethods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class HeadBlockInventoryClick {//HeadBlockインベントリークラスをクリックした時に操作するクラス
    final private InventoryManager inventoryManager;

    public HeadBlockInventoryClick(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot, InventoryClickEvent e) {
        if (slot == 0 && material == Material.IRON_DOOR) {inventoryManager.getHomeInventory().setinv(p);}
        else if (slot == 45 && material == Material.BARRIER) {p.closeInventory();}

        else if (slot == 1 && material == Material.TOTEM) {inventoryManager.getAppearanceInventory().setinv(p);}

        else if (CommonMethods.isInSlotSet(slot)){
            if (material.equals(Material.INK_SACK)) {
                p.sendMessage("このアイテムは手に入れてないためHeadBlockにすることはできません");
                return;
            }
            MaterialData materialData = e.getCurrentItem().getData();
            p.getInventory().setHelmet(new ItemStack(material, 1, materialData.getData()));
            p.sendMessage("HeadBlockを" + ChatColor.GREEN + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.WHITE + "にしました。");
            p.closeInventory();
        }
        else if (slot == 49 && material == Material.BARRIER) {
            p.getInventory().setHelmet(new ItemStack(Material.AIR));
            p.closeInventory();
            p.sendMessage("HeadBlockを消しました。");
        }
        else if ((slot == 48 || slot == 50) && material == Material.ARROW) {
            int page = Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0));
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p, playerStats.getHeadblock()[page-1], page);
        }

        else if (slot == 7 && material == Material.END_CRYSTAL) {
            inventoryManager.getParticleInventory().setinv(p);
        }
        else if(slot == 16 && material == Material.PRISMARINE_CRYSTALS) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString());
            inventoryManager.getBadgeInventory().setinv(p,playerStats.getBadges(0),1);
        }

        else if (slot == 8 && material == Material.GRASS) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p, playerStats.getHeadblock()[0], 1);
        }
        else if (slot == 17 && material == Material.DIAMOND_BLOCK) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p, playerStats.getHeadblock()[1], 2);
        }
        else if (slot == 26 && material == Material.WORKBENCH) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p, playerStats.getHeadblock()[2], 3);
        }
        else if (slot == 35 && material == Material.DOUBLE_PLANT) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p, playerStats.getHeadblock()[3], 4);
        }

    }
}
