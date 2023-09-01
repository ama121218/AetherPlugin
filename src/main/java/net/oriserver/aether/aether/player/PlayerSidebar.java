package net.oriserver.aether.aether.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PlayerSidebar {
    private final Scoreboard board;
    private final Objective objective;
    private final Team noCollisionTeam;

    String s3,s5,s6,s7,s9,s11,s13;

    PlayerSidebar(String player_name,int level,int global,int star,int AP,String chatroom,String location) {
        board = Bukkit.getScoreboardManager().getNewScoreboard();
        GregorianCalendar gcalendar = new GregorianCalendar();
        String time = ChatColor.GRAY + "" + gcalendar.get(Calendar.YEAR) + "/" + (gcalendar.get(Calendar.MONTH) + 1) + "/" + gcalendar.get(Calendar.DATE);
        objective = board.registerNewObjective("player_sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "--Aether Server--");
        s3 = time;
        s5 = ChatColor.WHITE + "" + ChatColor.BOLD + "Level  "+ ChatColor.WHITE +":  " + level;
        s6 = ChatColor.WHITE + "" + ChatColor.BOLD + "Global "+ ChatColor.WHITE +":  " + global;
        s7 = ChatColor.YELLOW + "" + ChatColor.BOLD + "  ✦   "+ ChatColor.WHITE +":  " + star;
        s9 = ChatColor.GREEN + "Aether Points "+ChatColor.WHITE + ":   " + AP;
        s11 = ChatColor.WHITE + "ChatRoom :  "+ChatColor.WHITE + " " + (chatroom.equals("") ? "--" : chatroom);
        s13 = ChatColor.WHITE + "Local :  "+ChatColor.WHITE + " " + location;

        objective.getScore(ChatColor.WHITE + "  > "+ChatColor.RED + player_name + ChatColor.WHITE + " < ").setScore(15);
        objective.getScore(ChatColor.WHITE + "" + ChatColor.BOLD + "－－－－－－－－－－－").setScore(14);
        objective.getScore(s3).setScore(13);
        objective.getScore("§6").setScore(12);
        objective.getScore(s5).setScore(11);
        objective.getScore(s6).setScore(10);
        objective.getScore(s7).setScore(9);
        objective.getScore("§7").setScore(8);
        objective.getScore(s9).setScore(7);
        objective.getScore("§8").setScore(6);
        objective.getScore(s11).setScore(5);
        objective.getScore("§9").setScore(4);
        objective.getScore(s13).setScore(3);

        noCollisionTeam = board.registerNewTeam("noCollision");
        noCollisionTeam.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        noCollisionTeam.addEntry(player_name);

        setlevel(level);
    }
    public void setSidebar(Player p){
        p.setScoreboard(board);
    }

    public void setlevel(int level){
        board.resetScores(s5);
        String string_level;
        if(level==121){
            string_level = ChatColor.AQUA+""+ChatColor.BOLD+"120+";;
        }else if(level>=122){
            string_level = ChatColor.RED+""+ChatColor.BOLD+(level-121);
        }else{
            string_level  = ChatColor.AQUA+""+ChatColor.BOLD+""+level;
        }
        s5 = ChatColor.WHITE + "" + ChatColor.BOLD + "Level  "+ ChatColor.WHITE +":  " + string_level;
        objective.getScore(s5).setScore(11);
    }
    public void setGlobal(int global){
        board.resetScores(s6);
        s6 = ChatColor.WHITE + "" + ChatColor.BOLD + "Global "+ ChatColor.WHITE +":  " + global;
        objective.getScore(s6).setScore(10);
    }

    public void setStar(int star){
        board.resetScores(s7);
        s7 = ChatColor.YELLOW + "" + ChatColor.BOLD + "  ✦   "+ ChatColor.WHITE +":  " + star;
        objective.getScore(s7).setScore(9);
    }

    public void setAP(int AP){
        board.resetScores(s9);
        s9 = ChatColor.GREEN + "Aether Points "+ChatColor.WHITE + ":   " + AP;
        objective.getScore(s9).setScore(7);
    }

    public void setChatroom(String chatroom){
        board.resetScores(s11);
        s11 = ChatColor.WHITE + "ChatRoom :  "+ChatColor.WHITE + " " + chatroom;
        objective.getScore(s11).setScore(5);
    }

    public void setLocation(String location){
        board.resetScores(s13);
        s13 = ChatColor.WHITE + "Local :  "+ChatColor.WHITE + " "+location;
        objective.getScore(s13).setScore(3);
    }



    public void cancelSidebar(Player p) {
        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    }
}
