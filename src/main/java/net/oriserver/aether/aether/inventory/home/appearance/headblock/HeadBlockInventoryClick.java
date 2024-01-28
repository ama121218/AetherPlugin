package net.oriserver.aether.aether.inventory.home.appearance.headblock;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.particle.Particle;
import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class HeadBlockInventoryClick {
    final private InventoryManager inventoryManager;

    public HeadBlockInventoryClick(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot, InventoryClickEvent e) {
        if (material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if (material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if (material == Material.TOTEM && slot == 1) {inventoryManager.getAppearanceInventory().setinv(p);}

        else if (slot==3||slot==4||slot==5||slot==12||slot==13||slot==14||slot==21||slot==22||slot==23||slot==30||slot==31||slot==32||slot==39||slot==40||slot==41){
            if (material.equals(Material.INK_SACK)) {
                p.sendMessage("このアイテムは手に入れてないためHeadBlockにすることはできません");
                return;
            }
            MaterialData materialData = e.getCurrentItem().getData();
            p.getInventory().setHelmet(new ItemStack(material, 1, materialData.getData()));
            p.sendMessage("HeadBlockを" + ChatColor.GREEN + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.WHITE + "にしました。");
            p.closeInventory();
        }
        else if (material == Material.BARRIER && slot == 49) {
            p.getInventory().setHelmet(new ItemStack(Material.AIR));
            p.closeInventory();
            p.sendMessage("HeadBlockを消しました。");
        }
        else if (material == Material.ARROW && (slot == 48 || slot == 50)) {
            int page = Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0));
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p, playerStats.getHeadblock()[page-1], page);
        }

        else if (material == Material.END_CRYSTAL && slot == 7) {
            inventoryManager.getParticleInventory().setinv(p);
        }
        else if(material == Material.PRISMARINE_CRYSTALS && slot == 16) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString());
            inventoryManager.getBadgeInventory().setinv(p,playerStats.getBadges(0),1);
        }



        else if (material == Material.GRASS && slot == 8) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p, playerStats.getHeadblock()[0], 1);
        }
        else if (material == Material.DIAMOND_BLOCK && slot == 17) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p, playerStats.getHeadblock()[1], 2);
        }
        else if (material == Material.WORKBENCH && slot == 26) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p, playerStats.getHeadblock()[2], 3);
        }
        else if (material == Material.DOUBLE_PLANT && slot == 35) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p, playerStats.getHeadblock()[3], 4);
        }
    }
}
