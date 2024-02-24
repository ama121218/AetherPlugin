package net.oriserver.aether.aether.command;

import net.oriserver.aether.aether.chart.ChartManager;
import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.chat.ChatManager;
import net.oriserver.aether.aether.command.commands.*;
import net.oriserver.aether.aether.command.hideshow.Hide;
import net.oriserver.aether.aether.command.hideshow.Show;
import net.oriserver.aether.aether.hideshow.HideShow;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.particle.ParticleManager;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.createinventory.CreateInventoryManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandSetter {

    @Autowired
    public CommandSetter(JavaPlugin plugin, InventoryManager inventoryManager, PlayerManager playerManager, ChatManager chatManager, CreateInventoryManager createInventoryManager,
                         HideShow hideShow, ParticleManager particleManager, ChartManager chartManager){

        plugin.getCommand("teleportaether").setExecutor(new TeleportAether(chartManager.getChartStageInfo()));
        plugin.getCommand("getphone").setExecutor(new GetPhone(playerManager));
        plugin.getCommand("saveteleport").setExecutor(new SaveTeleport(playerManager.getSqLiteManager().getSaveTeleportDB()));
        plugin.getCommand("saveitem").setExecutor(new SaveItem(playerManager.getSqLiteManager().getSaveItemDB()));
        plugin.getCommand("rename").setExecutor(new Rename());
        plugin.getCommand("chatroom").setExecutor(new Chat(chatManager,plugin,playerManager));
        plugin.getCommand("saveinventory").setExecutor(new SaveInventory(playerManager.getSqLiteManager().getSaveInventoryDB()));
        plugin.getCommand("savecommand").setExecutor(new SaveCommand(playerManager.getSqLiteManager().getSaveCommandDB()));
        plugin.getCommand("createinventory").setExecutor(new CreateInventory(createInventoryManager));
        plugin.getCommand("getfirstinv").setExecutor(new GetFirstInventory(playerManager));
        plugin.getCommand("hide").setExecutor(new Hide(hideShow));
        plugin.getCommand("show").setExecutor(new Show(hideShow));
        plugin.getCommand("i").setExecutor(new I());
        plugin.getCommand("skull").setExecutor(new Skull());
        plugin.getCommand("particle").setExecutor(new PlayParticle(particleManager));
        plugin.getCommand("openphone").setExecutor(new OpenPhone(inventoryManager));
        new PlaySound(plugin);
    }
}
