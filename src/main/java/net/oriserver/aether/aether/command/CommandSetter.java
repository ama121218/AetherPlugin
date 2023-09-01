package net.oriserver.aether.aether.command;

import net.oriserver.aether.aether.chat.ChatManager;
import net.oriserver.aether.aether.command.commands.*;
import net.oriserver.aether.aether.command.hideshow.Hide;
import net.oriserver.aether.aether.command.hideshow.Show;
import net.oriserver.aether.aether.hideshow.HideShow;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.saveinventory.SaveInventoryManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandSetter {
    private final JavaPlugin plugin;
    private final PlayerManager pm;
    private final ChatManager chatManager;
    private final SaveInventoryManager saveInventoryManager;
    private final HideShow hideShow;
    public CommandSetter(JavaPlugin plugin, PlayerManager playerManager, ChatManager chatManager, SaveInventoryManager saveInventoryManager, HideShow hideShow){
        this.plugin = plugin;
        this.pm = playerManager;
        this.chatManager = chatManager;
        this.saveInventoryManager = saveInventoryManager;
        this.hideShow = hideShow;
        setCommands();
    }
    public void setCommands(){
        plugin.getCommand("getphone").setExecutor(new GetPhone(pm));
        plugin.getCommand("saveteleport").setExecutor(new SaveTeleport(pm.getSqLiteManager().getSaveTeleportDB()));
        plugin.getCommand("saveitem").setExecutor(new SaveItem(pm.getSqLiteManager().getSaveItemDB()));
        plugin.getCommand("rename").setExecutor(new Rename());
        plugin.getCommand("chatroom").setExecutor(new Chat(chatManager,plugin,pm));
        plugin.getCommand("saveinventory").setExecutor(new SaveInventory(saveInventoryManager));
        plugin.getCommand("getfirstinv").setExecutor(new GetFirstInventory(pm));
        plugin.getCommand("hide").setExecutor(new Hide(hideShow));
        plugin.getCommand("show").setExecutor(new Show(hideShow));
    }
}
