package net.oriserver.aether.aether.statics;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
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

public class Item {//基本的にItemを操作する共通メソッドをまとめたクラス
    
    static final private ItemStack[] item_partition = new ItemStack[20];//スマホ開く際の装飾アイテムを保存
    static final private ItemStack[] item_phone = new ItemStack[7];//スマホの装飾アイテムを保存
    static final private ItemStack[] first_inv = new ItemStack[9];//join時のfirstInvを保存
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

        item_phone[0] = createitem(Material.IRON_DOOR, 1, ChatColor.AQUA + "Aether Phone", "");
        item_phone[1] = createitem(Material.WOOD_DOOR, 1, ChatColor.AQUA + "Aether Phone", "");
        item_phone[2] = createitem(Material.BIRCH_DOOR_ITEM, 1, ChatColor.AQUA + "Aether Phone", "");
        item_phone[3] = createitem(Material.ACACIA_DOOR_ITEM, 1, ChatColor.AQUA + "Aether Phone", "");
        item_phone[4] = createitem(Material.DARK_OAK_DOOR_ITEM, 1, ChatColor.AQUA + "Aether Phone", "");
        item_phone[5] = createitem(Material.JUNGLE_DOOR_ITEM, 1, ChatColor.AQUA + "Aether Phone", "");
        item_phone[6] = createitem(Material.PAINTING, 1, ChatColor.AQUA + "Aether Phone", "");

        first_inv[0] = createitem(Material.IRON_BARDING,1,ChatColor.DARK_AQUA+"checkpoint","");
        first_inv[1] = new ItemStack(Material.AIR);
        first_inv[2] = new ItemStack(Material.AIR);
        first_inv[3] = new ItemStack(Material.AIR);
        first_inv[4] = createitem(Material.EYE_OF_ENDER,1,ChatColor.GREEN+"Visible","");
        first_inv[5] = new ItemStack(Material.AIR);
        first_inv[6] = new ItemStack(Material.AIR);
        first_inv[7] = new ItemStack(Material.AIR);
        first_inv[8] = createitem(Material.IRON_DOOR,1,ChatColor.AQUA+"AetherPhone","");

        player_partition = new HashMap<>();
    }

    static public final HashMap<String,Integer> player_partition;//プレイヤーごとに装飾アイテムの番号を保存

    static public ItemStack createitem(final Material material, int a, final String name, final String... lore){//アイテムの作成
        final ItemStack item = new ItemStack(material,a);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
    static public ItemStack changename(final ItemStack i,final String name, final String... lore){//アイテムの名前変更
        final ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        i.setItemMeta(meta);
        return i;
    }
    static public ItemStack createitem2(final Material material,int a,final String name, final String... lore){//エンチャント付きアイテムの作成
        final ItemStack item = new ItemStack(material,a);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        meta.addEnchant(Enchantment.LUCK,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
    static public ItemStack enachantitem(ItemStack item){//アイテムにエンチャントをつける
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.LUCK,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
    static public Inventory inventorycopy(Inventory a){//Inventoryクラスの内容を全てコピー
        String title = a.getTitle();
        ItemStack[] allItem = a.getContents();
        a.setContents(allItem);
        Inventory copyInv = Bukkit.createInventory(null,54,title);
        copyInv.setContents(allItem);
        return copyInv;
    }
    static public ItemStack getHead(String player_name) {//プレイヤーの頭アイテムを入手
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1,(short) SkullType.PLAYER.ordinal());
        SkullMeta skullmeta = (SkullMeta) item.getItemMeta();
        skullmeta.setOwningPlayer(Bukkit.getPlayer(player_name));
        skullmeta.setDisplayName(player_name);
        item.setItemMeta(skullmeta);
        return item;
    }
    static public ItemStack getHead(String player_name,final String... lore) {//プレイヤーの頭アイテムを入手し説明を付与
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1,(short) SkullType.PLAYER.ordinal());
        SkullMeta skullmeta = (SkullMeta) item.getItemMeta();
        skullmeta.setOwningPlayer(Bukkit.getPlayer(player_name));
        skullmeta.setDisplayName(player_name);
        skullmeta.setLore(Arrays.asList(lore));
        item.setItemMeta(skullmeta);
        return item;
    }
    static public ItemStack getHead2(String value) {//プロパティから頭アイテムを入手
        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(skull,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}"
        );
    }

    static public void setInventory(Player p, Inventory inv){//装飾を含めたInventoryの作成
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

    static public void getItemPhone(Player p,int number){//スマホを入手、持っていた場合は更新
        boolean b = false;
        for(int i=0;i<35;i++) {
            if (p.getInventory().getItem(i) == null) continue;
            if (p.getInventory().getItem(i).getItemMeta().hasDisplayName() && p.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Aether Phone")){
                p.getInventory().setItem(i, item_phone[number]);
                b = true;
            }
        }
        if(!b){
            if(p.getInventory().getItem(8) == null)p.getInventory().setItem(8, item_phone[number]);
            else p.getInventory().addItem(item_phone[number]);
        }
    }

    static public void getFirstInventory(Player p,int phone_number){//プレイヤーjoin時にアイテムを設定
        Inventory player_inventory = p.getInventory();
        for(int i=0;i<=8;i++){
            player_inventory.setItem(i,first_inv[i]);
        }
        player_inventory.setItem(8,item_phone[phone_number]);

        if(p.getLocation().getWorld().equals(Bukkit.getWorld("shrine"))){
            p.getInventory().setItem(7,createitem(Material.IRON_INGOT,1,ChatColor.AQUA+"Teleport option"));
        }
        else if(p.getLocation().getWorld().equals(Bukkit.getWorld("chart"))){
            p.getInventory().setItem(7,createitem(Material.IRON_INGOT,1,ChatColor.AQUA+"Teleport option"));
        }
        else if(p.getLocation().getWorld().equals(Bukkit.getWorld("global"))){
            p.getInventory().setItem(7,createitem(Material.IRON_INGOT,1,ChatColor.AQUA+"Teleport option"));
        }

        if(p.isOp()){
            p.getInventory().setItem(2,createitem(Material.FEATHER,1,ChatColor.YELLOW+"left click:change gamemode",""));
        }
    }

    static public void removeCustomNamedItemFromInventory(Inventory inventory, Material material, String customName) {//指定した名前のアイテムをプレイヤーから削除
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == material && item.hasItemMeta() && customName.equals(item.getItemMeta().getDisplayName())) {
                inventory.remove(item);
            }
        }
    }

    public static void setItemNameNMS(net.minecraft.server.v1_12_R1.ItemStack itemStack, String name) {//NMSをつかったアイテムの作成
        NBTTagCompound tagCompound = itemStack.getTag();

        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            itemStack.setTag(tagCompound);
        }

        NBTTagCompound display = tagCompound.getCompound("display");

        if (!tagCompound.hasKey("display")) {
            tagCompound.set("display", display);
        }

        display.setString("Name", name);
    }




}
