package net.oriserver.aether.aether.TNTRun;

import net.oriserver.aether.aether.events.AnvilClickEvent;
import net.oriserver.aether.aether.events.CreateTNTRunStageClickItemEvent;
import net.oriserver.aether.aether.events.CreateTNTRunStageInventoryEvent;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CreateStage implements Listener {

    private String creatorName;
    private String name;
    private double x1,y1,z1,x2,y2,z2;
    private int maxPlayer;
    private int minPlayer;
    private double speed = 5;
    private int deathLine;
    private double sx,sy,sz;
    private CreateStageManager createStageManager;

    Inventory invCreateStage = Bukkit.createInventory(null, 27, "TNTRun_CreateStage");
    public CreateStage(String creatorName,CreateStageManager createStageManager){
        this.createStageManager = createStageManager;
        this.creatorName = creatorName;
        invCreateStage.setItem(0, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "名前を設定してください", ""));
        invCreateStage.setItem(1, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "範囲選択1を設定してください", "",ChatColor.YELLOW+"クリックして設定ピッケルを入手"));
        invCreateStage.setItem(2, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "範囲選択2を設定してください", "",ChatColor.YELLOW+"クリックして設定ピッケルを入手"));
        invCreateStage.setItem(3, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "最低人数を設定してください", ""));
        invCreateStage.setItem(4, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "最高人数を設定してください", ""));
        invCreateStage.setItem(5, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "デスラインを設定してください", ChatColor.WHITE+"設定したいところに多ってクリック"));
        invCreateStage.setItem(6, Item.createitem(Material.GOLD_BLOCK, 1, ChatColor.WHITE + "ブロックの消える速さ:"+speed,"",ChatColor.YELLOW+"クリックして設定できます"));
        invCreateStage.setItem(7, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "スポーン位置を設定してください", ChatColor.WHITE+"設定したいところに多ってクリック"));

        invCreateStage.setItem(18,Item.createitem(Material.WORKBENCH, 1, "この内容でステージ作成する", ""));
        invCreateStage.setItem(19,Item.createitem(Material.CACTUS, 1, "作成をやめる", ChatColor.DARK_RED+"すべての設定が消えます"));

        invCreateStage.setItem(26,Item.createitem(Material.BARRIER, 1, "閉じる", ""));
    }

    @EventHandler
    public void clickInv(CreateTNTRunStageInventoryEvent e){
        int slot = e.getSlot();
        Player player = e.getPlayer();
        if(!player.getName().equals(creatorName))return;
        if(slot == 0){
            openAnvilInterface(player,"TNTRun_StageName");
        }else if(slot == 1){
            player.getInventory().addItem(Item.createitem(Material.WOOD_PICKAXE, 1, "TNTRun_Setting", ChatColor.WHITE+"左クリックで、選択範囲1の設定",ChatColor.WHITE+"右クリックで、選択範囲2の設定"));
            player.closeInventory();
        }else if(slot == 2){
            player.getInventory().addItem(Item.createitem(Material.WOOD_PICKAXE, 1, "TNTRun_Setting", ChatColor.WHITE+"左クリックで、選択範囲1の設定",ChatColor.WHITE+"右クリックで、選択範囲2の設定"));
        }else if(slot == 3){
            openAnvilInterface(player,"TNTRun_MaxPlayer");
        }else if(slot == 4){
            openAnvilInterface(player,"TNTRun_MinPlayer");
        }else if(slot == 5){
            deathLine = (int)player.getLocation().getY();
            player.sendMessage("デスラインが Y="+deathLine+"に設定されました");
            invCreateStage.setItem(5, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"DeathLine: "+ChatColor.WHITE + deathLine, ""));
            player.closeInventory();
        }else if(slot == 6){
            openAnvilInterface(player,"TNTRun_DisappearSpeed");
        }else if(slot == 7){
            Location location = player.getLocation();
            sx = location.getX();
            sy = location.getY();
            sz = location.getZ();
            player.sendMessage("スポーン位置が X="+sx+" Y="+sy+" Z="+sz+"に設定されました");
            invCreateStage.setItem(7, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"SpawnPoint: "+ChatColor.WHITE + "X="+sx+" Y="+sy+" Z="+sz, ""));
            player.closeInventory();
        }else if(slot == 18){
            createStageManager.completeStage(player);
        }else if(slot==19){
            createStageManager.quitStage(player);
        }
    }

    @EventHandler
    public void onItemClick(CreateTNTRunStageClickItemEvent event){
        if(!event.getEvent().getPlayer().getName().equals(creatorName))return;
        PlayerInteractEvent e = event.getEvent();
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Location l = e.getClickedBlock().getLocation();
            x1 = l.getX();
            y1 = l.getY();
            z1 = l.getZ();
            e.getPlayer().sendMessage("FirstPoint" + "x:"+x1+" y:"+y1+" z:"+z1);
            invCreateStage.setItem(2, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"FirstPoint: " + "X="+x1+" Y="+y1+" Z="+z1, ""));
        }
        else if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            Location l = e.getClickedBlock().getLocation();
            x2 = l.getX();
            y2 = l.getY();
            z2 = l.getZ();
            e.getPlayer().sendMessage("SecondPoint" + "x:"+x2+" y:"+y2+" z:"+z2);
            invCreateStage.setItem(2, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"SecondPoint: " + "X="+x2+" Y="+y2+" Z="+z2, ""));
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

    @EventHandler
    public void onInventoryClick(AnvilClickEvent event) {
        InventoryClickEvent e = event.getEvent();
        if(e.getRawSlot() == 2) {
            ItemStack item = e.getCurrentItem();
            if(item != null && item.getType() != Material.AIR && item.getType() == Material.PAPER) {
                ItemStack old_item = e.getView().getItem(0);
                if(old_item == null || old_item.getType() == Material.AIR || old_item.getType() != Material.PAPER)return;
                String inputValue = old_item.getItemMeta().getDisplayName();
                Player player = (Player) e.getWhoClicked();
                switch(inputValue) {
                    case "TNTRun_StageName":
                        name = item.getItemMeta().getDisplayName();
                        player.sendMessage(ChatColor.GREEN + "ステージの名前が " + name + " に設定されました!");
                        invCreateStage.setItem(0, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"StageName: "+ChatColor.WHITE + name, ""));
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,100,100);
                        break;
                    case "TNTRun_MaxPlayer":
                        try {
                            maxPlayer = Integer.parseInt(item.getItemMeta().getDisplayName());
                            player.sendMessage(ChatColor.GREEN + "最高人数が " + minPlayer + " に設定されました!");
                            invCreateStage.setItem(0, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"MaxPlayer: "+ChatColor.WHITE + maxPlayer, ""));
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,100,100);
                        } catch (NumberFormatException ex) {
                            player.sendMessage(ChatColor.RED + "無効な数値です!");
                        }
                        break;
                    case "TNTRun_MinPlayer":
                        try {
                            minPlayer = Integer.parseInt(item.getItemMeta().getDisplayName());
                            player.sendMessage(ChatColor.GREEN + "最低人数が " + minPlayer + " に設定されました!");
                            invCreateStage.setItem(0, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"MinPlayer: "+ChatColor.WHITE + minPlayer, ""));
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,100,100);
                        } catch (NumberFormatException ex) {
                            player.sendMessage(ChatColor.RED + "無効な数値です!");
                        }
                        break;
                    case "TNTRun_DisappearSpeed":
                        try {
                            speed = Integer.parseInt(item.getItemMeta().getDisplayName());
                            player.sendMessage(ChatColor.GREEN + "ブロックの消えるスピードが " + speed + " に設定されました!");
                            invCreateStage.setItem(0, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"Speed: "+ChatColor.WHITE + speed, ""));
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,100,100);
                        } catch (NumberFormatException ex) {
                            player.sendMessage(ChatColor.RED + "無効な数値です!");
                        }
                        break;
                }
            }
        }
    }
    public Object[] getData(){
        Object[] objects = new Object[13];
        objects[0] = x1;
        objects[1] = y1;
        objects[2] = z1;
        objects[3] = x2;
        objects[4] = y2;
        objects[5] = z2;
        objects[6] = maxPlayer;
        objects[7] = minPlayer;
        objects[8] = speed;
        objects[9] = deathLine;
        objects[10] = sx;
        objects[11] = sy;
        objects[12] = sz;
        return objects;
    }
}
