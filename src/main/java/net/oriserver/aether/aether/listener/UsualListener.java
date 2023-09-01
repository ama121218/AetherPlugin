package net.oriserver.aether.aether.listener;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.oriserver.aether.aether.InventoryTitle;
import net.oriserver.aether.aether.hideshow.HideShow;
import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.chat.ChatManager;
import net.oriserver.aether.aether.chat.ChatRoom;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.saveinventory.SaveInventoryManager;
import net.oriserver.aether.aether.sqlite.PhoneSetting;
import net.oriserver.aether.aether.sqlite.PlayerDBManagerUUID;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;

public class UsualListener implements Listener {

    private final PlayerManager pm;
    private final SQLiteManager sq;
    private final ChatManager chatManager;
    private final SaveInventoryManager saveInventoryManager;
    private final HideShow hideShow;


    public UsualListener(PlayerManager pm, SQLiteManager sq, ChatManager chatManager, SaveInventoryManager saveInventoryManager, HideShow hideShow){
        this.pm = pm;
        this.sq = sq;
        this.chatManager = chatManager;
        this.saveInventoryManager = saveInventoryManager;
        this.hideShow = hideShow;


    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void joinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();

        player.setWalkSpeed(0.2f);
        player.setFlySpeed(0.1f);
        for(PotionEffect effect : player.getActivePotionEffects()) {player.removePotionEffect(effect.getType());}
        player.setCollidable(false);

        String uuid = String.valueOf(player.getUniqueId());
        PlayerDBManagerUUID playerDBManagerUUID = sq.getPlayerDBManagerUUID();

        Item.player_head.put(player.getName(),Item.getHead(player.getName()));

        if(!playerDBManagerUUID.isPlayerInDatabase(uuid)){//firstJoinEvent
            player.teleport(new Location(Bukkit.getWorld("world"),171.500,96,-4.5,90,30));
            playerDBManagerUUID.insertPlayer_name(uuid,player.getName());
            sq.getPlayerDBManagerR().insertPlayerData(uuid);
            sq.getPlayerDBManagerSetting().insertPlayerData(uuid);
            sq.getPlayerDBManagerJQ().insertPlayerData(uuid);
            sq.getPhoneSetting().insertData(uuid);
            sq.getPlayerDBManagerHeadBlock().insertData(uuid);
        }
        else if(!playerDBManagerUUID.isChangeName(uuid,player.getName())){
            playerDBManagerUUID.updatePlayer_name(uuid, player.getName());
        }
        if(!pm.isPlayer(uuid)) {
            pm.addPlayer(player);
        }

        PlayerStats playerStats = pm.getPlayer(uuid);
        playerStats.setPlayer(player);
        if(playerStats.isChatroomonoff()){
            playerStats.setChatroom("");
        }
        else{
            String roomName = "General";
            ChatRoom chatRoom = chatManager.getChatrooms().get(roomName);
            chatRoom.getMembers().add(player);
            chatManager.getPlayerRooms().put(player,chatRoom);
        }

        Item.player_partition.put(uuid,playerStats.getPartition());
        if(!playerStats.isPlayersidebaronoff())playerStats.getPlayerSidebar().setSidebar(player);
        else playerStats.getPlayerSidebar().cancelSidebar(player);

        if(!player.isOp()){
            player.getInventory().clear();
            Item.getFirstInventory(player,playerStats.getPhone());
        }
        playerStats.setListName();
        hideShow.handleNewPlayerJoin(player);
        playerStats.setJoin_time(System.currentTimeMillis());
    }

    @EventHandler
    public void dragInventory(InventoryDragEvent e){
        String title = e.getInventory().getTitle();
        InventoryTitle titleEnum = InventoryTitle.fromString(title);
        if (titleEnum != null) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onItemSwap(PlayerSwapHandItemsEvent event) {
        Player p = event.getPlayer();
        if(!p.isOp()){event.setCancelled(true);}
        else{
            event.setCancelled(true);
            // シフトキーが押されているか確認
            if (!p.isSneaking()) saveInventoryManager.setInventory1(p);
            else saveInventoryManager.setInventory2(p);
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player p = e.getPlayer();
        ChatRoom room = chatManager.getPlayerRooms().get(p);
        if (room != null) {
            String message = "["+room.getName()+"] "+p.getName()+" > "+chatManager.getJapanese(e.getMessage())+ ChatColor.GRAY+" ("+e.getMessage()+")";
            for (Player member : room.getMembers())member.sendMessage(message);
            for (Player member : chatManager.getChatrooms().get("admin").getMembers()) member.sendMessage(message);
        }else{

            p.sendMessage("チャットルームに入っていないため発言できません。");
            p.sendMessage("General チャットルームに入りますか？");

            String cmd1 = "/chatroom join General";
            TextComponent textComponent1 = new TextComponent("[入る]");
            textComponent1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd1));
            p.spigot().sendMessage(textComponent1);
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        ChatRoom room = chatManager.getPlayerRooms().get(player);

        if (room != null) {
            room.removeMember(player);
            chatManager.getPlayerRooms().remove(player);
            if (room.getOwner()!=null && room.getOwner().equals(player)) {
                if (room.getMembers().isEmpty()) {
                    chatManager.getChatrooms().remove(room.getName());
                } else {
                    Player newOwner = room.getMembers().iterator().next();
                    room.setOwner(newOwner);
                    newOwner.sendMessage("You are now the owner of the chatroom " + room.getName());
                }
            }
        }
        String uuid = String.valueOf(player.getUniqueId());
        PlayerStats playerStats = pm.getPlayer(uuid);
        playerStats.setChatroom("General");

        boolean[] setting = playerStats.getSetting();
        SQLiteManager sqLiteManager = pm.getSqLiteManager();
        sqLiteManager.getPlayerDBManagerSetting().setPlayerData(uuid,setting);

        PhoneSetting phoneSetting = sqLiteManager.getPhoneSetting();
        phoneSetting.setData(uuid, new int[]{playerStats.getPhone(), playerStats.getPartition(), playerStats.getCheckpoint()});

        playerStats.setPast_time(playerStats.getPast_time() + System.currentTimeMillis()-playerStats.getJoin_time());
        sqLiteManager.getPlayerDBManagerJQ().setData(uuid,new Object[]{playerStats.getJumpcount(), playerStats.getLocation(),playerStats.getPast_time()});
    }
    @EventHandler
    public void onPlayerJump(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (e.getFrom().getY() < e.getTo().getY() && p.isOnGround()) {
            pm.getPlayer(String.valueOf(p.getUniqueId())).setJumpcount1();
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().isOp()) {
            // OPのプレイヤーがアイテムをドロップした場合、アイテムを消去する
            event.getItemDrop().remove();
        } else {
            // OPでないプレイヤーがアイテムをドロップした場合、ドロップをキャンセルする
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Player player = (Player) event.getEntity();
        switch (event.getCause()) {
            case SUFFOCATION: // 窒息死
            case DROWNING:    // 溺死
            case LAVA:        // 溶岩ダメージ
            case FIRE:        // 火のダメージ
                event.setCancelled(true);
                break;
            case FIRE_TICK: // 火傷の効果によるダメージ
                // ダメージをキャンセル
                event.setCancelled(true);
                // 燃えているエフェクトを消す
                player.setFireTicks(0);
                break;
            default:
                break;
        }
        if (event.getEntity() instanceof Player) {
            // ダメージ量を0に設定
            event.setDamage(0.0);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        // 食料レベルの変更をキャンセル
        event.setCancelled(true);
    }

}
