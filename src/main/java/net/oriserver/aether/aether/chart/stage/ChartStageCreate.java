package net.oriserver.aether.aether.chart.stage;

import net.minecraft.server.v1_12_R1.*;
import net.oriserver.aether.aether.chart.ChartStageCreateManager;
import net.oriserver.aether.aether.chart.events.ChartCreateToolClickEvent;
import net.oriserver.aether.aether.events.AnvilClickEvent;
import net.oriserver.aether.aether.events.CreateChartStageInventoryEvent;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ChartStageCreate implements Listener {

    private final String creator_name;
    private final String stage_id;
    private String stage_name;
    private Double stage_tp_x;
    private Double stage_tp_y;
    private Double stage_tp_z;
    private Float stage_tp_yaw;
    private Float stage_tp_pitch;
    private Double start_x;
    private Double start_y;
    private Double start_z;
    private Double start_tp_x;
    private Double start_tp_y;
    private Double start_tp_z;
    private Float start_tp_yaw;
    private Float start_tp_pitch;
    private Double goal_x;
    private Double goal_y;
    private Double goal_z;
    private Double goal_tp_x;
    private Double goal_tp_y;
    private Double goal_tp_z;
    private Float goal_tp_yaw;
    private Float goal_tp_pitch;
    private Long star_time_3 = 0L;
    private Long star_time_2 = 0L;
    private Long star_time_1 = 0L;


    private ArrayList<Double[]> checkPoint_list;
    private final ChartStageCreateManager createChartStageManager;

    private final Inventory invCreateStage;

    public ChartStageCreate(String creator_name, String stage_id, ChartStageCreateManager createChartStageManager){
        this.createChartStageManager = createChartStageManager;
        this.creator_name = creator_name;
        this.stage_id = stage_id;
        checkPoint_list = new ArrayList<>();

        invCreateStage = Bukkit.createInventory(null, 27, stage_id + " " + "CreateChartStage");

        invCreateStage.setItem(0, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "名前を設定してください", "",ChatColor.YELLOW+"クリックで入力へ"));
        invCreateStage.setItem(1, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "ステージのtp先を設定してください", "",ChatColor.YELLOW+"設定位置に立ってクリック"));
        invCreateStage.setItem(2, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "ステージの開始位置を設定してください", "",ChatColor.YELLOW+"クリックして石の感圧板を入手"));
        invCreateStage.setItem(3, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "ステージの開始tp先を設定してください", "",ChatColor.YELLOW+"設定位置に立ってクリック"));
        invCreateStage.setItem(4, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "ステージの終了位置を設定してください", "",ChatColor.YELLOW+"クリックして金の感圧板を入手"));
        invCreateStage.setItem(5, Item.createitem(Material.REDSTONE_BLOCK, 1, ChatColor.WHITE + "ステージの終了tp先を設定してください", "",ChatColor.YELLOW+"設定位置に立ってクリック"));

        invCreateStage.setItem(6, Item.createitem(Material.GOLD_BLOCK, 1, ChatColor.WHITE + "☆3タイムを設定してください", "",ChatColor.YELLOW+"クリックで入力へ"));
        invCreateStage.setItem(7, Item.createitem(Material.GOLD_BLOCK, 1, ChatColor.WHITE + "☆2タイムを設定してください", "",ChatColor.YELLOW+"クリックで入力へ"));
        invCreateStage.setItem(8, Item.createitem(Material.GOLD_BLOCK, 1, ChatColor.WHITE + "☆1タイムを設定してください","",ChatColor.YELLOW+"クリックで入力へ"));

        invCreateStage.setItem(9, Item.createitem(Material.STRUCTURE_VOID, 1, ChatColor.WHITE + "CheckPointを増やす", ChatColor.WHITE+"クリックして設定アイテムをもらう"));

        invCreateStage.setItem(18,Item.createitem(Material.WORKBENCH, 1, "この内容でステージ作成する", ""));
        invCreateStage.setItem(19,Item.createitem(Material.CACTUS, 1, "作成をやめる", ChatColor.DARK_RED+"すべての設定が消えます"));

        invCreateStage.setItem(23,Item.createitem(Material.IRON_PLATE, 1, "連続でチェックポイントを設定します", ChatColor.GREEN+"右クリックで追加します"));
        invCreateStage.setItem(24,Item.createitem(Material.BARRIER, 1, "途中のチェックポイントを削除", ChatColor.DARK_RED+"指定したチェックポイントが消えます"));

        invCreateStage.setItem(26,Item.createitem(Material.BARRIER, 1, "閉じる", ""));
    }



    @EventHandler
    public void clickInv(CreateChartStageInventoryEvent e){
        int slot = e.getSlot();
        Material material = e.getMaterial();
        Player player = e.getPlayer();
        String title = ChatColor.stripColor(e.getTitle());
        if(!player.getName().equals(creator_name))return;
        if(title.charAt(0) == 'D'){
            player.sendMessage(slot+"/"+checkPoint_list.size()+"を消しました");
            checkPoint_list.remove(slot);
            setCheckPointInventory();
            player.openInventory(invCreateStage);
            return;
        }
        if(slot == 0) {
            openAnvilInterface(player,"ChartStageName");
        }else if(slot == 1){
            Location location = player.getLocation();
            stage_tp_x = location.getX();stage_tp_y = location.getY();stage_tp_z = location.getZ();stage_tp_yaw = location.getYaw();stage_tp_pitch = location.getPitch();
            player.sendMessage("StageTPLocation = x:"+ stage_tp_x + " " + "y:" + stage_tp_y + " " + "z:" + stage_tp_z);
            invCreateStage.setItem(1, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"StageTPLocation","",ChatColor.WHITE+ "x:" + stage_tp_x + " " + "y:" + stage_tp_y + " " + "z:" + stage_tp_z));
            player.closeInventory();
        }else if(slot == 2){
            player.getInventory().addItem(Item.createitem(Material.STONE_PLATE, 1, ChatColor.WHITE+"SetStartLocation", "",ChatColor.WHITE+"右クリックでスタート位置設定"));
            player.closeInventory();
        }else if(slot == 3){
            Location location = player.getLocation();
            start_tp_x = location.getX();start_tp_y = location.getY();start_tp_z = location.getZ();start_tp_yaw = location.getYaw();start_tp_pitch = location.getPitch();
            player.sendMessage("StartTPLocation = x:"+ start_tp_x + " " + "y:" + start_tp_y + " " + "z:" +start_tp_z);
            invCreateStage.setItem(3, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"StartTPLocation","",ChatColor.WHITE+ "x:" + start_tp_x + " " + "y:" + start_tp_y + " " + "z:" + start_tp_z));
            player.closeInventory();
        }else if(slot == 4){
            player.getInventory().addItem(Item.createitem(Material.GOLD_PLATE, 1, ChatColor.WHITE+"SetGoalLocation", "",ChatColor.WHITE+"右クリックでゴール位置設定"));
            player.closeInventory();
        }else if(slot == 5){
            Location location = player.getLocation();
            goal_tp_x = location.getX();goal_tp_y = location.getY();goal_tp_z = location.getZ();goal_tp_yaw = location.getYaw();goal_tp_pitch = location.getPitch();
            player.sendMessage("StartTPLocation = x:"+ goal_tp_x + " " + "y:" + goal_tp_y + " " + "z:" + goal_tp_z);
            invCreateStage.setItem(5, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"GoalTPLocation","",ChatColor.WHITE+ "x:" + goal_tp_x + " " + "y:" + goal_tp_y + " " + "z:" + goal_tp_z));
            player.closeInventory();
        }else if(slot == 6){
            openAnvilInterface(player,"StarTime3");
        }else if(slot == 7){
            openAnvilInterface(player,"StarTime2");
        }else if(slot == 8){
            openAnvilInterface(player,"StarTime1");
        }
        if(slot >= 9 && slot <=17 && material != null){
            player.getInventory().addItem(Item.createitem(Material.IRON_PLATE, 1, ChatColor.WHITE+"SetCheckpoint", ChatColor.WHITE+""+(slot+1-9),ChatColor.WHITE+"右クリックでチェックポイントを設定"));
            player.closeInventory();
        }else if(slot == 18){
            ArrayList<Object> list = getStageData();
            for(Object object:list) {
                if (object == null) {
                    player.sendMessage("作成中のステージに設定していないパラメータがあります");
                    player.closeInventory();
                    return;
                }
            }
            player.closeInventory();
            createChartStageManager.complete(player,list,getCheckPointList());
        }else if(slot==19)createChartStageManager.quit(player);
        else if(slot == 23 && material == Material.IRON_PLATE){
            player.getInventory().addItem(Item.createitem2(Material.IRON_PLATE, 1, ChatColor.WHITE+"SetCheckpoint ContinuousAddition", ChatColor.WHITE+""+(checkPoint_list.size()+1),ChatColor.WHITE+"右クリックでチェックポイントを連続設定"));
            player.closeInventory();
        }
        else if(slot == 24 && material == Material.BARRIER){
            Inventory deleteInvCreateStage = Bukkit.createInventory(null, 27, ChatColor.DARK_RED+"Delete"+" "+ stage_id + " " + "CreateCheckPoint");
            for(int i=0;i<checkPoint_list.size();i++){
                Double[] doubles = checkPoint_list.get(i);
                deleteInvCreateStage.setItem(i, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.WHITE + "Point :"+(i+1)+"/"+checkPoint_list.size(), ChatColor.WHITE+"x: "+doubles[0],ChatColor.WHITE+"y: "+doubles[1],ChatColor.WHITE+"z: "+doubles[2]));
            }
            player.openInventory(deleteInvCreateStage);
        }
        else if(slot==26)player.closeInventory();


    }

    public String getStage_id(){return this.stage_id;}
    public void openAnvilInterface(Player player, String type) {
        EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
        int containerId = nmsPlayer.nextContainerCounter();
        ContainerAnvil anvil = new ContainerAnvil(nmsPlayer.inventory, nmsPlayer.world, new BlockPosition(0, 0, 0), nmsPlayer);
        anvil.checkReachable = false;

        // ウィンドウのタイトルを設定
        nmsPlayer.playerConnection.sendPacket(new PacketPlayOutOpenWindow(containerId, "minecraft:anvil", new ChatMessage("Repair & Name"), 0));

        // BukkitのInventoryViewを取得し、アイテムをセット
        nmsPlayer.activeContainer = anvil;
        nmsPlayer.activeContainer.windowId = containerId;
        nmsPlayer.activeContainer.addSlotListener(nmsPlayer);

        // Anvilの0番スロットにペーパーをセット
        net.minecraft.server.v1_12_R1.ItemStack paper = new net.minecraft.server.v1_12_R1.ItemStack(Items.PAPER);
        Item.setItemNameNMS(paper, type);
        anvil.getSlot(0).set(paper);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(!p.isOp())return;
        if(!p.getName().equals(creator_name))return;
        ItemStack itemInHand = e.getItemInHand();
        if (itemInHand == null || !itemInHand.hasItemMeta() || !itemInHand.getItemMeta().hasLore() || !itemInHand.getItemMeta().hasDisplayName()) return;
        if (itemInHand.getType() == Material.IRON_PLATE){
            if (ChatColor.stripColor(itemInHand.getItemMeta().getDisplayName()).equals("SetCheckpoint") || ChatColor.stripColor(itemInHand.getItemMeta().getDisplayName()).equals("SetCheckpoint ContinuousAddition")) {
                int index = Integer.parseInt(ChatColor.stripColor(itemInHand.getItemMeta().getLore().get(0))) - 1;

                Location location = e.getBlock().getLocation();
                Double[] doubles = new Double[3];

                doubles[0] = location.getX();
                doubles[1] = location.getY();
                doubles[2] = location.getZ();

                if (index > 8) {
                    p.sendMessage("9個より多くは設定できません");
                    return;
                }
                if (index >= checkPoint_list.size()) checkPoint_list.add(doubles);
                else checkPoint_list.set(index, doubles);

                setCheckPointInventory();

                Item.removeCustomNamedItemFromInventory(p.getInventory(), Material.IRON_PLATE, ChatColor.WHITE + "SetCheckpoint");

                p.sendMessage("CheckPoint(" + (index + 1) + "/" + checkPoint_list.size() + ")を設定しました");

                if (ChatColor.stripColor(itemInHand.getItemMeta().getDisplayName()).equals("SetCheckpoint ContinuousAddition")) {
                    Item.removeCustomNamedItemFromInventory(p.getInventory(), Material.IRON_PLATE, ChatColor.WHITE + "SetCheckpoint ContinuousAddition");
                    p.getInventory().addItem(Item.createitem2(Material.IRON_PLATE, 1, ChatColor.WHITE + "SetCheckpoint ContinuousAddition", ChatColor.WHITE + "" + (checkPoint_list.size() + 1), ChatColor.WHITE + "右クリックでチェックポイントを連続設定"));
                }
            }
        }else if(itemInHand.getType() == Material.STONE_PLATE){
            if (ChatColor.stripColor(itemInHand.getItemMeta().getDisplayName()).equals("SetStartLocation")) {

                Location location = e.getBlock().getLocation();
                start_x = location.getX();
                start_y = location.getY();
                start_z = location.getZ();

                Item.removeCustomNamedItemFromInventory(p.getInventory(), Material.STONE_PLATE, ChatColor.WHITE + "SetStartLocation");

                p.sendMessage("StartLocation("+ChatColor.WHITE+ "x:" + start_x + " " + "y:" + start_y + " " + "z:" + start_z + ")を設定しました");
                invCreateStage.setItem(1, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"StartLocation","",ChatColor.WHITE+ "x:" + start_x + " " + "y:" + start_y + " " + "z:" + start_z));
            }
        }else if(itemInHand.getType() == Material.GOLD_PLATE){
            if (ChatColor.stripColor(itemInHand.getItemMeta().getDisplayName()).equals("SetGoalLocation")) {

                Location location = e.getBlock().getLocation();
                goal_x = location.getX();
                goal_y = location.getY();
                goal_z = location.getZ();

                Item.removeCustomNamedItemFromInventory(p.getInventory(), Material.GOLD_PLATE, ChatColor.WHITE + "GoalLocation");

                p.sendMessage("GoalLocation("+ChatColor.WHITE+ "x:" + goal_x + " " + "y:" + goal_y + " " + "z:" + goal_z + ")を設定しました");
                invCreateStage.setItem(3, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"GoalLocation","",ChatColor.WHITE+ "x:" + goal_x + " " + "y:" + goal_y + " " + "z:" + goal_z));
            }
        }
    }
    @EventHandler
    public void onInventoryClick(AnvilClickEvent event) {
        InventoryClickEvent e = event.getEvent();
        if(!e.getWhoClicked().getName().equals(creator_name))return;
        if(e.getRawSlot() == 2) {
            ItemStack item = e.getCurrentItem();
            if(item != null && item.getType() != Material.AIR && item.getType() == Material.PAPER) {
                ItemStack old_item = e.getView().getItem(0);
                if(old_item == null || old_item.getType() == Material.AIR || old_item.getType() != Material.PAPER)return;
                String inputValue = old_item.getItemMeta().getDisplayName();
                Player player = (Player) e.getWhoClicked();
                if(inputValue.equals("ChartStageName")){
                    stage_name = item.getItemMeta().getDisplayName();
                    player.sendMessage(ChatColor.GREEN + "ステージの名前が " + stage_name + " に設定されました!");
                    invCreateStage.setItem(0, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"StageName: "+ChatColor.WHITE + stage_name, ""));
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,100,100);
                    player.closeInventory();
                    Item.removeCustomNamedItemFromInventory(player.getInventory(), Material.PAPER, "ChartStageName");
                }else if(inputValue.equals("StarTime3")){
                    String temp = item.getItemMeta().getDisplayName();
                    Long time = getLongStarTime(temp);
                    if(time==null){player.sendMessage("設定できない数値です。 ex= 04:56:345");return;}
                    player.sendMessage(ChatColor.GREEN + "☆3タイムが " + temp + " に設定されました!");
                    invCreateStage.setItem(5, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"☆3タイム: "+ChatColor.WHITE + temp, ""));
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,100,100);
                    star_time_3 = time;
                    player.closeInventory();
                    Item.removeCustomNamedItemFromInventory(player.getInventory(), Material.PAPER, "StarTime3");
                }else if(inputValue.equals("StarTime2")){
                    String temp = item.getItemMeta().getDisplayName();
                    Long time = getLongStarTime(temp);
                    if(time==null){player.sendMessage("設定できない数値です。 ex= 04:56:345");return;}
                    player.sendMessage(ChatColor.GREEN + "☆2タイムが " + temp + " に設定されました!");
                    invCreateStage.setItem(6, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"☆2タイム: "+ChatColor.WHITE + temp, ""));
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,100,100);
                    star_time_2 = time;
                    player.closeInventory();
                    Item.removeCustomNamedItemFromInventory(player.getInventory(), Material.PAPER, "StarTime2");
                }else if(inputValue.equals("StarTime1")){
                    String temp = item.getItemMeta().getDisplayName();
                    Long time = getLongStarTime(temp);
                    if(time==null){player.sendMessage("設定できない数値です。 ex= 04:56:345");return;}
                    player.sendMessage(ChatColor.GREEN + "☆1タイムが " + temp + " に設定されました!");
                    invCreateStage.setItem(7, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"☆1タイム: "+ChatColor.WHITE + temp, ""));
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,100,100);
                    star_time_1 = time;
                    player.closeInventory();
                    Item.removeCustomNamedItemFromInventory(player.getInventory(), Material.PAPER, "StarTime1");
                }
            }
        }
    }
    public Long getLongStarTime(String time){
        try {
            String[] parts = time.split(":");
            if (parts.length != 3) {
                return null;
            }
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            int millis = Integer.parseInt(parts[2]);
            if (minutes < 0 || seconds < 0 || seconds >= 60 || millis < 0 || millis > 1000) {
                return null;
            }
            return (minutes * 60L + seconds) * 1000 + millis;
        }
        catch (NumberFormatException ex) {
            return null;
        }
    }
    @EventHandler
    public void onCreateToolClick(ChartCreateToolClickEvent e){
        Player p = e.getPlayer();
        if(!p.getName().equals(creator_name))return;
        p.openInventory(invCreateStage);
    }

    public void setCheckPointInventory(){
        for(int i=9;i<=17;i++)invCreateStage.clear(i);
        for(int i=0;i<checkPoint_list.size();i++){
            Double[] doubles = checkPoint_list.get(i);
            invCreateStage.setItem(i+9, Item.createitem(Material.IRON_BLOCK, 1, ChatColor.WHITE + "Point :"+(i+1)+"/"+checkPoint_list.size(), ChatColor.WHITE+"x: "+doubles[0],ChatColor.WHITE+"y: "+doubles[1],ChatColor.WHITE+"z: "+doubles[2]));
            if(i==checkPoint_list.size()-1){
                if(i<=7){
                    invCreateStage.setItem(i+1+9, Item.createitem(Material.STRUCTURE_VOID, 1, ChatColor.WHITE + "CheckPointを増やす", ChatColor.WHITE+"クリックして設定アイテムをもらう"));
                }
            }
        }
    }


    public void rework(ArrayList<Object> stage_list,ArrayList<Double[]> checkPoint_list) {

        this.checkPoint_list = checkPoint_list;

        stage_name = (String) stage_list.get(0);
        stage_tp_x =(Double)  stage_list.get(1);
        stage_tp_y = (Double) stage_list.get(2);
        stage_tp_z = (Double) stage_list.get(3);
        stage_tp_yaw = (Float) stage_list.get(4);
        stage_tp_pitch = (Float) stage_list.get(5);
        start_x = (Double) stage_list.get(6);
        start_y = (Double) stage_list.get(7);
        start_z = (Double) stage_list.get(8);
        start_tp_x =(Double)  stage_list.get(9);
        start_tp_y = (Double) stage_list.get(10);
        start_tp_z = (Double) stage_list.get(11);
        start_tp_yaw = (Float) stage_list.get(12);
        start_tp_pitch = (Float) stage_list.get(13);
        goal_x = (Double) stage_list.get(14);
        goal_y = (Double) stage_list.get(15);
        goal_z = (Double) stage_list.get(16);
        goal_tp_x = (Double) stage_list.get(17);
        goal_tp_y = (Double) stage_list.get(18);
        goal_tp_z = (Double) stage_list.get(19);
        goal_tp_yaw = (Float) stage_list.get(20);
        goal_tp_pitch = (Float) stage_list.get(21);
        star_time_3 = (Long) stage_list.get(22);
        star_time_2 = (Long) stage_list.get(23);
        star_time_1 = (Long) stage_list.get(24);

        invCreateStage.setItem(0, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"StageName: "+ChatColor.WHITE + stage_name, ""));
        invCreateStage.setItem(1, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"StageTPLocation","",ChatColor.WHITE+ "x:" + stage_tp_x + " " + "y:" + stage_tp_y + " " + "z:" + stage_tp_z));
        invCreateStage.setItem(2, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"StartLocation","",ChatColor.WHITE+ "x:" + start_x + " " + "y:" + start_y + " " + "z:" + start_z));
        invCreateStage.setItem(3, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"StartTPLocation","",ChatColor.WHITE+ "x:" + start_tp_x + " " + "y:" + start_tp_y + " " + "z:" + start_tp_z));
        invCreateStage.setItem(4, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"GoalLocation","",ChatColor.WHITE+ "x:" + goal_x + " " + "y:" + goal_y + " " + "z:" + goal_z));
        invCreateStage.setItem(5, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"GoalTPLocation","",ChatColor.WHITE+ "x:" + goal_tp_x + " " + "y:" + goal_tp_y + " " + "z:" + goal_tp_z));
        invCreateStage.setItem(6, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"☆3タイム: "+ChatColor.WHITE + getStringTime(star_time_3), ""));
        invCreateStage.setItem(7, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"☆2タイム: "+ChatColor.WHITE + getStringTime(star_time_2), ""));
        invCreateStage.setItem(8, Item.createitem(Material.EMERALD_BLOCK, 1, ChatColor.GREEN+"☆1タイム: "+ChatColor.WHITE + getStringTime(star_time_1), ""));
        setCheckPointInventory();
    }

    public ArrayList<Object> getStageData() {
        ArrayList<Object> list = new ArrayList<>();

        list.add(stage_id);
        list.add(stage_name);
        list.add(stage_tp_x);
        list.add(stage_tp_y);
        list.add(stage_tp_z);
        list.add(stage_tp_yaw);
        list.add(stage_tp_pitch);
        list.add(start_x);
        list.add(start_y);
        list.add(start_z);
        list.add(start_tp_x);
        list.add(start_tp_y);
        list.add(start_tp_z);
        list.add(start_tp_yaw);
        list.add(start_tp_pitch);
        list.add(goal_x);
        list.add(goal_y);
        list.add(goal_z);
        list.add(goal_tp_x);
        list.add(goal_tp_y);
        list.add(goal_tp_z);
        list.add(goal_tp_yaw);
        list.add(goal_tp_pitch);
        list.add(star_time_3);
        list.add(star_time_2);
        list.add(star_time_1);

        return list;
    }
    public ArrayList<Double[]> getCheckPointList(){
        return  checkPoint_list;
    }

    public String getStringTime(long time){
        return String.format("%02d:%02d:%03d", (time / (1000 * 60)) % 60, (time / 1000) % 60, time % 1000);
    }
}
