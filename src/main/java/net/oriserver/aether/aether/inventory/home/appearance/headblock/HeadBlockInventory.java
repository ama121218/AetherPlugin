package net.oriserver.aether.aether.inventory.home.appearance.headblock;

import net.oriserver.aether.aether.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class HeadBlockInventory {
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40,41};
    ItemStack gray_dye = new ItemStack(Material.INK_SACK, 1, (short) 8);
    ArrayList<ItemStack[]> headblock = new ArrayList<ItemStack[]>();
    Inventory invHeadBlock = Bukkit.createInventory(null, 54, "HeadBlock");
    public HeadBlockInventory(){
        invHeadBlock.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invHeadBlock.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invHeadBlock.setItem(1, Item.createitem(Material.TOTEM, 1, ChatColor.GREEN + "Appearance", ""));
        invHeadBlock.setItem(10, Item.createitem(Material.END_CRYSTAL, 1, ChatColor.GREEN + "HeadBlock", ""));

        invHeadBlock.setItem(7, Item.createitem(Material.END_CRYSTAL, 1,ChatColor.GREEN + "Particle", ""));
        invHeadBlock.setItem(16, Item.createitem(Material.PRISMARINE_CRYSTALS, 1,ChatColor.GREEN + "BBadge", ""));
        invHeadBlock.setItem(25, Item.createitem(Material.JUKEBOX, 1, ChatColor.GREEN + "Music", ""));

        invHeadBlock.setItem(8, Item.createitem(Material.GRASS, 1, ChatColor.GREEN + "1ページ目", ""));
        invHeadBlock.setItem(17, Item.createitem(Material.DIAMOND_BLOCK, 1, ChatColor.GREEN + "2ページ目", ""));
        invHeadBlock.setItem(26, Item.createitem(Material.WORKBENCH, 1, ChatColor.GREEN + "3ページ目", ""));
        invHeadBlock.setItem(35, Item.changename(new ItemStack(Material.DOUBLE_PLANT,1,(short) 5),ChatColor.GREEN +"4ページ目",""));

        iniHeadBlock();
    }
    public void setinv(Player p,String booleans,int page){
        Inventory openinv = Item.inventorycopy(invHeadBlock);
        for(int i=0;i<booleans.length();i++){
            if(booleans.charAt(i)=='1'){
                openinv.setItem(a[i],headblock.get(page)[i]);
            }else{
                openinv.setItem(a[i],gray_dye);
            }
        }
        if(page==1){
            openinv.setItem(48, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
            openinv.setItem(50, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
        }else if(page==2){
            openinv.setItem(48, Item.createitem(Material.ARROW, 1, "1ページ目へ", ""));
            openinv.setItem(50, Item.createitem(Material.ARROW, 1, "3ページ目へ", ""));
        }else if(page==3){
            openinv.setItem(48, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
            openinv.setItem(50, Item.createitem(Material.ARROW, 1, "4ページ目へ", ""));
        }else if(page==4){
            openinv.setItem(48, Item.createitem(Material.ARROW, 1, "3ページ目へ", ""));
            openinv.setItem(50, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
        }
        Item.setInventory(p,openinv);
    }
    
    public void iniHeadBlock(){
        ItemStack[] headblock1 = new ItemStack[15];
        headblock1[0] = Item.createitem(Material.STONE, 1, ChatColor.GREEN + "STONE", "");
        headblock1[1] = Item.createitem(Material.COBBLESTONE, 1, ChatColor.GREEN + "COBBLESTONE", "");
        headblock1[2] = Item.createitem(Material.SMOOTH_BRICK, 1, ChatColor.GREEN + "SMOOTH_BRICK", "");
        headblock1[3] = Item.createitem(Material.DIRT, 1, ChatColor.GREEN + "DIRT", "");
        headblock1[4] = Item.createitem(Material.GRASS, 1, ChatColor.GREEN + "GRASS", "");
        headblock1[5] = Item.changename(new ItemStack(Material.DIRT,1, (short) 2),ChatColor.GREEN +"PODZOL","");
        headblock1[6] = Item.createitem(Material.SAND, 1, ChatColor.GREEN + "SAND", "");
        headblock1[7] = Item.changename(new ItemStack(Material.SAND,1, (short) 1),ChatColor.GREEN +"REDSAND","");
        headblock1[8] = Item.createitem(Material.GRAVEL, 1, ChatColor.GREEN + "GRAVEL", "");
        headblock1[9] = Item.createitem(Material.SNOW_BLOCK, 1, ChatColor.GREEN + "SNOWBLOCK", "");
        headblock1[10] = Item.createitem(Material.ICE, 1, ChatColor.GREEN + "ICE", "");
        headblock1[11] = Item.createitem(Material.PACKED_ICE, 1, ChatColor.GREEN + "PACKEDICE", "");
        headblock1[12] = Item.createitem(Material.NETHERRACK, 1, ChatColor.GREEN + "NETHERRACK", "");
        headblock1[13] = Item.createitem(Material.ENDER_STONE, 1, ChatColor.GREEN + "ENDERSTONE", "");
        headblock1[14] = Item.createitem(Material.OBSIDIAN, 1, ChatColor.GREEN + "OBSIDIAN", "");

        ItemStack[] headblock2 = new ItemStack[15];
        headblock2[0] = Item.createitem(Material.PRISMARINE, 1, ChatColor.GREEN + "PRISMARINE", "");
        headblock2[1] = Item.createitem(Material.SEA_LANTERN, 1, ChatColor.GREEN + "SEALANTERN", "");
        headblock2[2] = Item.createitem(Material.SPONGE, 1, ChatColor.GREEN + "SPONGE", "");
        headblock2[3] = Item.createitem(Material.NETHER_BRICK, 1, ChatColor.GREEN + "NETHERBRICK", "");
        headblock2[4] = Item.createitem(Material.MAGMA, 1, ChatColor.GREEN + "MAGMA", "");
        headblock2[5] = Item.createitem(Material.PURPUR_BLOCK, 1, ChatColor.GREEN + "PURPURBLOCK", "");
        headblock2[6] = Item.createitem(Material.IRON_BLOCK, 1, ChatColor.GREEN + "IRONBLOCK", "");
        headblock2[7] = Item.createitem(Material.GOLD_BLOCK, 1, ChatColor.GREEN + "GOLDBLOCK", "");
        headblock2[8] = Item.createitem(Material.DIAMOND_BLOCK, 1, ChatColor.GREEN + "DIAMONDBLOCK", "");
        headblock2[9] = Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.GREEN + "REDSTONEBLOCK", "");
        headblock2[10] = Item.createitem(Material.LAPIS_BLOCK, 1, ChatColor.GREEN + "LAPISBLOCK", "");
        headblock2[11] = Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN + "EMERALDBLOCK", "");
        headblock2[12] = Item.createitem(Material.COAL_BLOCK, 1, ChatColor.GREEN + "COALBLOCK", "");
        headblock2[13] = Item.createitem(Material.QUARTZ_BLOCK, 1, ChatColor.GREEN + "QUARTZBLOCK", "");
        headblock2[14] = Item.createitem(Material.GLOWSTONE, 1, ChatColor.GREEN + "GLOWSTONE", "");

        ItemStack[] headblock3 = new ItemStack[15];
        headblock3[0] = Item.createitem(Material.WORKBENCH, 1, ChatColor.GREEN + "WORKBENCH", "");
        headblock3[1] = Item.createitem(Material.FURNACE, 1, ChatColor.GREEN + "FURNACE", "");
        headblock3[2] = Item.createitem(Material.NOTE_BLOCK, 1, ChatColor.GREEN + "NOTE_BLOCK", "");
        headblock3[3] = Item.createitem(Material.ANVIL, 1, ChatColor.GREEN + "ANVIL", "");
        headblock3[4] = Item.createitem(Material.BREWING_STAND_ITEM, 1, ChatColor.GREEN + "BREWING_STAND", "");
        headblock3[5] = Item.createitem(Material.CAULDRON_ITEM, 1, ChatColor.GREEN + "CAULDRON", "");
        headblock3[6] = Item.createitem(Material.CHEST, 1, ChatColor.GREEN + "CHEST", "");
        headblock3[7] = Item.createitem(Material.ENDER_CHEST, 1, ChatColor.GREEN + "ENDER_CHEST", "");
        headblock3[8] = Item.createitem(Material.BEACON, 1, ChatColor.GREEN + "BEACON", "");
        headblock3[9] = Item.createitem(Material.DISPENSER, 1, ChatColor.GREEN + "DISPENSER", "");
        headblock3[10] = Item.createitem(Material.DROPPER, 1, ChatColor.GREEN + "DROPPER", "");
        headblock3[11] = Item.createitem(Material.OBSERVER, 1, ChatColor.GREEN + "OBSERVER", "");
        headblock3[12] = Item.createitem(Material.ENDER_PORTAL_FRAME, 1, ChatColor.GREEN + "ENDER_PORTAL_FRAME", "");
        headblock3[13] = Item.createitem(Material.ENCHANTMENT_TABLE, 1, ChatColor.GREEN + "ENCHANTMENT_TABLE", "");
        headblock3[14] = Item.createitem(Material.HOPPER, 1, ChatColor.GREEN + "HOPPER", "");

        ItemStack[] headblock4 = new ItemStack[15];
        headblock4[0] = Item.createitem(Material.TORCH, 1, ChatColor.GREEN + "TORCH", "");
        headblock4[1] = Item.createitem(Material.LEVER, 1, ChatColor.GREEN + "LEVER", "");
        headblock4[2] = Item.createitem(Material.END_ROD, 1, ChatColor.GREEN + "ENDROD", "");
        headblock4[3] = Item.createitem(Material.YELLOW_FLOWER, 1, ChatColor.GREEN + "YELLOWFLOWER", "");
        headblock4[4] = Item.createitem(Material.RED_ROSE, 1, ChatColor.GREEN + "REDROSE", "");
        headblock4[5] = Item.changename(new ItemStack(Material.RED_ROSE,1, (short) 1),ChatColor.GREEN +"VANDA","");
        headblock4[6] = Item.createitem(Material.BONE, 1, ChatColor.GREEN + "BONE", "");
        headblock4[7] = Item.createitem(Material.SIGN, 1, ChatColor.GREEN + "SIGN", "");
        headblock4[8] = Item.createitem(Material.FENCE_GATE, 1, ChatColor.GREEN + "FENCEGATE", "");
        headblock4[9] = Item.createitem(Material.SNOW, 1, ChatColor.GREEN + "SNOW", "");
        headblock4[10] = Item.createitem(Material.IRON_TRAPDOOR, 1, ChatColor.GREEN + "IRONTRAPDOOR", "");
        headblock4[11] = Item.createitem(Material.DAYLIGHT_DETECTOR, 1, ChatColor.GREEN + "DAYLIGHTDETECTOR", "");
        headblock4[12] = Item.createitem(Material.MOB_SPAWNER, 1, ChatColor.GREEN + "MOBSPAWNER", "");
        headblock4[13] = Item.createitem(Material.DRAGON_EGG, 1, ChatColor.GREEN + "DRAGONEGG", "");
        headblock4[14] = Item.createitem(Material.CACTUS, 1, ChatColor.GREEN + "CACTUS", "");

        headblock.add(headblock1);
        headblock.add(headblock2);
        headblock.add(headblock3);
        headblock.add(headblock4);
    }
    
    
    
}
