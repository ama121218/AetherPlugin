package net.oriserver.aether.aether.command;

import net.oriserver.aether.aether.TNTRun.TNTRunMain;
import net.oriserver.aether.aether.chat.ChatManager;
import net.oriserver.aether.aether.command.commands.*;
import net.oriserver.aether.aether.command.hideshow.Hide;
import net.oriserver.aether.aether.command.hideshow.Show;
import net.oriserver.aether.aether.hideshow.HideShow;
import net.oriserver.aether.aether.particle.ParticleManager;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.saveinventory.SaveInventoryManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.sound.midi.Soundbank;

public class CommandSetter {

    public CommandSetter(JavaPlugin plugin, PlayerManager playerManager, ChatManager chatManager, SaveInventoryManager saveInventoryManager,
                         HideShow hideShow, TNTRunMain tntRunMain, ParticleManager particleManager){

        plugin.getCommand("getphone").setExecutor(new GetPhone(playerManager));
        plugin.getCommand("saveteleport").setExecutor(new SaveTeleport(playerManager.getSqLiteManager().getSaveTeleportDB()));
        plugin.getCommand("saveitem").setExecutor(new SaveItem(playerManager.getSqLiteManager().getSaveItemDB()));
        plugin.getCommand("rename").setExecutor(new Rename());
        plugin.getCommand("chatroom").setExecutor(new Chat(chatManager,plugin,playerManager));
        plugin.getCommand("saveinventory").setExecutor(new SaveInventory(saveInventoryManager));
        plugin.getCommand("getfirstinv").setExecutor(new GetFirstInventory(playerManager));
        plugin.getCommand("hide").setExecutor(new Hide(hideShow));
        plugin.getCommand("show").setExecutor(new Show(hideShow));
        plugin.getCommand("i").setExecutor(new I());
        plugin.getCommand("TNTRun").setExecutor(new TNTRun(tntRunMain));
        plugin.getCommand("skull").setExecutor(new Skull());
        plugin.getCommand("particle").setExecutor(new PlayParticle(particleManager));
        new PlaySound(plugin);
    }
}
