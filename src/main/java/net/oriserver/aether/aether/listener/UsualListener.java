package net.oriserver.aether.aether.listener;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.hideshow.HideShow;
import net.oriserver.aether.aether.particle.ParticleManager;
import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.chat.ChatManager;
import net.oriserver.aether.aether.chat.ChatRoom;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.saveinventory.SaveInventoryManager;
import net.oriserver.aether.aether.sqlite.PhoneSetting;
import net.oriserver.aether.aether.sqlite.PlayerDBManagerUUID;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UsualListener implements Listener {

    private final PlayerManager pm;
    private final SQLiteManager sq;
    private final ChatManager chatManager;
    private final SaveInventoryManager saveInventoryManager;
    private final ParticleManager particleManager;
    private final HideShow hideShow;


    public UsualListener(PlayerManager pm, SQLiteManager sq, ChatManager chatManager, SaveInventoryManager saveInventoryManager, HideShow hideShow,ParticleManager particleManager){
        this.pm = pm;
        this.sq = sq;
        this.chatManager = chatManager;
        this.saveInventoryManager = saveInventoryManager;
        this.hideShow = hideShow;
        this.particleManager = particleManager;
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

        player.getInventory().setItem(35,Item.getHead(player.getName()));

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
        //サーバー内のプレイヤーデータ
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
        //if(!playerStats.isPlayersidebaronoff())playerStats.getPlayerSidebar().setSidebar(player);
        //else playerStats.getPlayerSidebar().cancelSidebar();

        if(!player.isOp()){
            player.getInventory().clear();
            Item.getFirstInventory(player,playerStats.getPhone());
        }
        playerStats.setListName();
        hideShow.handleNewPlayerJoin(player);
        playerStats.setJoin_time(System.currentTimeMillis());



        if(player.isOp()){
            player.performCommand("wea");
        }
        player.setGameMode(GameMode.ADVENTURE);
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
        PlayerStats playerStats = pm.getPlayer(p.getUniqueId().toString());
        if (room != null) {
            String message =
                    "["+room.getName()+"] "+
                    playerStats.getTag()+ " "+
                    playerStats.getBadge()+" "+p.getName()+ " " + playerStats.getBadgeReverse() + " > "+
                    chatManager.getJapanese(e.getMessage())+ ChatColor.GRAY+" ("+e.getMessage()+")"
            ;
            for (Player member : room.getMembers())member.sendMessage(message);
            for (Player member : chatManager.getChatrooms().get("Admin").getMembers()) member.sendMessage(ChatColor.GRAY+message);
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
        if(room != null) {
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
        if(playerStats==null)return;
        playerStats.setChatroom("General");

        particleManager.setQuitPlayer(player);

        boolean[] setting = playerStats.getSetting();
        SQLiteManager sqLiteManager = pm.getSqLiteManager();
        sqLiteManager.getPlayerDBManagerSetting().setPlayerData(uuid,setting);

        PhoneSetting phoneSetting = sqLiteManager.getPhoneSetting();
        phoneSetting.setData(uuid, new int[]{playerStats.getPhone(), playerStats.getPartition(), playerStats.getCheckpoint()});

        playerStats.setPast_time(playerStats.getPast_time() + System.currentTimeMillis()-playerStats.getJoin_time());
        sqLiteManager.getPlayerDBManagerJQ().setData(uuid,new Object[]{playerStats.getJumpcount(), playerStats.getLocation(),playerStats.getPast_time()});
    }
    private final Set<String> playersJumping = new HashSet<>();

    @EventHandler
    public void onPlayerJump(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        String player_uuid = player.getUniqueId().toString();

        // Y座標が上がっているか確認（ジャンプまたは上昇中）
        if (event.getFrom().getY() < event.getTo().getY()) {
            // 地面にいない（ジャンプまたは上昇中）＆＆まだカウントされていない
            if (!player.isOnGround() && !playersJumping.contains(player_uuid)) {
                // ジャンプ回数を増やす
                if(pm.isPlayer(player_uuid)) {
                    pm.getPlayer(player_uuid.toString()).setJumpcount1();
                    // ジャンプを開始したとしてUUIDをセットに追加
                    playersJumping.add(player_uuid);
                }
            }
        }else if (player.isOnGround()) {
            // 地面にいる場合、セットからUUIDを削除
            playersJumping.remove(player_uuid);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // プレイヤーのY座標が-20以下になった場合の処理
        if (event.getTo().getY() <= -20) {
            Player player = event.getPlayer();
            if(player.getWorld().getName().equals("world")){
                player.teleport(AthleticLocation.getLocation(AthleticLocation.LOBBY));
            } else {
                Location spawnLocation = player.getWorld().getSpawnLocation();
                player.teleport(spawnLocation);
            }
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
        if(!(event.getEntity() instanceof Player))return;
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

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            // ターゲットがプレイヤーで、ダメージを与えるプレイヤーがOPの場合
            if (event.getEntity() instanceof Player && player.isOp()) {
                event.setCancelled(true);
                return;
            }
            // ダメージを与えるプレイヤーがOPではない場合
            if (!player.isOp()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp()) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!player.isOp()) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        event.setCancelled(true);
    }
    @EventHandler()
    public void onEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        if (event.getNewState().getType() == Material.FIRE) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp() && event.getItem() != null &&
                (event.getItem().getType().name().endsWith("_HELMET") ||
                        event.getItem().getType().name().endsWith("_CHESTPLATE") ||
                        event.getItem().getType().name().endsWith("_LEGGINGS") ||
                        event.getItem().getType().name().endsWith("_BOOTS"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp() && event.getHand() != null &&
                (event.getHand().name().endsWith("_HELMET") ||
                        event.getHand().name().endsWith("_CHESTPLATE") ||
                        event.getHand().name().endsWith("_LEGGINGS") ||
                        event.getHand().name().endsWith("_BOOTS"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!player.isOp()) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
        if (event.getEntity().getType() == EntityType.ITEM_FRAME) {
            if (event.getRemover() instanceof Player) {
                Player player = (Player) event.getRemover();
                if (!player.isOp()) {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
        }
    }
}
