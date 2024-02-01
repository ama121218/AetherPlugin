package net.oriserver.aether.aether.createinventory;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CreateInventory {

    ArrayList<ItemStack[]> item_list = new ArrayList<ItemStack[]>();
    ArrayList<String> item_string = new ArrayList<String>();
    int number;

    CreateInventory(Player p){
        number = 0;
        ItemStack[] temp = new ItemStack[9];
        Inventory player_inventory = p.getInventory();
        ItemStack[] allitem = player_inventory.getContents();
        System.arraycopy(allitem, 0, temp, 0, 9);
        item_list.add(temp);
        item_string.add("");
    }

    public void createInventory(Player p,String s) {
        ItemStack[] temp = new ItemStack[9];
        Inventory player_inventory = p.getInventory();
        ItemStack[] allitem = player_inventory.getContents();
        System.arraycopy(allitem, 0, temp, 0, 9);
        item_list.set(number,temp);

        temp = new ItemStack[9];
        for (int i = 0; i < 9; i++) {
            player_inventory.setItem(i, new ItemStack(Material.AIR));
            temp[i] = new ItemStack(Material.AIR);
        }
        item_list.add(temp);
        item_string.add(s);
        number=item_list.size()-1;
    }

    public void setInventory1(Player p){
        if(item_list.size()==0){
            p.sendMessage("保存されているInventoryはありません");
        }
        else{
            ItemStack[] temp = new ItemStack[9];
            Inventory player_inventory = p.getInventory();
            ItemStack[] allitem = player_inventory.getContents();
            System.arraycopy(allitem, 0, temp, 0, 9);
            item_list.set(number,temp);
            number = number+1<item_list.size()?number+1:0;
            ItemStack[] items = item_list.get(number);
            for (int i = 0; i < 9; i++) {
                player_inventory.setItem(i, items[i]);
            }
            String s = item_string.get(number).equals("")?""+number:item_string.get(number);
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(s));
        }
    }
    public void setInventory2(Player p){
        if(item_list.size()==0){
            p.sendMessage("保存されているInventoryはありません");
        }
        else{
            ItemStack[] temp = new ItemStack[9];
            Inventory player_inventory = p.getInventory();
            ItemStack[] allitem = player_inventory.getContents();
            System.arraycopy(allitem, 0, temp, 0, 9);
            item_list.set(number,temp);
            number = number-1>-1?number-1:item_list.size()-1;
            ItemStack[] items = item_list.get(number);
            for (int i = 0; i < 9; i++) {
                player_inventory.setItem(i, items[i]);
            }
            String s = item_string.get(number).equals("")?""+number:item_string.get(number);
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(s));
        }
    }
    public void deleteInventory(Player p) {
        if(item_list.size()==1){
            item_list.remove(number);
            item_string.remove(number);
            number = 0;
            ItemStack[] temp = new ItemStack[9];
            Inventory player_inventory = p.getInventory();
            for (int i = 0; i < 9; i++) {
                player_inventory.setItem(i, new ItemStack(Material.AIR));
                temp[i] = new ItemStack(Material.AIR);
            }
            item_list.add(temp);
            item_string.add("");
        }else {
            item_list.remove(number);
            item_string.remove(number);
            if (item_list.size() == 1) number = 0;
            else number = number == 0 ? 1 : number - 1;

            Inventory player_inventory = p.getInventory();
            ItemStack[] items = item_list.get(number);
            for (int i = 0; i < 9; i++) {
                player_inventory.setItem(i, items[i]);
            }
            String s = item_string.get(number).equals("")?""+number:item_string.get(number);
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(s));
            p.sendMessage("現在のInventoryを削除しました");
        }
    }
    public void changeName(String s){
        item_string.set(number,s);
    }
}
