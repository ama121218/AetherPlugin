package net.oriserver.aether.aether.command.commands;


import net.oriserver.aether.aether.sqlite.SaveInventoryDB;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class SaveInventory implements CommandExecutor {

    private final SaveInventoryDB saveInventoryDB;

    public SaveInventory(SaveInventoryDB saveInventoryDB){
        this.saveInventoryDB = saveInventoryDB;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if (!player.isOp()) return true;
        if(args.length<=2){
            if(args.length==0) {
                Random random = new Random();
                String inventory_name = String.format("%06d", random.nextInt(1000000));
                Inventory inventory = player.getInventory();
                boolean isData = false;
                saveInventoryDB.deleteData(player.getUniqueId().toString(),inventory_name);
                for (int i = 0; i < 9; i++) {
                    ItemStack item = inventory.getItem(i);
                    if (item == null) continue;
                    if(item.hasItemMeta() || item.getItemMeta().hasDisplayName()){
                        String item_name = item.getItemMeta().getDisplayName();
                        saveInventoryDB.insertData(player.getUniqueId().toString(), inventory_name, i, String.valueOf(item.getType()), item_name);
                    }else {
                        saveInventoryDB.insertData(player.getUniqueId().toString(), inventory_name, i, String.valueOf(item.getType()), "");
                    }
                    isData = true;
                }
                if(isData)player.sendMessage(ChatColor.GOLD + "Inventoryを" + ChatColor.WHITE + inventory_name + ChatColor.GOLD + "として設定しました");
                else player.sendMessage("設定するアイテムがありません。");
            }
            else if(args.length==1){
                String inventory_name = args[0];
                Inventory inventory = player.getInventory();
                boolean isData = false;
                saveInventoryDB.deleteData(player.getUniqueId().toString(), inventory_name);
                for (int i = 0; i < 9; i++) {
                    ItemStack item = inventory.getItem(i);
                    if (item == null) continue;
                    if(item.hasItemMeta() || item.getItemMeta().hasDisplayName()){
                        String item_name = item.getItemMeta().getDisplayName();
                        saveInventoryDB.insertData(player.getUniqueId().toString(), inventory_name, i, String.valueOf(item.getType()), item_name);
                    }else {
                        saveInventoryDB.insertData(player.getUniqueId().toString(), inventory_name, i, String.valueOf(item.getType()), "");
                    }
                    isData = true;
                }
                if(isData)player.sendMessage(ChatColor.GOLD + "Inventoryを" + ChatColor.WHITE + inventory_name + ChatColor.GOLD + "として設定しました");
                else player.sendMessage("設定するアイテムがありません。");
            }
            else{
                if(args[0].equals("get")){
                    ArrayList<Object[]> item_list = saveInventoryDB.getDataInInventory(player.getUniqueId().toString(),args[1]);
                    if(item_list.size()==0){ player.sendMessage(args[1]+"は保存されていません");return false;}
                    for(int i=0;i<9;i++)player.getInventory().clear(i);
                    for(Object[] objects:item_list){
                        player.getInventory().setItem((int)objects[0], Item.createitem(Material.getMaterial((String)objects[1]),1,(String)objects[2]));
                    }
                }
                else if(args[1].equals("a")||args[1].equals("admin")) {
                    String inventory_name = args[0];
                    Inventory inventory = player.getInventory();
                    boolean isData = false;
                    saveInventoryDB.deleteData("admin",inventory_name);
                    for (int i = 0; i < 9; i++) {
                        ItemStack item = inventory.getItem(i);
                        if (item == null) continue;
                        if(item.hasItemMeta() || item.getItemMeta().hasDisplayName()){
                            String item_name = item.getItemMeta().getDisplayName();
                            saveInventoryDB.insertData("admin", inventory_name, i, String.valueOf(item.getType()), item_name);
                        }else {
                            saveInventoryDB.insertData("admin", inventory_name, i, String.valueOf(item.getType()), "");
                        }
                        isData = true;
                    }
                    if(isData)player.sendMessage(ChatColor.GOLD + "Inventoryを" + ChatColor.WHITE + inventory_name + ChatColor.GOLD + "(admin)として設定しました");
                    else player.sendMessage("設定するアイテムがありません。");
                }
            }
        }
        else{
            player.sendMessage("引数の数が違うかコマンドが間違っています。");
        }


        return true;
    }
}

