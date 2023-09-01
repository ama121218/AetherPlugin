package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.chat.ChatManager;
import net.oriserver.aether.aether.chat.ChatRoom;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Chat implements CommandExecutor {

    private final ChatManager cm;
    private final Plugin plugin;
    private final PlayerManager playerManager;
    public Chat(ChatManager cm, Plugin plugin, PlayerManager playerManager) {
        this.cm = cm;
        this.plugin = plugin;
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if (args.length < 2) return false;
        switch (args[0].toLowerCase()) {
            case "create":
                String name = args[1];
                boolean isPrivate = false;
                if(args.length==3){
                    isPrivate = "private".equalsIgnoreCase(args[2]);
                }
                if(cm.getChatrooms().get(name)!=null || name.equals("General") || name.equals("Admin")){
                    player.sendMessage("この名前のチャットルームは既に存在します");
                    return true;
                }
                cm.getChatrooms().put(name, new ChatRoom(name, isPrivate, player));
                cm.getPlayerRooms().put(player, cm.getChatrooms().get(name));
                playerManager.getPlayer(String.valueOf(player.getUniqueId())).setChatroom(name);
                player.sendMessage(isPrivate ? (name+"(private) チャットルームを作成しました") : name+"(public) チャットルームを作成しました");
                break;

            case "join":
                String roomName = args[1];
                ChatRoom room = cm.getChatrooms().get(roomName);
                if (room != null) {
                    if (room.isPrivate()) {
                        player.sendMessage("このチャットルームはプライベートです");
                    } else {
                        room.addMember(player);
                        cm.getPlayerRooms().put(player, room);
                        playerManager.getPlayer(String.valueOf(player.getUniqueId())).setChatroom(roomName);
                        player.sendMessage(roomName+"に入りました");
                    }
                } else {
                    player.sendMessage("Room doesn't exist!");
                }
                break;
            case "remove":
                String removeName = args[1];
                ChatRoom removeRoom = cm.getChatrooms().get(removeName);
                if (removeRoom != null && removeRoom.getOwner() !=null && removeRoom.getOwner().equals(player)) {
                    cm.getChatrooms().remove(removeName);
                    for (Player p : removeRoom.getMembers()) {
                        cm.getPlayerRooms().remove(p);
                        p.sendMessage("The chatroom " + removeName + " has been removed by the owner.");
                    }
                    player.sendMessage("You have removed the chatroom " + removeName + ".");
                } else {
                    player.sendMessage("You can't remove this chatroom.");
                }
                break;
            case "kick":
                if (args.length < 3) return false;
                String kickRoomName = args[1];
                Player target = plugin.getServer().getPlayer(args[2]);
                if (target == null) {
                    player.sendMessage("That player is not online!");
                    return true;
                }
                ChatRoom kickRoom = cm.getChatrooms().get(kickRoomName);
                if (kickRoom != null && kickRoom.getOwner() != null && kickRoom.getOwner().equals(player)) {
                    if (kickRoom.getMembers().contains(target)) {
                        kickRoom.removeMember(target);
                        cm.getPlayerRooms().remove(target);
                        target.sendMessage("You have been kicked out of the chatroom " + kickRoomName + " by the owner.");
                        player.sendMessage("You have kicked " + target.getName() + " out of the chatroom " + kickRoomName + ".");
                    } else {
                        player.sendMessage(target.getName() + " is not in this chatroom.");
                    }
                } else {
                    player.sendMessage("You can't kick players from this chatroom.");
                }
                break;
        }
        return true;
    }
}
