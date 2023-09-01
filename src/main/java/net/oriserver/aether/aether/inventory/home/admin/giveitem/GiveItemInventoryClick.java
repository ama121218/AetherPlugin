package net.oriserver.aether.aether.inventory.home.admin.giveitem;

import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GiveItemInventoryClick {

    final private InventoryManager inventoryManager;
    public GiveItemInventoryClick(InventoryManager inventoryManager){this.inventoryManager = inventoryManager;}
    public void event(Player p, Material material, int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.COMMAND && slot == 3) {p.getInventory().addItem(Item.createitem(Material.COMMAND,1,"command_block",""));p.closeInventory();}
        else if(material == Material.BARRIER && slot == 4) {p.getInventory().addItem(Item.createitem(Material.BARRIER,1,"barrier_block",""));p.closeInventory();}
        else if(material == Material.STRUCTURE_VOID && slot == 5) {p.getInventory().addItem(Item.createitem(Material.STRUCTURE_VOID,1,"structure_void",""));p.closeInventory();}
        else if(material == Material.STICK && slot == 12) {p.getInventory().addItem(Item.createitem(Material.STICK, 1, ChatColor.GOLD + "ReplaceTool", ""));p.closeInventory();}
        else if(material == Material.BONE && slot == 13) {p.getInventory().addItem(Item.createitem(Material.BONE, 1, ChatColor.GOLD + "MetaDataChanger", ""));p.closeInventory();}
        else if(material == Material.STRUCTURE_BLOCK && slot == 21) {p.getInventory().addItem(Item.createitem(Material.STRUCTURE_BLOCK,1,"structure_block",""));p.closeInventory();}
        else if(material == Material.COMPASS && slot == 30) {p.getInventory().addItem(Item.createitem(Material.COMPASS, 1, "COMPASS", ""));p.closeInventory();}
        else if(material == Material.WOOD_AXE && slot == 31) {p.getInventory().addItem(Item.createitem(Material.WOOD_AXE, 1, "WOOD_AXE", ""));p.closeInventory();}
        else if(material == Material.FEATHER && slot == 39) {p.getInventory().addItem(Item.createitem(Material.FEATHER, 1, ChatColor.YELLOW+"left click:change gamemode", ""));p.closeInventory();}
        else if(material == Material.NETHER_STAR && slot == 40) {p.getInventory().addItem(Item.createitem(Material.NETHER_STAR, 1, "NETHER_STAR", ""));p.closeInventory();}
        else if(material == Material.IRON_BARDING && slot == 41) {p.getInventory().addItem(Item.createitem(Material.IRON_BARDING, 1, ChatColor.DARK_AQUA+"checkpoint", ""));p.closeInventory();}
        else if(material == Material.SPECTRAL_ARROW && slot == 48) {p.getInventory().addItem(Item.createitem(Material.SPECTRAL_ARROW, 1, ChatColor.WHITE+"left:back,right:next", ""));p.closeInventory();}
    }
}
