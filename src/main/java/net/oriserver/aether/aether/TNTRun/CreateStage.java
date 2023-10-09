package net.oriserver.aether.aether.TNTRun;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CreateStage {

    private String name;
    private double x1,y1,z1,x2,y2,z2;
    private int maxPlayer;
    private int minPlayer;
    private double speed;
    private int deathLine;
    private double sx,sy,sz;

    Inventory invCreateStage = Bukkit.createInventory(null, 27, "TNTRun_CreateStage");
    public CreateStage(){
        invCreateStage.setItem(0, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "名前を設定してください", ""));
        invCreateStage.setItem(1, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "範囲選択1を設定してください", "",ChatColor.YELLOW+"クリックして設定ピッケルを入手"));
        invCreateStage.setItem(2, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "範囲選択2を設定してください", "",ChatColor.YELLOW+"クリックして設定ピッケルを入手"));
        invCreateStage.setItem(3, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "最低人数を設定してください", ""));
        invCreateStage.setItem(4, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "最高人数を設定してください", ""));
        invCreateStage.setItem(4, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "デスラインを設定してください", ChatColor.WHITE+"設定したいところに多ってクリック"));
        invCreateStage.setItem(5, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "ブロックの消える速さを設定してください", ""));
        invCreateStage.setItem(6, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "スポーン位置を設定してください", ChatColor.WHITE+"設定したいところに多ってクリック"));

        invCreateStage.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));
    }
    public void clickInv(Player player, int slot){
        if(slot == 0){
            openAnvilInterface(player,"TNTRun_StageName");
        }else if(slot == 1){
            player.getInventory().addItem(Item.createitem(Material.WOOD_PICKAXE, 1, "TNTRun_Setting", ChatColor.WHITE+"左クリックで、選択範囲1の設定",ChatColor.WHITE+"右クリックで、選択範囲2の設定"));
        }else if(slot == 2){
            player.getInventory().addItem(Item.createitem(Material.WOOD_PICKAXE, 1, "TNTRun_Setting", ChatColor.WHITE+"左クリックで、選択範囲1の設定",ChatColor.WHITE+"右クリックで、選択範囲2の設定"));
        }else if(slot == 3){
            openAnvilInterface(player,"TNTRun_MaxPlayer");
        }else if(slot == 4){
            openAnvilInterface(player,"TNTRun_MinPlayer");
        }else if(slot == 5){

        }else if(slot == 6){
            openAnvilInterface(player,"TNTRun_DisappearSpeed");
        }
    }

    private void openAnvilInterface(Player player, String type) {
        Inventory anvilInv = Bukkit.createInventory(player, InventoryType.ANVIL);
        ItemStack inputItem = new ItemStack(Material.PAPER); // このアイテムの名前を変更することで各設定を行う
        ItemMeta meta = inputItem.getItemMeta();
        meta.setDisplayName(type); // typeをデフォルトの名前として設定
        inputItem.setItemMeta(meta);
        anvilInv.setItem(0, inputItem);
        player.openInventory(anvilInv);
    }


    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getRawSlot() == 2) {
            ItemStack item = event.getCurrentItem();
            if(item != null && item.getType() != Material.AIR) {
                String inputValue = item.getItemMeta().getDisplayName();
                Player player = (Player) event.getWhoClicked();
                switch(inputValue) {
                    case "TNTRun_StageName":
                        name = inputValue;
                        player.sendMessage(ChatColor.GREEN + "ステージの名前が " + name + " に設定されました!");
                        break;
                    case "TNTRun_MaxPlayer":
                        try {
                            maxPlayer = Integer.parseInt(inputValue);
                            player.sendMessage(ChatColor.GREEN + "最高人数が " + minPlayer + " に設定されました!");
                        } catch (NumberFormatException e) {
                            player.sendMessage(ChatColor.RED + "無効な数値です!");
                        }
                        break;
                    case "TNTRun_MinPlayer":
                        try {
                            minPlayer = Integer.parseInt(inputValue);
                            player.sendMessage(ChatColor.GREEN + "最低人数が " + minPlayer + " に設定されました!");
                        } catch (NumberFormatException e) {
                            player.sendMessage(ChatColor.RED + "無効な数値です!");
                        }
                        break;
                    case "TNTRun_DisappearSpeed":
                        try {
                            speed = Integer.parseInt(inputValue);
                            player.sendMessage(ChatColor.GREEN + "ブロックの消えるスピードが " + minPlayer + " に設定されました!");
                        } catch (NumberFormatException e) {
                            player.sendMessage(ChatColor.RED + "無効な数値です!");
                        }
                        break;
                }
            }
        }
    }

}
