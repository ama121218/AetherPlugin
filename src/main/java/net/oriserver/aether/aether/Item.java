package net.oriserver.aether.aether;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class Item {
    
    static final private ItemStack[] item_partition = new ItemStack[20];
    static final private ItemStack[] item_phone = new ItemStack[7];
    static {
        item_partition[0] = createitem(Material.IRON_FENCE, 1, "", "");
        item_partition[1] = createitem(Material.THIN_GLASS, 1, "", "");
        item_partition[2] = createitem(Material.RAILS, 1, "", "");
        item_partition[3] = createitem(Material.POWERED_RAIL, 1, "", "");
        item_partition[4] = createitem(Material.ACTIVATOR_RAIL, 1, "", "");
        item_partition[5] = createitem(Material.DETECTOR_RAIL, 1, "", "");
        item_partition[6] = createitem(Material.LADDER, 1, "", "");
        item_partition[7] = createitem(Material.VINE, 1, "", "");
        item_partition[8] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "", "");
        item_partition[9] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13), "", "");
        item_partition[10] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11), "", "");
        item_partition[11] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4), "", "");
        item_partition[12] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 2), "", "");
        item_partition[13] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3), "", "");
        item_partition[14] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 6), "", "");
        item_partition[15] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1), "", "");
        item_partition[16] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "", "");
        item_partition[17] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15), "", "");
        item_partition[18] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0), "", "");
        item_partition[19] = changename(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 12), "", "");

        item_phone[0] = createitem(Material.IRON_DOOR, 1, ChatColor.AQUA + "AetherPhone", "");
        item_phone[1] = createitem(Material.WOOD_DOOR, 1, ChatColor.AQUA + "AetherPhone", "");
        item_phone[2] = createitem(Material.BIRCH_DOOR_ITEM, 1, ChatColor.AQUA + "AetherPhone", "");
        item_phone[3] = createitem(Material.ACACIA_DOOR_ITEM, 1, ChatColor.AQUA + "AetherPhone", "");
        item_phone[4] = createitem(Material.DARK_OAK_DOOR_ITEM, 1, ChatColor.AQUA + "AetherPhone", "");
        item_phone[5] = createitem(Material.JUNGLE_DOOR_ITEM, 1, ChatColor.AQUA + "AetherPhone", "");
        item_phone[6] = createitem(Material.PAINTING, 1, ChatColor.AQUA + "AetherPhone", "");

        player_head = new HashMap<>();
        player_partition = new HashMap<>();
    }
    static public final HashMap<String,ItemStack> player_head;
    static public final HashMap<String,Integer> player_partition;

    static public ItemStack createitem(final Material material, int a, final String name, final String... lore){
        final ItemStack item = new ItemStack(material,a);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
    static public ItemStack changename(final ItemStack i,final String name, final String... lore){
        final ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        i.setItemMeta(meta);
        return i;
    }
    static public ItemStack createitem2(final Material material,int a,final String name, final String... lore){
        final ItemStack item = new ItemStack(material,a);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        meta.addEnchant(Enchantment.LUCK,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
    static public ItemStack enachantitem(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.LUCK,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
    static public Inventory inventorycopy(Inventory a){
        ItemStack[] allitem;
        String t = a.getTitle();
        allitem = a.getContents();
        a.setContents(allitem);
        Inventory copyinv = Bukkit.createInventory(null,54,t);
        copyinv.setContents(allitem);
        return copyinv;
    }
    static public ItemStack getHead(String player_name) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1,(short) SkullType.PLAYER.ordinal());
        SkullMeta skullmeta = (SkullMeta) item.getItemMeta();
        skullmeta.setOwningPlayer(Bukkit.getPlayer(player_name));
        skullmeta.setDisplayName(player_name);
        item.setItemMeta(skullmeta);
        return item;
    }
    static public ItemStack getHead(String player_name,final String... lore) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1,(short) SkullType.PLAYER.ordinal());
        SkullMeta skullmeta = (SkullMeta) item.getItemMeta();
        skullmeta.setOwningPlayer(Bukkit.getPlayer(player_name));
        skullmeta.setDisplayName(player_name);
        skullmeta.setLore(Arrays.asList(lore));
        item.setItemMeta(skullmeta);
        return item;
    }
    static public ItemStack getHead2(String value) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(skull,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}"
        );
    }
    static public void setInventory(Player p, Inventory inv){
        int itemnumber = 0;
        if(player_partition.containsKey(String.valueOf(p.getUniqueId())))itemnumber = player_partition.get( String.valueOf(p.getUniqueId()));
        for (int i = 2; i <= 47; i += 9) {
            inv.setItem(i,item_partition[itemnumber]);
        }
        for (int i = 6; i <= 51; i += 9) {
            inv.setItem(i,item_partition[itemnumber]);
        }
        p.openInventory(inv);
    }
}
