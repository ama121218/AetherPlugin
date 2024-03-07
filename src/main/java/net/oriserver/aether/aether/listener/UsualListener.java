package net.oriserver.aether.aether.listener;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.hideshow.HideShow;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.particle.ParticleManager;
import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.chat.ChatManager;
import net.oriserver.aether.aether.chat.ChatRoom;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.createinventory.CreateInventoryManager;
import net.oriserver.aether.aether.sqlite.playerDB.PlayerPhoneSetting;
import net.oriserver.aether.aether.sqlite.playerDB.PlayerUUIDDB;
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
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UsualListener implements Listener {//プレイヤーが起こす普通のイベントを操作するクラス

    private final PlayerManager playerManager;
    private final SQLiteManager sqLiteManager;
    private final ChatManager chatManager;
    private final CreateInventoryManager saveInventoryManager;
    private final ParticleManager particleManager;
    private final HideShow hideShow;
    private final InventoryManager inventoryManager;

    @Autowired
    public UsualListener(JavaPlugin plugin, PlayerManager playerManager, InventoryManager inventoryManager, SQLiteManager sqLiteManager, ChatManager chatManager, CreateInventoryManager saveInventoryManager, HideShow hideShow, ParticleManager particleManager){
        Bukkit.getPluginManager().registerEvents(this,plugin);
        this.playerManager = playerManager;
        this.inventoryManager = inventoryManager;
        this.sqLiteManager = sqLiteManager;
        this.chatManager = chatManager;
        this.saveInventoryManager = saveInventoryManager;
        this.hideShow = hideShow;
        this.particleManager = particleManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void joinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        onJoin(player);
        if(player.isOp()){
            player.performCommand("wea");
        }
    }
    public void onJoin(Player p) {
        p.setWalkSpeed(0.2f);
        p.setFlySpeed(0.1f);
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.setCollidable(false);

        String uuid = String.valueOf(p.getUniqueId());
        PlayerUUIDDB playerDBManagerUUID = sqLiteManager.getPlayerUUIDDB();

        p.getInventory().setItem(35, Item.getHead(p.getName()));

        if (!playerDBManagerUUID.isPlayerInDatabase(uuid)) {//firstJoinEvent
            p.teleport(new Location(Bukkit.getWorld("world"), 171.500, 96, -4.5, 90, 30));
            playerDBManagerUUID.insertPlayer_name(uuid, p.getName());
            sqLiteManager.getPlayerRealTimeDataDB().insertPlayerData(uuid);
            sqLiteManager.getPlayerSettingDB().insertPlayerData(uuid);
            sqLiteManager.getPlayerJoinQuitDataDB().insertPlayerData(uuid);
            sqLiteManager.getPlayerPhoneSetting().insertData(uuid);
            sqLiteManager.getPlayerHeadBlockDB().insertData(uuid);
        } else if (!playerDBManagerUUID.isChangeName(uuid, p.getName())) {
            playerDBManagerUUID.updatePlayer_name(uuid, p.getName());
        }
        if (!playerManager.isPlayer(uuid)) {
            playerManager.addPlayer(p);
        }
        //サーバー内のプレイヤーデータ
        PlayerStats playerStats = playerManager.getPlayer(uuid);
        playerStats.setPlayer(p);
        if (playerStats.isChatroomonoff()) {
            playerStats.setChatroom("");
        } else {
            String roomName = "General";
            ChatRoom chatRoom = chatManager.getChatrooms().get(roomName);
            chatRoom.getMembers().add(p);
            chatManager.getPlayerRooms().put(p, chatRoom);
        }

        Item.player_partition.put(uuid, playerStats.getPartition());
        //if(!playerStats.isPlayersidebaronoff())playerStats.getPlayerSidebar().setSidebar(player);
        //else playerStats.getPlayerSidebar().cancelSidebar();

        if (!p.isOp()) {
            p.getInventory().clear();
            Item.getFirstInventory(p, playerStats.getPhone());
        }
        playerStats.setListName();
        hideShow.handleNewPlayerJoin(p);
        playerStats.setJoin_time(System.currentTimeMillis());

        p.setGameMode(GameMode.ADVENTURE);

    }

    @EventHandler
    public void onItemSwap(PlayerSwapHandItemsEvent event) {
        Player p = event.getPlayer();
        if(!p.isOp()){event.setCancelled(true);}
        else if(p.getLocation().getPitch()==-90){
            event.setCancelled(true);
            inventoryManager.getHomeInventory().setinv(p);
        }
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
        PlayerStats playerStats = playerManager.getPlayer(p.getUniqueId().toString());
        if (room != null) {
            StringBuilder sb = new StringBuilder();
            if(!playerStats.getBadge().equals("")){
                if(!playerStats.getTag().equals("")){
                    sb.append(playerStats.getBadge()).append(playerStats.getTag()).append(playerStats.getBadge()).append(" ");
                }else{
                    sb.append(playerStats.getBadge()).append(" ");
                }
            }else{
                if(!playerStats.getTag().equals("")){
                    sb.append(playerStats.getTag()).append(" ");
                }
            }
            sb.append(p.getName()).append(" ");
            sb.append(">").append(" ");
            sb.append(chatManager.getJapanese(e.getMessage()));
            sb.append(ChatColor.GRAY).append(" ").append("(").append(e.getMessage()).append(")");
            //sb.append("[").append(room.getName().equals("General")?"G":room.getName()).append("]").append(" ");
            for(Player member : room.getMembers())member.sendMessage(sb.toString());
            for(Player member : chatManager.getChatrooms().get("Admin").getMembers()) member.sendMessage(ChatColor.GRAY+sb.toString());
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
        PlayerStats playerStats = playerManager.getPlayer(uuid);
        if(playerStats==null)return;
        playerStats.setChatroom("General");

        particleManager.setQuitPlayer(player);

        boolean[] setting = playerStats.getSetting();
        SQLiteManager sqLiteManager = playerManager.getSqLiteManager();
        sqLiteManager.getPlayerSettingDB().setPlayerData(uuid,setting);

        PlayerPhoneSetting playerPhoneSetting = sqLiteManager.getPlayerPhoneSetting();
        playerPhoneSetting.setData(uuid, new int[]{playerStats.getPhone(), playerStats.getPartition(), playerStats.getCheckpoint()});

        playerStats.setPast_time(playerStats.getPast_time() + System.currentTimeMillis()-playerStats.getJoin_time());
        sqLiteManager.getPlayerJoinQuitDataDB().setData(uuid,new Object[]{playerStats.getJumpcount(), playerStats.getLocation(),playerStats.getPast_time()});
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
                if(playerManager.isPlayer(player_uuid)) {
                    playerManager.getPlayer(player_uuid.toString()).setJumpcount1();
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
