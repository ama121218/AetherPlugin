package net.oriserver.aether.aether.player;

import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerStats {
    private Player player;
    private int level;
    private int global;
    private int chart;
    private int star;
    private int AP;

    private int jumpcount;
    private String location;
    private Long past_time;

    private String permission;
    private String status;

    private String chatroom;
    private String[] friends;
    private String[] headblocks;
    private String[] badges;
    private String[] tags;

    private String badge = "";
    private String tag = "";

    private boolean particleonoff;
    private boolean mailonoff;
    private boolean friendonoff;
    private boolean chatroomonoff;
    private boolean playersidebaronoff;
    private boolean chartagainonoff;

    private PlayerSidebar playerSidebar;
    private Long join_time;

    private int phone;
    private int partition;
    private int checkpoint;


    private int chart_page = 1;
    private int level_page = 1;

    private String language;

    PlayerStats(Player player,SQLiteManager sqLiteManager){
        this.player = player;
        String uuid = String.valueOf(player.getUniqueId());

        ArrayList<Integer> arrayList_R = sqLiteManager.getPlayerDBManagerR().getPlayerData(uuid);
        if(arrayList_R==null)return;
        if(arrayList_R.size()==0){
            sqLiteManager.getPlayerDBManagerR().insertPlayerData(uuid);
            arrayList_R = sqLiteManager.getPlayerDBManagerR().getPlayerData(uuid);
        }
        this.level = arrayList_R.get(0);
        this.global = arrayList_R.get(1);
        this.chart = arrayList_R.get(2);
        this.star = arrayList_R.get(3);
        this.AP = arrayList_R.get(4);

        ArrayList<Object> arrayList_JQ = sqLiteManager.getPlayerDBManagerJQ().getData(uuid);
        if(arrayList_JQ==null)return;
        if(arrayList_JQ.size()==0){
            sqLiteManager.getPlayerDBManagerJQ().insertPlayerData(uuid);
            arrayList_JQ = sqLiteManager.getPlayerDBManagerJQ().getData(uuid);
        }
        this.jumpcount = (int) arrayList_JQ.get(0);
        this.location = (String) arrayList_JQ.get(1);
        this.past_time = (long) arrayList_JQ.get(2);

        ArrayList<Object> arrayList_Setting = sqLiteManager.getPlayerDBManagerSetting().getPlayerData(uuid);
        if(arrayList_Setting==null)return;
        if(arrayList_Setting.size()==0){
            sqLiteManager.getPlayerDBManagerSetting().insertPlayerData(uuid);
            arrayList_Setting = sqLiteManager.getPlayerDBManagerSetting().getPlayerData(uuid);
        }
        this.particleonoff = (boolean) arrayList_Setting.get(0);
        this.mailonoff = (boolean) arrayList_Setting.get(1);
        this.friendonoff = (boolean) arrayList_Setting.get(2);
        this.chatroomonoff = (boolean) arrayList_Setting.get(3);
        this.playersidebaronoff = (boolean) arrayList_Setting.get(4);
        this.chartagainonoff = (boolean)  arrayList_Setting.get(5);

        ArrayList<Object> arrayList_Phone = sqLiteManager.getPhoneSetting().getData(uuid);
        if(arrayList_Phone==null)return;
        if(arrayList_Phone.size()==0){
            sqLiteManager.getPhoneSetting().insertData(uuid);
            arrayList_Phone = sqLiteManager.getPhoneSetting().getData(uuid);
        }
        this.phone = (int) arrayList_Phone.get(0);
        this.partition = (int) arrayList_Phone.get(1);
        this.checkpoint = (int) arrayList_Phone.get(2);

        ArrayList<Object> arrayList_HeadBlock = sqLiteManager.getPlayerDBManagerHeadBlock().getData(uuid);
        if(arrayList_HeadBlock==null)return;
        if(arrayList_HeadBlock.size()==0){
            sqLiteManager.getPlayerDBManagerHeadBlock().insertData(uuid);
            arrayList_HeadBlock = sqLiteManager.getPlayerDBManagerHeadBlock().getData(uuid);
        }
        this.headblocks = new String[]{String.valueOf(arrayList_HeadBlock.get(0)),
                String.valueOf(arrayList_HeadBlock.get(1)),
                String.valueOf(arrayList_HeadBlock.get(2)),
                String.valueOf(arrayList_HeadBlock.get(3))
        };

        Item.player_partition.put(uuid,this.partition);
        this.chatroom = this.chatroomonoff ? "" : "General";
        this.playerSidebar = new PlayerSidebar(player.getName(),this.level,this.global,this.star,this.AP,this.chatroom,this.location);

        this.headblocks[0] = "111111111111111";
        this.headblocks[1] = "111111111111111";
        this.headblocks[2] = "111111111111111";
        this.headblocks[3] = "111111111111111";

        this.badges = new String[1];
        this.badges[0] = "111111111111111";

        this.tags = new String[1];
        this.tags[0] = "111111111111111";
    }

    public Player getPlayer() {return this.player;}
    public void setPlayer(Player player) {this.player = player;}
    public int getLevel() {return this.level;}
    public void setLevel(int level) {
        this.level = level;
        this.playerSidebar.setlevel(level);
        setListName();
    }
    public int getGlobal() {return this.global;}
    public void setGlobal(int global) {
        this.global = global;
        this.playerSidebar.setGlobal(global);
        //
    }
    public int getChart() {return this.chart;}
    public void setChart(int chart) {this.chart = chart;}
    public int getStar() {return this.star;}
    public void setStar(int star) {
        this.star = star;
        this.playerSidebar.setStar(star);
        //listname
    }
    public int getAetherpoint() {return this.AP;}
    public void setAetherpoint(int aetherpoint) {
        this.AP = aetherpoint;
        this.playerSidebar.setAP(aetherpoint);
        //listname
    }
    public int getJumpcount() {return this.jumpcount;}
    public void setJumpcount1() {this.jumpcount += 1;}
    public String getLocation() {return this.location;}
    public void setLocation(String location) {
        this.location = location;
        this.getPlayerSidebar().setLocation(location);
    }
    public Long getPast_time() {return this.past_time;}
    public void setPast_time(Long past_time) {this.past_time = past_time;}
    public String getStatus(){return this.status;}
    public void setStatus(String status){this.status = status;}
    public String getChatroom(){return this.chatroom;}
    public void setChatroom(String chatroom){
        this.chatroom = chatroom;
        this.playerSidebar.setChatroom(chatroom.equals("")?"--":chatroom);
    }


    public boolean[] getSetting(){
        boolean[] booleans = new boolean[6];
        booleans[0] = particleonoff;
        booleans[1] = mailonoff;
        booleans[2] = friendonoff;
        booleans[3] = chatroomonoff;
        booleans[4] = playersidebaronoff;
        booleans[5] = chartagainonoff;
        return booleans;
    }

    public boolean isParticleonoff() {return this.particleonoff;}
    public void setParticleonoff(boolean particleonoff) {this.particleonoff = particleonoff;}
    public boolean isMailonoff() {return this.mailonoff;}
    public void setMailonoff(boolean mailonoff) {this.mailonoff = mailonoff;}
    public boolean isFriendonoff() {return this.friendonoff;}
    public void setFriendonoff(boolean friendonoff) {this.friendonoff = friendonoff;}
    public boolean isChatroomonoff() {return this.chatroomonoff;}
    public void setChatroomonoff(boolean chatroomonoff) {this.chatroomonoff = chatroomonoff;}
    public boolean isPlayersidebaronoff() {return this.playersidebaronoff;}
    public void setPlayersidebaronoff(boolean playersidebaronoff) {this.playersidebaronoff = playersidebaronoff;}
    public boolean isChartagainonoff() {return chartagainonoff;}
    public void setChartagainonoff(boolean chartagainonoff) {this.chartagainonoff = chartagainonoff;}

    public int getPhone(){return this.phone;}
    public void setPhone(int phone){this.phone = phone;}
    public int getPartition(){return this.partition;}
    public void setPartition(int partition){this.partition = partition;}
    public int getCheckpoint(){return this.checkpoint;}
    public void setCheckpoint(int checkpoint){this.checkpoint = checkpoint;}

    public PlayerSidebar getPlayerSidebar() {
        return playerSidebar;
    }

    public String[] getHeadblock(){return this.headblocks;}
    public void setHeadblock(String[] headblocks){this.headblocks = headblocks;}

    public void setListName(){
        if(this.level==121){
            player.setPlayerListName(player.getName()+ ChatColor.GRAY+" ["+ChatColor.AQUA+ChatColor.BOLD+120+"+"+ChatColor.GRAY+"] ");
        }else if(this.level>=122){
            player.setPlayerListName(player.getName()+ ChatColor.GRAY+" ["+ChatColor.RED+ChatColor.BOLD+(level-121)+ChatColor.GRAY+"] ");
        }else{
            player.setPlayerListName(player.getName()+ ChatColor.GRAY+" ["+ChatColor.AQUA+ChatColor.BOLD+level+ChatColor.GRAY+"] ");
        }
    }

    public Long getJoin_time(){return this.join_time;}
    public void setJoin_time(Long time){this.join_time=time;}

    public int getChart_page() {return chart_page;}
    public void setChart_page(int chart_page){this.chart_page = chart_page;}

    public int getLevel_page() {return level_page;}
    public void setLevel_page(int level_page){this.level_page = level_page;}

    public String getBadge(){return this.badge;}
    public String getBadgeReverse(){return new StringBuilder(ChatColor.stripColor(badge)).reverse().toString();}
    public void setBadge(String badge){this.badge = badge;}


    public String getTag(){return this.tag;}
    public void setTag(String tag){this.tag = tag;}

    public String getBadges(int page){return this.badges[page];}
    public void setBadges(int page,String badge){this.badges[page] = badge;}

    public String getTags(int page){return this.tags[page];}
    public void setTags(int page,String tags){this.tags[page] = tags;}
}
