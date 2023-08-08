package net.oriserver.aether.aether.listener;

import net.oriserver.aether.aether.InventoryTitle;
import net.oriserver.aether.aether.Item;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.sqlite.PlayerDBManagerUUID;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class UsualListener implements Listener {

    private final PlayerManager pm;
    private final SQLiteManager sq;


    public UsualListener(PlayerManager pm,SQLiteManager sq){
        this.pm = pm;
        this.sq = sq;
    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();

        player.setWalkSpeed(0.2f);
        player.setFlySpeed(0.1f);
        player.setCollidable(false);

        String uuid = String.valueOf(player.getUniqueId());
        PlayerDBManagerUUID playerDBManagerUUID = sq.getPlayerDBManagerUUID();
        Item.player_head.put(uuid,Item.getHead(player.getName()));


        if(!playerDBManagerUUID.isPlayerInDatabase(uuid)){//firstJoinEvent
            player.teleport(new Location(Bukkit.getWorld("world"),171.500,96,-4.5,90,30));
            playerDBManagerUUID.insertPlayer_name(uuid,player.getName());
            sq.getPlayerDBManagerR().insertPlayerData(uuid);
            sq.getPlayerDBManagerSetting().insertPlayerData(uuid);
            sq.getPlayerDBManagerJQ().insertPlayerData(uuid);
        }
        else if(!playerDBManagerUUID.isChangeName(uuid,player.getName())){
            playerDBManagerUUID.updatePlayer_name(uuid, player.getName());
        }
        if(!pm.isPlayer(uuid)) {
            pm.addPlayer(player);
        }
        Item.player_partition.put(uuid,pm.getPlayer(String.valueOf(player.getUniqueId())).getPartition());
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
        if(!p.isOp()){event.setCancelled(true);return;}


    }





}
